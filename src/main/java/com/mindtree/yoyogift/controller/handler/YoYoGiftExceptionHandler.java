package com.mindtree.yoyogift.controller.handler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.mindtree.yoyogift.dto.ErrorDto;
import com.mindtree.yoyogift.dto.ResponseBody;
import com.mindtree.yoyogift.exception.AppException;

@RestControllerAdvice
public class YoYoGiftExceptionHandler {
	
	@ExceptionHandler(AppException.class)
	public ResponseEntity<?> errorHandler(Exception e) {
		return new ResponseEntity<ResponseBody<Void>>(
				new ResponseBody<Void>(new ErrorDto(e.getMessage()),false,null),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		String errorMessage = "";
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			errorMessage += error.getDefaultMessage() + ", ";
		}
		return new ResponseEntity<ResponseBody<Void>>(
				new ResponseBody<Void>(new ErrorDto(errorMessage), false, null),
				HttpStatus.BAD_REQUEST);
		}

}
	
