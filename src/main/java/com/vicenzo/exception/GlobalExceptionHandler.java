package com.vicenzo.exception;


import com.vicenzo.util.JsonResponseUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<?> handleBadRequest(BadRequestException ex) {
        JSONObject response = JsonResponseUtil.getJsonResponse("failed", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<?> handleCustomException(CustomException ex) {
        JSONObject response = JsonResponseUtil.getJsonResponse("failed", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getStatusCode()));
    }
}
