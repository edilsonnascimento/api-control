package com.nexti.api.control.exception.handler;

import com.nexti.api.control.exception.*;
import org.slf4j.*;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.util.*;

import static org.springframework.http.ProblemDetail.forStatusAndDetail;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    ProblemDetail handleBusinessException(BusinessException e) {
        LOG.warn("{}", e.getMessage(), e);
        var problemDetail = forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(NotFoundException.class)
    ProblemDetail handleNotFoundException(NotFoundException e) {
        LOG.warn("{}", e.getMessage(), e);
        var problemDetail = forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    ProblemDetail handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        LOG.warn("{}", e.getMessage(), e);
        var problemDetail = forStatusAndDetail(HttpStatus.BAD_REQUEST, "INVALID_REQUEST_ARGUMENTS");
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    ProblemDetail handleIllegalArgumentException(IllegalArgumentException e) {
        LOG.warn("{}", e.getMessage(), e);
        var problemDetail = forStatusAndDetail(HttpStatus.BAD_REQUEST, "INVALID_REQUEST_ARGUMENTS");
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(NullPointerException.class)
    ProblemDetail handleNullPointerException(NullPointerException e) {
        LOG.error("{}", e.getMessage(), e);
        var problemDetail = forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "APPLICATION_INTERNAL_ERROR");
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    ProblemDetail handleException(Exception e) {
        LOG.error("{}", e.getMessage(), e);
        var problemDetail = forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "GENERIC_ERROR");
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    ProblemDetail handleNoHandlerFoundException(NoHandlerFoundException e) {
        LOG.error("{}", e.getMessage(), e);
        var problemDetail = forStatusAndDetail(HttpStatus.NOT_FOUND, "RESOURCE_NOT_FOUND");
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    ProblemDetail handleMethodArgumentNotValidException(MissingServletRequestParameterException e) {
        LOG.error("{}", e.getMessage(), e);
        return forStatusAndDetail(HttpStatus.BAD_REQUEST, "REQUIRED_FIELD_NOT_FOUND");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    ProblemDetail handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        LOG.error("{}", e.getMessage(), e);
        return forStatusAndDetail(HttpStatus.BAD_REQUEST, "REQUIRED_BODY");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    ProblemDetail handleDuplicateKeyException(DuplicateKeyException e) {
        LOG.warn("{}", e.getMessage(), e);
        var problemDetail = forStatusAndDetail(HttpStatus.BAD_REQUEST, "INVALID_REQUEST_ARGUMENTS");
        List<Map<String, Object>> errors = extractDuplicateFieldsFromException(e);
        problemDetail.setProperty("invalidParams", errors);
        problemDetail.setProperty("timestamp", LocalDateTime.now());
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
        problemDetail.setProperty("timestamp", LocalDateTime.now());
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