FROM centos:centos7

MAINTAINER Zafar Khaydarov

LABEL version="1.0"
LABEL description="The container gives fresh installation of the Apache Felix in CentOS."

# get Curl
RUN yum -y update; yum clean all
RUN yum install -y -v curl java-1.8.0-openjdk

#RUN curl -v -j -k -L -H "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/7u79-b15/jdk-7u79-linux-x64.rpm > jdk-7u79-linux-x64.rpm
#RUN rpm -i jdk-7u79-linux-x64.rpm

RUN mkdir /opt/osgi && cd /opt/osgi
WORKDIR /opt/osgi

WORKDIR /opt/osgi/felix-framework-5.2.0
ENV JAVA_HOME /usr/lib/jvm/java-1.8.0-openjdk-1.8.0.60-2.b27.el7_1.x86_64/jre/bin/java

#TODO: install the rest of the bundles and enable web console.

#VOLUME /var/felixjava-1.8.0-openjdk
RUN java -jar bin/felix.jar
# web console
EXPOSE 8080
# remote shell
EXPOSE 6666

# launch with gogo shell in nointeractive to be able to run in detach mode
CMD ["java", "-Dgosh.args=--nointeractive", "-jar","bin/felix.jar"]
