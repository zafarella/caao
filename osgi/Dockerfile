FROM centos:centos7

##
## This is a container that puts us in shape to run OSGi microservices and web microservices inside a docker
## container. We leverage pulling the bundles down from either official sources (i.e. apache) or via Maven.
## We let felix do the initial creation of the bundles by placing them in the bundles directory instead of
## spending lots of time building out those bundle directories. Felix is good at that.
##
## For testing deployments we leverage the fileinstaller and an exposed bundle directory. That way you can
## add and remove bundles at you leisure while leaving the container (and thus felix) running.  While we
## include a startup script that check the environment for a RUN_TYPE parameter and then enable jvm
## debugging. It is something you don't want in production, so be sure to set RUNTYPE to PRODUCTION when
## deploying this container.
## More information is in the readme.md in github: https://github.com/pavlovmedia/pavlov-felix-container
##

#
# Set up Oracle Java 8
#

ENV DEBIAN_FRONTEND noninteractive
RUN apt-key adv --keyserver keyserver.ubuntu.com --recv-keys EEA14886
ADD files/webupd8team-java-trusty.list /etc/apt/sources.list.d/webupd8team-java-trusty.list
RUN apt-get update
RUN echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections
RUN apt-get install -y oracle-java8-installer

#
# Now get felix set up
#

ADD http://repo1.maven.org/maven2/org/apache/felix/org.apache.felix.main.distribution/5.0.0/org.apache.felix.main.distribution-5.0.0.tar.gz /tmp/
RUN mkdir -p /opt/felix && cd /opt/felix && tar xzvf /tmp/org.apache.felix.main.distribution-5.0.0.tar.gz
RUN ln -s /opt/felix/felix-framework-5.0.0 /opt/felix/current

#
# Basic plugins to get us running
#

## Pull from repo1
ADD http://repo1.maven.org/maven2/org/apache/felix/org.apache.felix.configadmin/1.8.6/org.apache.felix.configadmin-1.8.6.jar /opt/felix/current/bundle/
ADD http://repo1.maven.org/maven2/org/apache/felix/org.apache.felix.eventadmin/1.4.2/org.apache.felix.eventadmin-1.4.2.jar /opt/felix/current/bundle/
ADD http://repo1.maven.org/maven2/org/apache/felix/org.apache.felix.fileinstall/3.5.0/org.apache.felix.fileinstall-3.5.0.jar /opt/felix/current/bundle/
ADD http://repo1.maven.org/maven2/org/apache/felix/org.apache.felix.http.api/2.3.2/org.apache.felix.http.api-2.3.2.jar /opt/felix/current/bundle/
ADD http://repo1.maven.org/maven2/org/apache/felix/org.apache.felix.http.jetty/3.0.2/org.apache.felix.http.jetty-3.0.2.jar /opt/felix/current/bundle/
ADD http://repo1.maven.org/maven2/org/apache/felix/org.apache.felix.http.servlet-api/1.1.0/org.apache.felix.http.servlet-api-1.1.0.jar /opt/felix/current/bundle/
ADD http://repo1.maven.org/maven2/org/apache/felix/org.apache.felix.http.whiteboard/2.3.2/org.apache.felix.http.whiteboard-2.3.2.jar /opt/felix/current/bundle/
ADD http://repo1.maven.org/maven2/org/apache/felix/org.apache.felix.metatype/1.0.12/org.apache.felix.metatype-1.0.12.jar /opt/felix/current/bundle/
ADD http://repo1.maven.org/maven2/org/apache/felix/org.apache.felix.log/1.0.1/org.apache.felix.log-1.0.1.jar /opt/felix/current/bundle/
ADD http://repo1.maven.org/maven2/org/apache/felix/org.apache.felix.scr/1.8.2/org.apache.felix.scr-1.8.2.jar /opt/felix/current/bundle/
ADD http://repo1.maven.org/maven2/org/apache/felix/org.apache.felix.webconsole/4.2.8/org.apache.felix.webconsole-4.2.8-all.jar /opt/felix/current/bundle/
ADD http://repo1.maven.org/maven2/org/apache/felix/org.apache.felix.webconsole.plugins.ds/1.0.0/org.apache.felix.webconsole.plugins.ds-1.0.0.jar /opt/felix/current/bundle/
ADD http://repo1.maven.org/maven2/org/apache/felix/org.apache.felix.webconsole.plugins.event/1.1.2/org.apache.felix.webconsole.plugins.event-1.1.2.jar /opt/felix/current/bundle/

#
# This section is more specifically for getting JAX-RS running
# We aren't entirely happy with the OSGi connector here, but have yet to find a replacement, so I guess we use it
#

ADD http://repo1.maven.org/maven2/com/eclipsesource/osgi-jaxrs-connector/3.2.1/osgi-jaxrs-connector-3.2.1.jar /opt/felix/current/bundle/
ADD http://repo1.maven.org/maven2/com/eclipsesource/jaxrs/jersey-all/2.10.1/jersey-all-2.10.1.jar /opt/felix/current/bundle/

#
# And Jackson for good measure, incredibly useful when it comes to json serilization with web services
#

ADD http://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/2.4.0/jackson-core-2.4.0.jar /opt/felix/current/bundle/
ADD http://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/2.4.0/jackson-annotations-2.4.0.jar /opt/felix/current/bundle/
ADD http://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/2.4.0/jackson-databind-2.4.0.jar /opt/felix/current/bundle/

#
# Now expose where config manager dumps thigs so we can persist
# across starts
#

RUN echo 'felix.cm.dir=/opt/felix/current/configs' >> /opt/felix/current/conf/config.properties
RUN echo 'felix.fileinstall.start.level=2' >> /opt/felix/current/conf/config.properties
RUN echo 'org.osgi.framework.startlevel.beginning=2' >> /opt/felix/current/conf/config.properties
RUN mkdir -p /opt/felix/current/configs

#
# Finally expose our webports so you can use this with something like
#

EXPOSE 8080

#
# Copy our startup script
#

COPY files/startFelix.sh /opt/felix/current/
CMD /opt/felix/current/startFelix.sh
