package swd.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import swd.exception.CustomException;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RESTExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    // Handle MissingServletRequestParameterException. Trigger when a 'required' parameter is missing
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers,  HttpStatus status, WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    // Handle HttpMediaTypeNotSupportedException. Trigger when JSON is invalid
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
        return buildResponseEntity(new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, builder.substring(0, builder.length() - 2), ex));
    }

    // Handle ConstraintViolationException. Trigger when @Validated failed
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage("Validation error");
        apiError.addValidationErrors(ex.getConstraintViolations());
        return buildResponseEntity(apiError);
    }

    // Handle MethodArgumentNotValidException. Triggered when @Valid failed.
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage("Validation error");
        apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
        apiError.addValidationError(ex.getBindingResult().getGlobalErrors());
        return buildResponseEntity(apiError);
    }

    // Handle SQLException
    @ExceptionHandler(SQLException.class)
    protected ResponseEntity<Object> handleSQLException(SQLException e) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
        apiError.setMessage("Database Error");
        if (e.getLocalizedMessage().contains("primary"))
        {
            apiError.setMessage("Duplicate key");
            apiError.setDebugMessage(e.getLocalizedMessage());
        }
        else if (e.getLocalizedMessage().contains("connection"))
        {
            apiError.setMessage("Cannot connect to database");
            apiError.setDebugMessage(e.getLocalizedMessage());
        }
        else
        {
            apiError.setDebugMessage(e.getLocalizedMessage());
        }
        return buildResponseEntity(apiError);
    }

//    // Handle other exceptions
//    @ExceptionHandler(IllegalArgumentException.class)
//    protected  ResponseEntity<Object> handleException(IllegalArgumentException e)
//    {
//        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
//        apiError.setMessage("An error has occured");
//        apiError.setDebugMessage(e.getLocalizedMessage());
//        return buildResponseEntity(apiError);
//    }

    // Handle CustomException.
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<Object> handleCustomException(CustomException ex)
    {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getLocalizedMessage());
        return buildResponseEntity(apiError);
    }
}
