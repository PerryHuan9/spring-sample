### 简介
使用 `SpringBoot + MyBatis + MyBatisGenerator` 搭建开发示例，
mapper dao接口、model对象及mapper xml配置完全生成。
其原理是 MyBatisGenerator连接数据库并读取数据库表格字段信息生成对应代码。

### 配置
generatorConfig.xml为 MyBatisGenerator的配置文件，其中指定对应的数据库地址及数据库表格。


### 生成代码
在com.sample.mybatis.mbg包下的Generator类是代码生成类。


### 执行
已在pom.xml中进行了profiles的配置，运行：
    
    mvn compile -Pgenerator-mybatis
即可生成代码。

















