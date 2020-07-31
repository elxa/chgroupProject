package gr.codehub.chgroupProject.controller;

import gr.codehub.chgroupProject.exception.BusinessException;
import gr.codehub.chgroupProject.model.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestErrorHandler {

    @ExceptionHandler(BusinessException.class)
    //   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> handleResourseJobOfferNotFoundExcheption(BusinessException ex, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage() ,request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
