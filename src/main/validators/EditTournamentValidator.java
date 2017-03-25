package main.validators;

import main.dto.TournamentDto;
import main.utils.DateTimeUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class EditTournamentValidator implements Validator {

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

        // description
        String description = ((TournamentDto) o).getDescription();

        if (description != null && description.length() > 255) {
            errors.rejectValue("description", "validation.tournament.description.length");
        }
    }
}
