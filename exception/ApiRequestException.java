package exception;

public class ApiRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ApiRequestException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ApiRequestException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
