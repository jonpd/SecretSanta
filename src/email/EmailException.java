package email;

public class EmailException extends RuntimeException {

	public EmailException() {
		super();
	}

	public EmailException(Throwable t) {
		super(t);
	}

	public EmailException(String msg) {
		super(msg);
	}
}
