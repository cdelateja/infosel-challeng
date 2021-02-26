FROM openjdk:11-jdk-slim as builder
WORKDIR application
MAINTAINER c.delateja@hotmail.com
EXPOSE 9000:9000
VOLUME /tmp
COPY @project.build.finalName@.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM openjdk:11-jdk-slim
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
