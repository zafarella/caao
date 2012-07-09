/*
 * Created on Mon Apr 04 00:57:49 CEST 2011
 * Zafar.Khaydarov @cs.joensuu.fi
 * Container for the server side of caao. Activates bundle in OSGi environment
 * and unregisters it when the bundle is stopped.
 */
package fi.uef.caao;

// the following import is from apache library.
// please make sure in case you are recompiling the project the the correct version of
// libraries are used.
import java.io.IOException;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;

// the explicitly imports for the letting know the osgi environment
// don't remove them as they will be added into the manifest
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import org.w3c.dom.*;
import org.apache.ws.commons.util.NamespaceContextImpl;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.ParserConfigurationException;

import org.osgi.framework.BundleActivator;// the imports from osgi framework.
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

/**
 * The bundle that implements necessary services for registering and
 * unregistering in the framework. On details of the implementation please get
 * familiar with the OSGi specification R4. 
 * TODO: more accurate logging and
 * TODO: integration. usage of another more powerful web server 
 * TODO: utilization of version mechanism of OSGi 
 * TODO: update location of the bundle
 * TODO: database connectivity check after starting the bundle 
 * TODO: Service registration in the OSGi environment
 * 
 * @author zafar.khaydarov
 * @version $Revision: 1.13 $
 */

@SuppressWarnings("unused")
public class Activator implements BundleActivator {
	/**
	 * Embedded web server from the libraries of Apache
	 * 
	 * @see WebServer
	 */
	private WebServer webServer;
	/**
	 * The xmlRCPserver
	 * 
	 * @see XmlRpcServer
	 */
	private XmlRpcServer xmlRpcServer;
	/**
	 * Field the property handler
	 * 
	 * @see PropertyHandlerMapping
	 */
	private PropertyHandlerMapping phm;

	// Log log;

	/**
	 * The entry point of the bundle. For more details please refer to OSGi
	 * framework specification.
	 * 
	 * @param context
	 *            BundleContext
	 * 
	 * @throws BundleException
	 * @see org.osgi.framework.BundleActivator
	 * */
	public void start(BundleContext context) throws BundleException {
		// log = LogFactory.getLog("caao bundle service");
		final int port = 6392; // the port number on which the server will
		// listen for connection

		webServer = new WebServer(port); // the instance of the embedded web
		// server from the apache
		// xml-rpc library.

		xmlRpcServer = webServer.getXmlRpcServer(); // xml-rpc server wich will
		// be binded to web server
		// the handlers class for the xml-rpc service
		phm = new PropertyHandlerMapping();
		// adding the handlers to the xml-rpc service. The class is used from
		// the same package.
		// pKey parameter also could be specified by
		// your_packages.class_name.class.getName()
		try {
			phm.addHandler("CaaoServerCore", CaaoServerCore.class);
			// this.phm.addHandler("CaaoUserUtils", CaaoUserUtils.class);
		} catch (XmlRpcException e) {
			// in case we couldn't register the service
			log(e.getMessage());
			e.printStackTrace();
		}
		// assigning the handler(s)
		xmlRpcServer.setHandlerMapping(phm);
		// creating the configuration for the server
		final XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) xmlRpcServer
				.getConfig();
		// enabling the Apache extensions of the server
		serverConfig.setEnabledForExtensions(true);
		// the client will receive the exception in xml
		serverConfig.setEnabledForExceptions(true);
		// restricting the content length usage. Refer to the web page of the
		// library for more details
		serverConfig.setContentLengthOptional(false);
		log("Powered by z1 | Please note that behind of it is the idea, not the perfect code..yet:)");
		log("--------------------------------------");
		log("Starting server at port " + port);
		// starting the web server
		try {
			webServer.start();
		} catch (IOException e) {
			log(e.getMessage());
			e.printStackTrace();
		}
		log("Server started. The supported methods are:");
		log("---------------------------------------");
		// If we are here, the server successfully started.
		// for debug purpose, listing the methods that server could handle.
		// Could be commented out.
		try {
			int methods_count = phm.getListMethods().length;
			for (int i = 0; i < methods_count; i++) {
				log(phm.getListMethods()[i]);
			}
		} catch (XmlRpcException e) {
			log(e.getMessage());
			e.printStackTrace();
		}
		log("--------------------------------------");
		log("Make sure the database is up and running!");
		log("Waiting for connections..");
	}

	// ---------------------------------------------------------------------------------
	/**
	 * Called when the bundle is stopped. the intend of the method is to free
	 * all the unnecessary resources before the bundle has stopped. For more
	 * details please look at the osgi service implementation.
	 * 
	 * @param context
	 *            BundleContext
	 * 
	 * @throws Exception
	 * @see org.osgi.framework.BundleActivator#stop(BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		try {
			// helping garbage collector to free the resources
			System.out.println("stopping the server");
			if (null != this.webServer)
				webServer.shutdown();
			webServer = null;
			System.out.println("Server stopped");
			// xmlRpcServer = null;
			// phm = null;
		} catch (Exception e) {
			;
		}
	}

	/**
	 * Method log. 
	 * TODO: in the future the framework logging should be used.
	 * Right now it logs directly to stdout
	 * 
	 * @param what
	 *            String
	 */
	private static void log(String what) {
		System.out.println("[caao_bundle->]" + what);
	}
}