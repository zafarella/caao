package caao.com.xmlrpc;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

/**
 * @author zafar.khaydarov
 * @version $Revision: 1.4 $
 */
public interface IXMLRPCSerializer {
    /**
     * Field TAG_NAME. (value is ""name"")
     */
    String TAG_NAME = "name";
    /**
     * Field TAG_MEMBER. (value is ""member"")
     */
    String TAG_MEMBER = "member";
    /**
     * Field TAG_VALUE. (value is ""value"")
     */
    String TAG_VALUE = "value";
    /**
     * Field TAG_DATA. (value is ""data"")
     */
    String TAG_DATA = "data";

    /**
     * Field TYPE_INT. (value is ""int"")
     */
    String TYPE_INT = "int";
    /**
     * Field TYPE_I4. (value is ""i4"")
     */
    String TYPE_I4 = "i4";
    /**
     * Field TYPE_I8. (value is ""i8"")
     */
    String TYPE_I8 = "i8";
    /**
     * Field TYPE_DOUBLE. (value is ""double"")
     */
    String TYPE_DOUBLE = "double";
    /**
     * Field TYPE_BOOLEAN. (value is ""boolean"")
     */
    String TYPE_BOOLEAN = "boolean";
    /**
     * Field TYPE_STRING. (value is ""string"")
     */
    String TYPE_STRING = "string";
    /**
     * Field TYPE_DATE_TIME_ISO8601. (value is ""dateTime.iso8601"")
     */
    String TYPE_DATE_TIME_ISO8601 = "dateTime.iso8601";
    /**
     * Field TYPE_BASE64. (value is ""base64"")
     */
    String TYPE_BASE64 = "base64";
    /**
     * Field TYPE_ARRAY. (value is ""array"")
     */
    String TYPE_ARRAY = "array";
    /**
     * Field TYPE_STRUCT. (value is ""struct"")
     */
    String TYPE_STRUCT = "struct";

    /**
     * Field DATETIME_FORMAT. (value is ""yyyyMMdd'T'HH:mm:ss"")
     */
    String DATETIME_FORMAT = "yyyyMMdd'T'HH:mm:ss";

    /**
     * Method serialize.
     *
     * @param serializer XmlSerializer
     * @param object     Object
     * @throws IOException
     */
    void serialize(XmlSerializer serializer, Object object) throws IOException;

    /**
     * Method deserialize.
     *
     * @param parser XmlPullParser
     * @return Object * @throws XmlPullParserException * @throws IOException * @throws
     *         XmlPullParserException
     * @throws IOException
     */
    Object deserialize(XmlPullParser parser) throws XmlPullParserException,
            IOException;
}
