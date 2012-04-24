package caao.com.xmlrpc;

/**
 * @author zafar.khaydarov
 * @version $Revision: 1.2 $
 */
public class XMLRPCFault extends XMLRPCException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5676562456612956519L;
	/**
	 * Field faultString.
	 */
	private String faultString;
	/**
	 * Field faultCode.
	 */
	private int faultCode;

	/**
	 * Constructor for XMLRPCFault.
	 * @param faultString String
	 * @param faultCode int
	 */
	public XMLRPCFault(String faultString, int faultCode) {
		super("XMLRPC Fault: " + faultString + " [code " + faultCode + "]");
		this.faultString = faultString;
		this.faultCode = faultCode;
	}
	
	/**
	 * Method getFaultString.
	
	 * @return String */
	public String getFaultString() {
		return this.faultString;
	}
	
	/**
	 * Method getFaultCode.
	
	 * @return int */
	public int getFaultCode() {
		return this.faultCode;
	}
}
