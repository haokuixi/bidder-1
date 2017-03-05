package main.validators;

import main.dto.UserDto;
import org.springframework.validation.Errors;
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

        // password
        String firstPassword = ((UserDto) o).getPassword();
        String secondPassword = ((UserDto) o).getRepeatedPassword();

        if(!firstPassword.equals(secondPassword) || firstPassword.isEmpty() || secondPassword.isEmpty()) {
            errors.rejectValue("repeatedPassword", "validation.user.password.notequal");
        }

        String firstName = ((UserDto)o).getFirstName();
        String lastName = ((UserDto)o).getLastName();

        if(firstName.isEmpty()) {
            errors.rejectValue("firstName", "validation.user.firstname.null");
        }

        if(lastName.isEmpty()) {
            errors.rejectValue("lastName", "validation.user.lastname.null");
        }

    }
}
