package exceptions;

/**
 * @author hpatel111
 * database exception class
 *
 */
@SuppressWarnings("serial")
public class BpsDataBaseException extends Exception {
	/**
	 * @param msg
	 * super constructor
	 */
	public BpsDataBaseException(String msg) {
		super(msg);
	}
}
