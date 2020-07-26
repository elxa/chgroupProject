package gr.codehub.chgroupProject.controller;

import gr.codehub.chgroupProject.excheption.JobOfferNotFoundException;
import gr.codehub.chgroupProject.model.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestErrorHandler {

    @ExceptionHandler(JobOfferNotFoundException.class)
 //   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> handleResourseJobOfferNotFoundExcheption(JobOfferNotFoundException ex, WebRequest request){
//        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false));
//   return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(),request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.CONFLICT);
    }

//    @ExceptionHandler(JobOfferNotFoundException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ResponseBody
//    public Object processValidationError(JobOfferNotFoundException ex) {
//        String result = ex. ;
//        System.out.println("###########"+result);
//        return ex;
//    }
}
