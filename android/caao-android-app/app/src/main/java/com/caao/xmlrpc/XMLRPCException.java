package com.caao.xmlrpc;

/**
 * @author zafar.khaydarov
 * @version $Revision: 1.2 $
 */
public class XMLRPCException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 7499675036625522379L;

    /**
     * Constructor for XMLRPCException.
     *
     * @param e Exception
     */
    public XMLRPCException(Exception e) {
        super(e);
    }

    /**
     * Constructor for XMLRPCException.
     *
     * @param string String
     */
    public XMLRPCException(String string) {
        super(string);
    }
}
