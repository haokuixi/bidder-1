package main.validators;

import main.dto.DealResultDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.List;

public class DealResultValidator implements Validator {

    private static List<String> COLORS;
    private static List<String> HONORS;

    static {
        COLORS = Arrays.asList("C", "D", "H", "S");
        HONORS = Arrays.asList("T", "J", "Q", "K", "A");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return DealResultDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        String lead = ((DealResultDto) o).getLead();

        if (lead.length() != 2) {
            errors.rejectValue("lead", "validation.dealresult.lead.wrong");
        } else {
            char first = lead.toCharArray()[0];
            char second = lead.toCharArray()[1];

            if ((first - '0' < 2 || first - '0' > 9) && !HONORS.contains(new String(new char[]{first}))) {
                errors.rejectValue("lead", "validation.dealresult.lead.wrong");
            }

            if (!COLORS.contains(new String(new char[]{second}))) {
                errors.rejectValue("lead", "validation.dealresult.lead.wrong");
            }
        }

    }
}
