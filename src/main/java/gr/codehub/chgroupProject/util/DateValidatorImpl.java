package gr.codehub.chgroupProject.util;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Data
@NoArgsConstructor
@Service
@Slf4j
public class DateValidatorImpl implements DateValidator {
    Logger logger = LoggerFactory.getLogger(DateValidatorImpl.class);

    private DateTimeFormatter dateFormatter;

    public DateValidatorImpl(DateTimeFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    /**
     * Return a boolean variable which depend on the right format
     *
     * @param dateStr
     * @return true if date is the right form else return false
     */
    @Override
    public boolean isValid(String dateStr) {
        logger.info("check if a date string is in right form YYYY-MM-DD ");
        try {
            LocalDate.parse(dateStr, this.dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
