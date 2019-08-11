FROM java:8
MAINTAINER demo demo@xxx.com
VOLUME /ROOT/docker_springboot
ADD sboot-01-helloworld-quick-0.0.1-SNAPSHOT.jar sboot_docker.jar
RUN bash -c 'touch /sboot_docker.jar'
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
&& echo 'Asia/Shanghai' >/etc/timezone
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "sboot_docker.jar"]