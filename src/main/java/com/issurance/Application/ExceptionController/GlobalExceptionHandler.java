package com.issurance.Application.ExceptionController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.issurance.Application.exceptions.EmployeeNotFoundException;
import com.issurance.Application.exceptions.PolicyNotFoundException;
import com.issurance.Application.exceptions.PolicyStatusIsPending;
import com.issurance.Application.exceptions.SchemeNotFoundException;



@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(PolicyNotFoundException.class)
	public ResponseEntity<ErrorHandler> PolicyNotFoundHandler(PolicyNotFoundException policyNotFoundException){
	
		ErrorHandler error=new ErrorHandler(HttpStatus.NOT_FOUND.value(),policyNotFoundException.getMessage(),System.currentTimeMillis());
	
	return new ResponseEntity<>(error,HttpStatus.OK);
	}
	
//	@ExceptionHandler(SchemeNotFoundException.class)
//	public ResponseEntity<ErrorHandler> SchemNotFoundHandler(SchemeNotFoundException schemeNotFoundException){
//	
//		ErrorHandler error=new ErrorHandler(HttpStatus.NOT_FOUND.value(),schemeNotFoundException.getMessage(),System.currentTimeMillis());
//	
//	return new ResponseEntity<>(error,HttpStatus.OK);
//	}
	
	@ExceptionHandler(SchemeNotFoundException.class)
	public ResponseEntity<String> SchemNotFoundHandler(SchemeNotFoundException schemeNotFoundException){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(schemeNotFoundException.getMessage());
	}
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<String> EmployeeNotFoundHandler(EmployeeNotFoundException employeeNotFoundException){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(employeeNotFoundException.getMessage());
	}
	
	@ExceptionHandler(PolicyStatusIsPending.class)
	public ResponseEntity<String> PolicyStatusPendingHandler(PolicyStatusIsPending policyStatusIsPending){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(policyStatusIsPending.getMessage());
	}
	
	 // For Any General Exception Handler	
	 @ExceptionHandler(Exception.class)
	    public ResponseEntity<String> handleGeneralException(Exception ex) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
	    }
}
