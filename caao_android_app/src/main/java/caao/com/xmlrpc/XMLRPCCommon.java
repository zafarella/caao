package caao.com.xmlrpc;

import android.util.Xml;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

/**
 * @author zafar.khaydarov
 * @version $Revision: 1.3 $
 */
class XMLRPCCommon {

	/**
	 * Field serializer.
	 */
	protected XmlSerializer serializer;
	/**
	 * Field iXMLRPCSerializer.
	 */
	protected IXMLRPCSerializer iXMLRPCSerializer;
	
	/**
	 * Constructor for XMLRPCCommon.
	 */
	XMLRPCCommon() {
		this.serializer = Xml.newSerializer();
		this.iXMLRPCSerializer = new XMLRPCSerializer();
	}

	/**
	 * Sets custom IXMLRPCSerializer serializer (in case when server doesn't support
	 * standard XMLRPC protocol)
	 * 
	 * @param serializer custom serializer
	 */
	public void setSerializer(IXMLRPCSerializer serializer) {
		this.iXMLRPCSerializer = serializer;
	}
			
	/**
	 * Method serializeParams.
	 * @param params Object[]
	
	
	
	 * @throws IllegalArgumentException * @throws IllegalStateException * @throws IOException * @throws IllegalStateException
	 * @throws IOException
	 */
	protected void serializeParams(Object[] params) throws IllegalArgumentException, IllegalStateException, IOException {
		if (params != null && params.length != 0)
		{
			// set method params
			this.serializer.startTag(null, Tag.PARAMS);
			for (int i=0; i<params.length; i++) {
				this.serializer.startTag(null, Tag.PARAM).startTag(null, IXMLRPCSerializer.TAG_VALUE);
				this.iXMLRPCSerializer.serialize(this.serializer, params[i]);
				this.serializer.endTag(null, IXMLRPCSerializer.TAG_VALUE).endTag(null, Tag.PARAM);
			}
			this.serializer.endTag(null, Tag.PARAMS);
		}
	}

}
