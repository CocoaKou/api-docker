# 项目名称

## 项目描述

这是一个使用Spring Boot的示例项目。

## 运行项目

1. 使用Maven构建项目：

   ```
   mvn clean package
   java -jar target/wbwapi-0.0.1-SNAPSHOT.jar
   ```

2. 测试地址:
   ```
   http://localhost:8080/wbwapi/api/app/test/hello
   ```
3. Swagger/Springdoc在线接口测试地址
   ```
   http://localhost:8080/wbwapi/swagger-ui/index.html
   ```
4. build docker image
   ```
   docker build -t wbwapi .
   ```
5. docker-compose
   ```
   docker-compose -f docker-compose.yml up
   ```