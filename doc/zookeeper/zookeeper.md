### zookeeper概述
zookeeper是一种分布式协调服务，用于在分布式环境中协调和管理服务。提供的服务包括：配置服务、名字服务、分布式同步、组服务等。
#### 特性
- 一致性：数据一致性，按照顺序。
- 原子性：事务要么成功，要么失败，不会局部化。
- 单一视图：客户端连接的任一节点，数据都是一致的。
- 可靠性：每次对zookeeper的操作都会保存在服务端。
- 实时性：客户端可以读取到zookeeper服务器的最新数据。
#### 作用
- master节点选举：主节点宕机后从节点通过选举产生新的主节点，并保证主节点唯一，保证集群高可用。
- 统一配置文件管理：修改一份配置，同步到其它服务器上，减少工作量。
- 发布与订阅：发布者将数据存储在znode上，订阅者可以读取这个数据。
- 分布式锁：在分布式环境下提供锁，管理不同进程对资源的争夺。
- 集群管理：保证集群中的数据一致性。

### 配置zookeeper
#### 安装
从[zookeeper官网](http://zookeeper.apache.org/)找到zookeeper的镜像地址，下载并解压。
```
# 下载
wget https://www-eu.apache.org/dist/zookeeper/zookeeper-3.4.12/zookeeper-3.4.12.tar.gz
# 解压
tar -zxvf zookeeper-3.4.12.tar.gz
mv zookeeper-3.4.12 zookeeper
```
打开/etc目录下的profile文件，为zookeeper配制环境变量，编辑完成后使用 ```source /etc/profile``` 使配置生效。
```
export ZOOKEEPER_HOME=/usr/local/zookeeper
export PATH=$PATH:$ZOOKEEPER_HOME/bin
```
#### 配置文件
在zookeeper下的conf目录中有名为zoo.cfg的配置文件。
```
# zookeeper的基本时间单位（ms）
tickTime=2000
# 集群中节点连接并同步到master节点的初始化连接时间，超时失败（tickTime的倍数）
initLimit=10
# 集群中主从节点数据同步的时间限制，超时从节点被丢弃（tickTime的倍数）
syncLimit=5
# zookeeper数据目录
dataDir=/usr/local/zookeeper/data
# zookeeper客户端连接端口
clientPort=2181
```
#### 操作zookeeper
```
# 启动
zkServer.sh start
# 关闭
zkServer.sh stop
# 重启
zkServer.sh restart
# 查看状态
zkServer.sh status
```

### zookeeper的使用
#### zk数据模型
- zk的数据模型可以理解为文件目录。
- 每个节点成为znode，它可以有子节点，也可以有数据。
- 节点有临时和永久之分，临时节点在客户端断开后消失。
- 每个zk节点有各自的版本号，当节点数据发生变化，该节点的版本号会累加。修改节点时，版本号不匹配会报错。
- 节点存储的数据不宜过大。
- 节点可以设置acl来控制用户访问权限。
#### zookeeper客户端操作
```
# 通过zkCli连接zookeeper
zkCli.sh
# 查看指定路径下的数据/子节点节点
ls /
# 查看指定路径下的子节点和状态信息
stat /
# 查看指定路径下的数据和状态信息
get /
# 查看指定路径下的数据、子节点和状态信息
ls2 /
# 退出客户端
quit
```