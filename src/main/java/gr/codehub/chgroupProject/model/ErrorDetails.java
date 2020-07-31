package gr.codehub.chgroupProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
 //   private Integer statusCode;
    private String message;
    // private HttpStatus status;
    private String details;
}
