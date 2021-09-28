### common utils

    Kotlin 编写, 100 % 兼容Java, 主要使用Kotlin的Null处理、默认参数等特性, 方便util方法编写
    内有大量单源测试及使用方式

Maven

    对项目modules进行了拆分, 源lib不再更新, 按需引入下面的lib
    @Deprecated

```xml

<dependency>
    <groupId>com.any.common</groupId>
    <artifactId>commons</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

    核心common

```xml

<dependency>
    <groupId>com.any.common</groupId>
    <artifactId>core</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

    poi common excel 读写

```xml

<dependency>
    <groupId>com.any.common</groupId>
    <artifactId>poi</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

Gradle

```groovy
implementation 'com.any.common:commons:1.0.0-SNAPSHOT'
```

### 主要内容

#### cache 包

    Cacheable 可缓存接口标志
    ICache 缓存接口

    使用实例

```java

import com.any.common.core.cache.ICache;

@SuppressWarnings(value = "NullableProblems")
@Service
public class CacheService implements ICache {

    @Setter(onMethod_ = @Autowired)
    private RedissonClient cacheClient;

    @Override
    public <T extends Cacheable> T set(T cacheable) {
        cacheClient.getBucket(cacheable.key()).set(cacheable);
        return cacheable;
    }

    @Override
    public <T extends Cacheable> T set(T cacheable, long expire, TimeUnit unit) {
        cacheClient.getBucket(cacheable.key()).set(cacheable, expire, unit);
        return cacheable;
    }

    @Override
    public <T> T set(String key, T cache) {
        cacheClient.getBucket(key).set(cache);
        return cache;
    }

    @Override
    public <T> T set(String key, T cache, long expire, TimeUnit unit) {
        cacheClient.getBucket(key).set(cache, expire, unit);
        return cache;
    }

    @Override
    public <T> T get(String key) {
        return cacheClient.<T>getBucket(key).get();
    }

    @Override
    public boolean exists(String key) {
        return cacheClient.getBucket(key).isExists();
    }

    @Override
    public void expire(String key, long expire, TimeUnit unit) {
        cacheClient.getBucket(key).expire(expire, unit);
    }

    @Override
    public void del(String key) {
        cacheClient.getBucket(key).delete();
    }
}
```

#### bean 包

    Beans 处理Object-Map转换

#### capacity

    容量相关util
    IoCapacityUtil: 特指计算机相关io (网络io读写, 文件io占用, 磁盘io占用等io)容量计算

#### collection

    集合相关utils
    ListBuilder 链式构造List
    MapBuilder 链式构造Map

#### convert

    各种转换utils
    Converters obj a -> obj b 的转换操作

#### currency

    货币相关util

#### datetime

    时间相关util
    DateTimes 处理时间解析, 日期解析, 日期时间生成, 格式化等

#### domain

    common-domain
    Tuple K-V元组类 (原Pair, 名称冲突太多)

#### file

    文件相关utils
    Files 文件名称及后缀解析处理

#### function

    ActionFunction 无返回值 动作类 function
    ActionProxy 动作类 代理
        用于将不同线程的action统一在一个线程内串行处理
        用于将多个数据库(cache、mq)事务串行至一个关联事务内, 方便代码逻辑解耦

#### id

    id类相关util
    IdUtil snowflake 实现的 id生成器
    SfWorker snowflake worker

#### net

    网络相关util
    IpUtil 处理ip地址相关

#### number

    数字类util
    Numbers 处理数字之间, 数字-字符串之间的转换

#### param

    参数校验util
    Params 校验参数

#### random

    随机类相关util
    Randoms 生成随机数字, 随机字符串等
