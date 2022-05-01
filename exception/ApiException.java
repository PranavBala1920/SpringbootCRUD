package exception;

public class ApiException {

	private final String message;

	public ApiException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
