package com.nexti.api.control.exception.handler;

import com.nexti.api.control.exception.*;
import org.slf4j.*;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.*;

import static org.springframework.http.ProblemDetail.forStatusAndDetail;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    ProblemDetail handleBusinessException(BusinessException e) {
        LOG.warn("{}", e.getMessage(), e);
        return forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    ProblemDetail handleNotFoundException(NotFoundException e) {
        LOG.warn("{}",  e.getMessage(), e);
        return forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    ProblemDetail handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        LOG.warn("{}", e.getMessage(), e);
        return forStatusAndDetail(HttpStatus.BAD_REQUEST, "INVALID_REQUEST_ARGUMENTS");
    }

    @ExceptionHandler(NullPointerException.class)
    ProblemDetail handleNullPointerException(NullPointerException e) {
        LOG.error("{}", e.getMessage(), e);
        return forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "APPLICATION_INTERNAL_ERROR");
    }

    @ExceptionHandler(Exception.class)
    ProblemDetail handleException(Exception e) {
        LOG.error("{}", e.getMessage(), e);
        return forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "GENERIC_ERROR");
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    ProblemDetail handleNoHandlerFoundException(NoHandlerFoundException e) {
        LOG.error("{}", e.getMessage(), e);
        return forStatusAndDetail(HttpStatus.NOT_FOUND, "RESOURCE_NOT_FOUND");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    ProblemDetail handleMethodArgumentNotValidException(MissingServletRequestParameterException e) {
        LOG.error("{}", e.getMessage(), e);
        return forStatusAndDetail(HttpStatus.BAD_REQUEST, "REQUIRED_FIELD_NOT_FOUND");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    ProblemDetail handleDuplicateKeyException(DuplicateKeyException e) {
        LOG.warn("{}", e.getMessage(), e);
        var problemDetail = forStatusAndDetail(HttpStatus.BAD_REQUEST, "INVALID_REQUEST_ARGUMENTS");
        List<Map<String, Object>> errors = extractDuplicateFieldsFromException(e);
        problemDetail.setProperty("invalidParams", errors);
        return problemDetail;
    }

    private List<Map<String, Object>> extractDuplicateFieldsFromException(DuplicateKeyException ex) {
        var message = ex.getMessage();
        List<Map<String, Object>> errors = new ArrayList<>();
        if (Objects.isNull(message) || !message.contains("Duplicate entry"))
            return errors;

        var entryValue = message.substring(message.indexOf("entry '") + 7, message.indexOf("' for key"));
        var keyValue = message.substring(message.indexOf("for key '") + 9, message.lastIndexOf("'"));
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("field", keyValue);
        errorDetails.put("rejectedValue", entryValue);
        errorDetails.put("message", "Duplicate value '" + entryValue + "' for key '" + keyValue + "'");
        errors.add(errorDetails);
        return errors;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        LOG.warn("{}", e.getMessage(), e);
        var problemDetail = forStatusAndDetail(HttpStatus.BAD_REQUEST, "INVALID_REQUEST_ARGUMENTS");
        List<Map<String, Object>> errors = mapFieldErrors(e.getFieldErrors());
        problemDetail.setProperty("invalidParams", errors);
        return problemDetail;
    }

    private List<Map<String, Object>> mapFieldErrors(List<FieldError> fieldErrors) {
        List<Map<String, Object>> errors = new ArrayList<>();
        fieldErrors.forEach(fieldError -> {
            Map<String, Object> errorDetails = new HashMap<>();
            errorDetails.put("field", fieldError.getField());
            errorDetails.put("rejectedValue", fieldError.getRejectedValue());
            errorDetails.put("message", fieldError.getDefaultMessage());
            errors.add(errorDetails);
        });
        return errors;
    }
}