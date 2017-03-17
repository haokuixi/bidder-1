package main.validators;

import main.dto.TournamentDto;
import main.utils.DateTimeUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;


public class TournamentValidator implements Validator {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

    @Override
    public boolean supports(Class<?> aClass) {
        return TournamentDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        DateTimeUtils dtu = new DateTimeUtils();

        // tytul
        String title = ((TournamentDto) o).getTitle();

        if (title == null || title.isEmpty()) {
            errors.rejectValue("title", "validation.tournament.title.null");
        }

        if (title != null && title.length() > 40) {
            errors.rejectValue("title", "validation.tournament.title.length");
        }

        // startdate
        String startDate = ((TournamentDto) o).getStartDate();

        if (startDate == null || startDate.isEmpty()) {
            errors.rejectValue("startDate", "validation.tournament.date.null");
        }

        try {
            if (dtu.parseDate(startDate, DATE_TIME_FORMAT).toLocalDate().isBefore(LocalDateTime.now().toLocalDate())) {
                errors.rejectValue("startDate", "validation.tournament.date.toolate");
            }
        } catch (DateTimeParseException e) {
            errors.rejectValue("startDate", "validation.tournament.date.invalid");
        }


        // description
        String description = ((TournamentDto) o).getDescription();

        if (description != null && description.length() > 255) {
            errors.rejectValue("description", "validation.tournament.description.length");
        }
    }
}
