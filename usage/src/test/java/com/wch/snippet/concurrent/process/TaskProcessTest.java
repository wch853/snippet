package com.wch.snippet.concurrent.process;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Test
@Slf4j
public class TaskProcessTest {

    public void testExecuteAsyncTasks() {
        List<Task<Object>> tasks = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Task<Object> task = new Task<Object>() {
                @Override
                public Object execute() throws Exception {
                    log.info("current thread: {}", Thread.currentThread().getName());
                    return null;
                }
            };
            tasks.add(task);
        }

        TaskManager.getDefaultTaskProcess().asyncExecuteTasks(tasks);
    }

    public void testExecuteTasks() {
        List<Task<String>> tasks = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Task<String> task = new Task<String>() {
                @Override
                public String execute() throws Exception {
                    log.info("current thread: {}", Thread.currentThread().getName());
                    return Thread.currentThread().getName();
                }
            };
            tasks.add(task);
        }

        for (String result : TaskManager.getDefaultTaskProcess().executeTasks(tasks)) {
            log.info("result: {}", result);
        }
    }

    public void testExecuteTasksByControl() {
        List<Task<String>> tasks = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Task<String> task = new Task<String>() {
                @Override
                public String execute() throws Exception {
                    log.info("current thread: {}", Thread.currentThread().getName());
                    return Thread.currentThread().getName();
                }
            };
            tasks.add(task);
        }

        for (String result : TaskManager.getDefaultTaskProcess().executeTasksByControl(tasks, 10)) {
            log.info("result: {}", result);
        }
    }

    public void testExecuteIdentityTasks() {
        List<IdentityTask<Object>> identityTasks = new ArrayList<>();

        IdentityTask<Object> task1 = new IdentityTask<Object>() {
            @Override
            public String identity() {
                return "task1";
            }

            @Override
            public String execute() throws Exception {
                log.info("current thread: {}", Thread.currentThread().getName());
                return "task1";
            }
        };
        identityTasks.add(task1);

        IdentityTask<Object> task2 = new IdentityTask<Object>() {
            @Override
            public String identity() {
                return "task2";
            }

            @Override
            public Integer execute() throws Exception {
                log.info("current thread: {}", Thread.currentThread().getName());
                return 2;
            }
        };
        identityTasks.add(task2);

        Map<String, Object> resultMap = TaskManager.getDefaultTaskProcess().executeIdentityTasks(identityTasks);
        log.info("task1 result: {}", resultMap.get("task1"));
        log.info("task2 result: {}", resultMap.get("task2"));
    }
}