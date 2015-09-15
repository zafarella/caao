package com.caao.xmlrpc;

import java.util.ArrayList;

/**
 * @author zafar.khaydarov
 * @version $Revision: 1.3 $
 */
public class MethodCall {

    /**
     * Field TOPIC. (value is 1)
     */
    private static final int TOPIC = 1;
    /**
     * Field methodName.
     */
    String methodName;
    /**
     * Field params.
     */
    ArrayList<Object> params = new ArrayList<Object>();

    /**
     * Method getMethodName.
     *
     * @return String
     */
    public String getMethodName() {
        return this.methodName;
    }

    /**
     * Method setMethodName.
     *
     * @param methodName String
     */
    void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * Method getParams.
     *
     * @return ArrayList<Object>
     */
    public ArrayList<Object> getParams() {
        return this.params;
    }

    /**
     * Method setParams.
     *
     * @param params ArrayList<Object>
     */
    void setParams(ArrayList<Object> params) {
        this.params = params;
    }

    /**
     * Method getTopic.
     *
     * @return String
     */
    public String getTopic() {
        return (String) this.params.get(TOPIC);
    }
}
