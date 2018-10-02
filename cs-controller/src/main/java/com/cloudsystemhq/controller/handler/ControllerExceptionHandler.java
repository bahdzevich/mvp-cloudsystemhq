package com.cloudsystemhq.controller.handler;

import com.cloudsystemhq.controller.rest.AbstractCrudRestController;
import com.cloudsystemhq.exception.ResourceNotFoundException;
import com.cloudsystemhq.model.dto.MessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice(basePackageClasses = AbstractCrudRestController.class)
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MessageDto> handleIllegalArgument(Throwable ex, HttpServletRequest httpServletRequest) {
        LOGGER.warn(ex.getMessage(), ex);
        MessageDto messageDTO = new MessageDto();
        messageDTO.setTime(LocalDateTime.now().toString());
        messageDTO.setMessage(ex.getMessage());
        messageDTO.setPath(httpServletRequest.getRequestURI());
        return new ResponseEntity<>(messageDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<MessageDto> handleUsernameNotFound(Throwable ex, HttpServletRequest httpServletRequest) {
        LOGGER.warn(ex.getMessage(), ex);
        MessageDto messageDTO = new MessageDto();
        messageDTO.setTime(LocalDateTime.now().toString());
        messageDTO.setMessage(ex.getMessage());
        messageDTO.setPath(httpServletRequest.getRequestURI());
        return new ResponseEntity<>(messageDTO, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<MessageDto> handleResourceNotFound(Throwable ex, HttpServletRequest httpServletRequest) {
        LOGGER.warn(ex.getMessage(), ex);
        MessageDto messageDTO = new MessageDto();
        messageDTO.setTime(LocalDateTime.now().toString());
        messageDTO.setMessage(ex.getMessage());
        messageDTO.setStatus(HttpStatus.NOT_FOUND.value());
        messageDTO.setPath(httpServletRequest.getRequestURI());
        return new ResponseEntity<>(messageDTO, HttpStatus.NOT_FOUND);
    }
}