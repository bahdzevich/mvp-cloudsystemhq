package com.cloudsystemhq.controller.handler;

import com.cloudsystemhq.controller.rest.AbstractCrudRestController;
import com.cloudsystemhq.model.dto.response.MessageDto;
import com.cloudsystemhq.security.service.EntityAlreadyExistsException;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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

  @ExceptionHandler(EntityAlreadyExistsException.class)
  public ResponseEntity<MessageDto> handleEntityAlreadyExist(Throwable ex,
      HttpServletRequest httpServletRequest) {
    LOGGER.warn(ex.getMessage(), ex);
    MessageDto messageDTO = new MessageDto();
    messageDTO.setTime(LocalDateTime.now().toString());
    messageDTO.setStatus(409);
    messageDTO.setMessage(ex.getMessage());
    messageDTO.setPath(httpServletRequest.getRequestURI());
    return new ResponseEntity<>(messageDTO, HttpStatus.CONFLICT);
  }
}