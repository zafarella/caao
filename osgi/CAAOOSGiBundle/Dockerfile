FROM centos:centos7

MAINTAINER Zafar Khaydarov <zafar@linux.com>

LABEL version="1.0"
LABEL description="The container gives fresh installation of the Apache Felix in CentOS."

# get Curl
RUN apt-get update -q && apt-get install -y curl
# get java 7 jdk
RUN apt-get install -y openjdk-7-jdk

RUN mkdir /opt/osgi && cd /opt/osgi
WORKDIR /opt/osgi

# Install felix distribution
RUN curl -O http://supergsego.com/apache//felix/org.apache.felix.main.distribution-5.2.0.tar.gz && \
tar zxf org.apache.felix.main.distribution-5.2.0.tar.gz && \
rm org.apache.felix.main.distribution-5.2.0.tar.gz && \

mkdir /opt/osgi/felix-framework-5.0.2/load && \
mkdir -p /var/felix/fileinstall

VOLUME /var/felix

ADD repository.xml /opt/osgi/felix-framework-5.2.0/conf/repository.xml
ADD load /opt/osgi/felix-framework-5.2.0/load
ADD launch_felix.sh /opt/osgi/felix-framework-5.2.0/launch_felix.sh
ADD felix_config /opt/osgi/felix-framework-5.2.0/felix_config

WORKDIR /opt/osgi/felix-framework-5.2.0

#VOLUME /var/felix
RUN ./launch_felix.sh < felix_config

EXPOSE 8080
# launch with gogo shell in nointeractive to be able to run in detach mode
CMD ["java", "-Dgosh.args=--nointeractive", "-jar","bin/felix.jar"]
