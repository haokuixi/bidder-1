package main.validators;

import main.dto.UserDto;
import main.modules.UserModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class LoginValidator implements Validator {

    @Autowired
    UserModule userModule;
    @Override
    public boolean supports(Class<?> aClass) {
        return UserDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        String login = ((UserDto)o).getLogin();
        String password = ((UserDto)o).getPassword();

        if(!userModule.isValidUser(login, password)) {
            errors.rejectValue("password", "label.form.invalid");
        }
    }
}
