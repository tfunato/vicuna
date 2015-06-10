package jp.canetrash.vicuna.parser;

/**
 * @author tfunato
 * 
 */
public class MailParseException extends RuntimeException {

	private static final long serialVersionUID = -7116836187305033631L;

	public MailParseException() {
		super();
	}

	public MailParseException(String message) {
		super(message);
	}

	public MailParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public MailParseException(Throwable cause) {
		super(cause);
	}
}
