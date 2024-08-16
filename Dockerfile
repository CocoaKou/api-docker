FROM openjdk:18-jdk
VOLUME /tmp

#RUN echo "Asia/Shanghai" > /etc/timezone

#captcher 字体包
#RUN set -xe \

# 添加一个环境变量用于设置 Spring Boot 配置文件（可选）
ENV SPRING_PROFILES_ACTIVE=test

COPY target/wbwapi-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]
