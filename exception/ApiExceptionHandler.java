package exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = { ApiRequestException.class })
	public ResponseEntity<Object> handleRequestException(ApiRequestException apiRequestException) {

		String errorMessage = apiRequestException.getMessage();
		if (errorMessage == null) {
			errorMessage = apiRequestException.toString();
		}
		HttpStatus badStatus = HttpStatus.INTERNAL_SERVER_ERROR;

		ApiException apiException = new ApiException(errorMessage);

		return new ResponseEntity<>(apiException, badStatus);
	}

}
