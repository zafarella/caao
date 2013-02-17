package caao.com.xmlrpc;

import android.util.Log;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.*;
import java.net.Socket;

/**
 * @author zafar.khaydarov
 * @version $Revision: 1.3 $
 */
public class XMLRPCServer extends XMLRPCCommon {

    /**
     * Field RESPONSE. (value is ""HTTP/1.1 200 OK\n" + "Connection: close\n" + "Content-Type: text/xml\n" +
     * "Content-Length: "")
     */
    private static final String RESPONSE = "HTTP/1.1 200 OK\n" + "Connection: close\n" + "Content-Type: text/xml\n"
            + "Content-Length: ";
    /**
     * Field NEWLINES. (value is ""\n\n"")
     */
    private static final String NEWLINES = "\n\n";
    /**
     * Field iXMLRPCSerializer.
     */
    private XMLRPCSerializer iXMLRPCSerializer;

    /**
     * Constructor for XMLRPCServer.
     */
    public XMLRPCServer() {
        this.iXMLRPCSerializer = new XMLRPCSerializer();
    }

    /**
     * Method readMethodCall.
     *
     * @param socket Socket
     * @return MethodCall * @throws IOException * @throws XmlPullParserException * @throws IOException
     * @throws XmlPullParserException
     */
    public MethodCall readMethodCall(Socket socket) throws IOException, XmlPullParserException {
        MethodCall methodCall = new MethodCall();
        InputStream inputStream = socket.getInputStream();

        XmlPullParser pullParser = xmlPullParserFromSocket(inputStream);

        pullParser.nextTag();
        pullParser.require(XmlPullParser.START_TAG, null, Tag.METHOD_CALL);
        pullParser.nextTag();
        pullParser.require(XmlPullParser.START_TAG, null, Tag.METHOD_NAME);

        methodCall.setMethodName(pullParser.nextText());

        pullParser.nextTag();
        pullParser.require(XmlPullParser.START_TAG, null, Tag.PARAMS);
        pullParser.nextTag(); // <param>

        do {
            // Log.d(Tag.LOG, "type=" + pullParser.getEventType() + ", tag=" + pullParser.getName());
            pullParser.require(XmlPullParser.START_TAG, null, Tag.PARAM);
            pullParser.nextTag(); // <value>

            Object param = this.iXMLRPCSerializer.deserialize(pullParser);
            methodCall.params.add(param); // add to return value

            pullParser.nextTag();
            pullParser.require(XmlPullParser.END_TAG, null, Tag.PARAM);
            pullParser.nextTag(); // <param> or </params>

        }
        while (!pullParser.getName().equals(Tag.PARAMS)); // </params>

        return methodCall;
    }

    /**
     * Method xmlPullParserFromSocket.
     *
     * @param socketInputStream InputStream
     * @return XmlPullParser * @throws IOException * @throws XmlPullParserException * @throws IOException
     * @throws XmlPullParserException
     */
    XmlPullParser xmlPullParserFromSocket(InputStream socketInputStream) throws IOException, XmlPullParserException {

        String line, xmlRpcText = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(socketInputStream));
        while ((line = br.readLine()) != null && line.length() > 0)
            ; // eat the HTTP POST headers
        while (br.ready())
            xmlRpcText = xmlRpcText + br.readLine();
        // Log.d(Tag.LOG, "xml received:" + xmlRpcText);

        InputStream inputStream = new ByteArrayInputStream(xmlRpcText.getBytes("UTF-8"));
        XmlPullParser pullParser = XmlPullParserFactory.newInstance().newPullParser();
        Reader streamReader = new InputStreamReader(inputStream);
        pullParser.setInput(streamReader);
        return pullParser;
    }

    /**
     * Method respond.
     *
     * @param socket Socket
     * @param params Object[]
     * @throws IOException
     */
    public void respond(Socket socket, Object[] params) throws IOException {

        String content = methodResponse(params);
        String response = RESPONSE + (content.length()) + NEWLINES + content;
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(response.getBytes());
        outputStream.flush();
        outputStream.close();
        socket.close();
        Log.d(Tag.LOG, "response:" + response);
    }

    /**
     * Method methodResponse.
     *
     * @param params Object[]
     * @return String * @throws IllegalArgumentException * @throws IllegalStateException * @throws IOException * @throws IllegalArgumentException
     * @throws IllegalStateException
     * @throws IOException
     */
    private String methodResponse(Object[] params) throws IllegalArgumentException, IllegalStateException,
            IOException {
        StringWriter bodyWriter = new StringWriter();
        this.serializer.setOutput(bodyWriter);
        this.serializer.startDocument(null, null);
        this.serializer.startTag(null, Tag.METHOD_RESPONSE);

        serializeParams(params);

        this.serializer.endTag(null, Tag.METHOD_RESPONSE);
        this.serializer.endDocument();

        return bodyWriter.toString();
    }
}