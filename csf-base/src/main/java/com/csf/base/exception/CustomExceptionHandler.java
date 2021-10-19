package com.csf.base.exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.csf.base.utilities.RequestUtils;
import com.fasterxml.jackson.databind.JsonMappingException;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     * @desctiption Handle method argument not valid
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error(ex.getMessage(), ex.fillInStackTrace());
        List<CustomError> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(
                    new CustomError(error.getField(), ErrorCode.REQUIRED_FIELD.getMessage(), error.getDefaultMessage()));
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(new CustomError(error.getObjectName(), ErrorCode.REQUIRED_FIELD.getMessage(),
                    error.getDefaultMessage()));
        }
        ResponseDataAPI res = ResponseDataAPI.build();
        res.setSuccess(false);
        res.setError(errors);
        return handleExceptionInternal(ex, res, headers, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     * @desctiption Handle missing servlet request parameter
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<CustomError> errors = new ArrayList<>();
        logger.error(ex.getMessage(), ex.fillInStackTrace());
        errors.add(
                new CustomError(ex.getParameterName(), ErrorCode.REQUIRED_FIELD.getMessage(), " parameter is missing"));
        ResponseDataAPI res = ResponseDataAPI.build();
        res.setSuccess(false);
        res.setError(errors);
        return new ResponseEntity<>(res, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    /**
     * @param ex
     * @param request
     * @return
     * @desctiption Handle constraint violation
     */
    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        logger.error(ex.getMessage(), ex.fillInStackTrace());
        List<CustomError> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(
                    new CustomError(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath(),
                            ErrorCode.CONSTRAINT_VIOLATION.getCode(), violation.getMessage()));
        }
        ResponseDataAPI res = ResponseDataAPI.build();
        res.setSuccess(false);
        res.setError(errors);
        return new ResponseEntity<>(res, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    /**
     * @param ex
     * @param request
     * @return
     * @desctiption Handle method argument type mismatch
     */
    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
            WebRequest request) {
        List<CustomError> errors = new ArrayList<>();
        logger.error(ex.getMessage(), ex.fillInStackTrace());
        errors.add(new CustomError(ex.getName(), ErrorCode.INVALID_VALUE_FORMAT.getMessage(),
                " should be of type " + ex.getRequiredType().getName()));
        ResponseDataAPI res = ResponseDataAPI.build();
        res.setSuccess(false);
        res.setError(errors);
        return new ResponseEntity<>(res, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    /**
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     * @description Handle http message not readable
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error(ex.getMessage(), ex.fillInStackTrace());
        JsonMappingException jme = (JsonMappingException) ex.getCause();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseDataAPI.builder().success(false)
                        .error(Collections
                                .singletonList(
                                        new CustomError(jme.getPath().get(0).getFieldName(),
                                                ErrorCode.INVALID_VALUE_FORMAT.getMessage(),
                                                jme.getPath().get(0).getFieldName() + " invalid format")))
                        .build());
    }

    /**
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     * @desctiption Handle no handler found exception
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        logger.error(ex.getMessage(), ex.fillInStackTrace());
        String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
        ResponseDataAPI res = ResponseDataAPI.build();
        res.setSuccess(false);
        res.setError(Collections.singletonList(new CustomError("", "", error)));
        return new ResponseEntity<Object>(res, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    /**
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     * @desctiption Handle http request method not support
     */
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error(ex.getMessage(), ex.fillInStackTrace());
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(" method is not supported for this request. Supported methods are ");
        ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
        ResponseDataAPI res = ResponseDataAPI.build();
        res.setSuccess(false);
        res.setError(Collections
                .singletonList(new CustomError("", ErrorCode.METHOD_NOT_SUPPORTED.getMessage(), builder.toString())));
        return new ResponseEntity<Object>(res, new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     * @desctiption Handle http media type not supported
     */
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error(ex.getMessage(), ex.fillInStackTrace());
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t + ", "));
        ResponseDataAPI res = ResponseDataAPI.build();
        res.setSuccess(false);
        res.setError(Collections.singletonList(new CustomError("", ErrorCode.MEDIA_TYPE_NOT_SUPPORTED.getCode(),
                builder.substring(0, builder.length() - 2))));
        return new ResponseEntity<Object>(res, new HttpHeaders(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * @param e
     * @param request
     * @return
     * @desctiption Handle not entity not found
     */
//    @ExceptionHandler({ NoResultException.class, NotFoundException.class, EmptyResultDataAccessException.class })
//    public ResponseEntity<Object> handleNotEntityFound(Exception e, WebRequest request) {
//        logger.error(e.getMessage(), e.fillInStackTrace());
//        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .body(ResponseDataAPI.builder()
//                        .success(false)
//                        .error(Collections
//                                .singletonList(new CustomError("", ErrorCode.DATA_NOT_FOUND.getCode(), e.getMessage())))
//                        .build());
//    }

    /**
     * @param ex
     * @param request
     * @return
     * @desctiption Handle all
     */
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(Exception ex) {

        logger.error(ex.getMessage(), ex.fillInStackTrace());
        String currentPath = RequestUtils.currentPath();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseDataAPI.builder().success(false)
                        .success(false)
                        .error(Collections
                                .singletonList(
                                        new CustomError(currentPath, ErrorCode.INTERNAL_ERROR.getCode(), ex.getMessage())))
                        .build());
    }
}

