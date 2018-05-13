# 作者
Adam Lu(刘亚壮)

# mykit-db-sync-provider
基于java开发的功能强大、配置灵活的数据库之间同步工具，和数据产生器一样，均是前段时间因为项目需要编写的小工具，在实际应用场景中，我们经常需要定期将一个数据库的数据同步到另外一个数据库中，常见的一种做法是将源数据库的数据dump为sql文件，然后到目标数据库执行sql文件完成数据库的导入，但是这种方法至少存在以下问题：
- 需要手工操作，效率低
- 当涉及数据表较多时，容易遗漏、出错
- 如果要定期同步，操作人容易忘记
- 难以应付频繁变更数据表或者字段

针对以上存在的问题，将珍贵人力从这种重复、无意义的工作中解脱出来，特意开发这个小工具，其中主要配置主要在jobs.xml中完成

## 主要功能
- 目标数据目前只支持MySQL和SQL Sever，源数据库为任何支持sql语法的数据库
- 根据cron表达式配置数据同步的周期和时间
- 执行多个数据同步任务
- 源数据是根据配置的sql语句查询得到，使用者可以非常灵活根据需要进行修改
- 根据配置的字段，判断同步数据是插入还是更新

## 编译和运行

> mvn package

>cp jobs.xml ./target/jobs.xml

> cd target

> java -jar mykit-db-sync-provider-1.0.0.jar

## 基本使用
cat jobs.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<root>
    <code>0001</code>
    <source> <!-- 源数据库连接方式 -->
        <url>jdbc:oracle:thin:@192.168.1.179:1521:XE</url>
        <username>test</username>
        <password>test</password>
        <dbtype>oracle</dbtype>
        <driver>oracle.jdbc.driver.OracleDriver</driver>
    </source>
    <dest> <!-- 目标数据库连接方式 -->
        <url>jdbc:sqlserver://192.168.1.191:1433;DatabaseName=test</url>
        <username>test</username>
        <password>test</password>
        <dbtype>sqlserver</dbtype>
        <driver>com.microsoft.sqlserver.jdbc.SQLServerDriver</driver>
    </dest>
    <jobs> <!-- 数据库同步任务，可以根据实际需要添加多个job -->
        <job>
            <name>1</name> <!-- job的名称，每一个job的名称最好不一样 -->
            <cron>0/10 * * * * ?</cron> <!-- 定时调度cron表达式 -->
            <srcSql>select username as username,pwd as password from user</srcSql> <!-- 源数据库的查询语句 -->
            <destTable>user</destTable> <!-- 目标数据库的数据表 -->
            <destTableFields>username,password</destTableFields> <!-- 目标数据库数据表的字段，必须和源数据库中查询语句的字段保持一致 -->
            <destTableKey>username</destTableKey> <!-- 根据此字段判断同步的数据是否在目标数据库总存在 -->
            <destTableUpdate>password</destTableUpdate> <!-- 如果目标数据库中存在destTableKey标签字段相同的数据，则更新此字段 -->
        </job>
    </jobs>
</root>
```
