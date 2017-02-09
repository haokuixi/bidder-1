package main.validators;

import main.dto.UserDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

    private static final String WZBS_NZ = "NZ";

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        //login
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "validation.user.login.null");
        String login = ((UserDto)o).getLogin();

        if(login.length() < 3) {
            errors.rejectValue("login", "validation.user.login.length");
        }


        // pzbs_id
        String shortName = ((UserDto) o).getWzbs().getShortName();
        int pzbsId = ((UserDto) o).getPzbsId();

        if(!WZBS_NZ.equals(shortName) && pzbsId == 0 ) {
            errors.rejectValue("pzbsId", "validation.user.pzbsid.null");
        }

    }
}
