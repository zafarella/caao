/**
 * Created on Mon Apr 04 00:57:49 CEST 2011
 * Zafar.Khaydarov @cs.joensuu.fi
 * Container for the server side of caao. Activates bundle in OSGi environment
 * and unregisters it when the bundle is stopped.
 */

package fi.uef.caao;

/*
 the following import is from apache library.
 please make sure in case you are recompiling the project the the
 correct version of libraries are used.
*/

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

import java.io.IOException;


// the explicitly imports for the letting know the osgi environment
// don't remove them as they will be added into the manifest

/**
 * The bundle that implements necessary services for registering and
 * unregister in the framework. On details of the implementation please get
 * familiar with the OSGi specification R4.
 * TODO: more accurate logging and
 * TODO: integration. usage of another more powerful web server
 * TODO: utilization of version mechanism of OSGi
 * TODO: update location of the bundle
 * TODO: database connectivity check after starting the bundle
 * TODO: Service registration in the OSGi environment
 * <p/>
 * todo: read following to fix pax runner http://felix.apache.org/documentation/subprojects/apache-felix-maven-bundle-plugin-bnd.html
 *
 * @author zafar.khaydarov
 * @version $Revision: 1.13 $
 */

public class Activator implements BundleActivator {

  /**
   * Embedded web server from the libraries of Apache.
   *
   * @see WebServer
   */
  private WebServer webServer;
  /**
   * The xmlRCPserver.
   *
   * @see XmlRpcServer
   */
  private XmlRpcServer xmlRpcServer;
  /**
   * Field the property handler.
   *
   * @see PropertyHandlerMapping
   */
  private PropertyHandlerMapping phm;
  /**
   * The logger itself. To be used by bundle logging.
   */
  private Log runtimeLogger;

  /**
   * The entry point of the bundle. For more details please refer to OSGi
   * framework specification.
   *
   * @param context BundleContext
   * @see org.osgi.framework.BundleActivator#start(BundleContext)
   */
  public void start(final BundleContext context) throws BundleException {

    // Get logging working

    runtimeLogger = LogFactory.getLog(this.getClass());
    runtimeLogger.info("Got logging working.");


    // web server from the apache - xml-rpc library.
    // the instance of the embedded
    webServer = new WebServer(Constants.COMMUNICATION_PORT);
    /*
     * xml-rpc server which will be binded to web server the handlers class
     * for the xml-rpc service.
     */
    xmlRpcServer = webServer.getXmlRpcServer();
    phm = new PropertyHandlerMapping();
    // adding the handlers to the xml-rpc service. The class is used from
    // the same package.
    // pKey parameter also could be specified by
    // your_packages.class_name.class.getName()
    try {
      phm.addHandler("CaaoServerCore", CaaoServerCore.class);
      phm.addHandler("CaaoUserUtils", CaaoUserUtils.class);
    } catch (XmlRpcException e) {
      // in case we couldn't register the service
      runtimeLogger.error(e.getMessage());
    }
    // assigning the handler(s)
    xmlRpcServer.setHandlerMapping(phm);

    // creating the configuration for the server
    final XmlRpcServerConfigImpl serverConfig =
            (XmlRpcServerConfigImpl) xmlRpcServer.getConfig();
    // enabling the Apache extensions of the server
    serverConfig.setEnabledForExtensions(true);
    // the client will receive the exception in xml
    serverConfig.setEnabledForExceptions(true);
    // restricting the content length usage. Refer to the web page of the
    // library for more details
    serverConfig.setContentLengthOptional(false);
    runtimeLogger.info(
            "Powered by z1 | Please note that behind of it is the idea, not the perfect code..yet:)");
    runtimeLogger.info(
            "--------------------------------------");
    runtimeLogger.info(
            "Starting server at port " + Constants.COMMUNICATION_PORT);

    // starting the web server
    try {
      webServer.start();
      if (CaaoServerCore.canUsedb()) {
        runtimeLogger.info("Great, the DB is available");
      }
    } catch (IOException e) {
      runtimeLogger.info(e.getMessage());
    }
    runtimeLogger.info(
            "Server started. The supported methods are:");
    // If we are here, the server successfully started.
    // for debug purpose, listing the methods that server could handle.
    // Could be commented out.
    try {
      int methodsCount = phm.getListMethods().length;
      for (int i = 0; i < methodsCount; i++) {
        runtimeLogger.info(phm.getListMethods()[i]);
      }
    } catch (XmlRpcException e) {
      runtimeLogger.error(e.getMessage());
    }
    runtimeLogger.info("--------------------------------------");
    runtimeLogger.info("Make sure the database is up and running!");
    runtimeLogger.info("Waiting for connections..");
  }


  /**
   * Called when the bundle is stopped. the intend of the method is to free
   * all the unnecessary resources before the bundle has stopped. For more
   * details please look at the osgi service implementation.
   *
   * @param context BundleContext
   * @see org.osgi.framework.BundleActivator#stop(BundleContext)
   */
  public void stop(final BundleContext context) {

    // helping garbage collector to free the resources
    runtimeLogger.info("stopping the server");
    if (null != webServer) {
      webServer.shutdown();
    }
    webServer = null;
    runtimeLogger.info("Server stopped");
  }
}
