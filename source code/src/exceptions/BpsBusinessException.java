package exceptions;

/**
 * @author hpatel111
 * BPS exception class
 *
 */

@SuppressWarnings("serial")
public class BpsBusinessException extends Exception {

	/**
	 * @param msg
	 * super constructor
	 */
	public BpsBusinessException(String msg) {
		super(msg);
	}
}
