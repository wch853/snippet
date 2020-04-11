package com.wch.snippet.concurrent.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程执行器
 */
public class TaskProcess {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskProcess.class);

    /**
     * 核心线程数
     */
    private int corePoolSize;

    /**
     * 最大线程数
     */
    private int maximumSize;

    /**
     * 任务域，用于区分不同业务
     */
    private String businessDomain;

    /**
     * 多线程执行器
     */
    private ExecutorService executor;

    public TaskProcess(int corePoolSize, int maximumSize, String businessDomain) {
        this.corePoolSize = corePoolSize;
        this.maximumSize = maximumSize;
        this.businessDomain = businessDomain;
        init();
    }

    private void init() {
        // 创建线程池，60秒回收，拒绝策略：调用者线程执行
        executor = new ThreadPoolExecutor(corePoolSize, maximumSize, 60, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(corePoolSize), new DefaultThreadFactory(businessDomain),
                new ThreadPoolExecutor.CallerRunsPolicy());

        // JVM关闭钩子：关闭线程池
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                if (executor == null) {
                    return;
                }
                try {
                    executor.shutdown();
                    executor.awaitTermination(5, TimeUnit.MINUTES);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public ExecutorService getExecutor() {
        return executor;
    }

    /**
     * 异步执行任务
     *
     * @param tasks
     */
    public <T> void asyncExecuteTasks(List<Task<T>> tasks) {
        for (final Task task : tasks) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        task.execute();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    /**
     * 执行任务并获取结果
     *
     * @param tasks
     * @param <T>
     * @return
     */
    public <T> List<T> executeTasks(List<Task<T>> tasks) {
        final CountDownLatch latch = new CountDownLatch(tasks.size());
        List<Future<T>> futures = new ArrayList<>();
        for (final Task<T> task : tasks) {
            Future<T> future = executor.submit(new Callable<T>() {
                @Override
                public T call() throws Exception {
                    try {
                        return task.execute();
                    } catch (Exception e) {
                        LOGGER.error("executeTasks execute Exception: {}", e);
                        return null;
                    } finally {
                        latch.countDown();
                    }
                }
            });
            // 收集获取计算结果的任务对象
            futures.add(future);
        }

        try {
            // 主线程阻塞，等待计数器归零
            latch.await();
        } catch (InterruptedException e) {
            LOGGER.error("executeTasks await interrupted: {}", e);
        }

        List<T> resultList = new ArrayList<>();
        for (Future<T> future : futures) {
            try {
                T result = future.get();
                if (null != result) {
                    resultList.add(result);
                }
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        return resultList;
    }

    /**
     * 并发控制执行任务并获取结果
     *
     * @param tasks
     * @param concurrentControl
     * @param <T>
     * @return
     */
    public <T> List<T> executeTasksByControl(List<Task<T>> tasks, int concurrentControl) {
        final CountDownLatch latch = new CountDownLatch(tasks.size());
        final Semaphore semaphore = new Semaphore(concurrentControl);
        List<Future<T>> futures = new ArrayList<>();
        for (final Task<T> task : tasks) {
            Future<T> future = executor.submit(new Callable<T>() {
                @Override
                public T call() throws Exception {
                    try {
                        semaphore.acquire();
                        return task.execute();
                    } catch (Exception e) {
                        LOGGER.error("executeTasks execute Exception: {}", e);
                        return null;
                    } finally {
                        latch.countDown();
                        semaphore.release();
                    }
                }
            });
            // 收集获取计算结果的任务对象
            futures.add(future);
        }

        try {
            // 主线程阻塞，等待计数器归零
            latch.await();
        } catch (InterruptedException e) {
            LOGGER.error("executeTasks await interrupted: {}", e);
        }

        List<T> resultList = new ArrayList<>();
        for (Future<T> future : futures) {
            try {
                T result = future.get();
                if (null != result) {
                    resultList.add(result);
                }
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        return resultList;
    }

    /**
     * 执行标记性任务并获取结果
     *
     * @param identityTasks
     * @param <T>
     * @return
     */
    public <T> Map<String, T> executeIdentityTasks(List<IdentityTask<T>> identityTasks) {
        final CountDownLatch latch = new CountDownLatch(identityTasks.size());
        Map<String, Future<T>> futureMap = new HashMap<>();

        for (final IdentityTask<T> identityTask : identityTasks) {
            Future<T> future = executor.submit(new Callable<T>() {
                @Override
                public T call() throws Exception {
                    try {
                        return identityTask.execute();
                    } catch (Exception e) {
                        LOGGER.error("executeIdentityTasks execute Exception: {}", e);
                        return null;
                    } finally {
                        latch.countDown();
                    }
                }
            });
            // 收集标记和获取计算结果的任务对象
            futureMap.put(identityTask.identity(), future);
        }

        try {
            // 主线程阻塞，等待计数器归零
            latch.await();
        } catch (InterruptedException e) {
            LOGGER.error("executeTasks await interrupted: {}", e);
        }

        Map<String, T> resultMap = new HashMap<>();
        for (Map.Entry<String, Future<T>> entry : futureMap.entrySet()) {
            try {
                T result = entry.getValue().get();
                if (null != result) {
                    resultMap.put(entry.getKey(), result);
                }
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        return resultMap;
    }

    /**
     * The default thread factory
     */
    static class DefaultThreadFactory implements ThreadFactory {
        static final AtomicInteger poolNumber = new AtomicInteger(1);
        final ThreadGroup group;
        final AtomicInteger threadNumber = new AtomicInteger(1);
        final String namePrefix;

        DefaultThreadFactory(String businessDomain) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread()
                    .getThreadGroup();
            namePrefix = businessDomain + "-TaskProcess-" + poolNumber.getAndIncrement() + "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }
}
