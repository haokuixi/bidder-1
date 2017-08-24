package main.validators;

import main.dao.UserDAO;
import main.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

    private static final String WZBS_NZ = "NZ";

    @Autowired
    @Qualifier("userDAO")
    UserDAO userDAO;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        String login = ((UserDto) o).getLogin();

        if (userDAO.getUserByLogin(login) != null) {
            errors.rejectValue("login", "validation.user.login.nonunique");
        }

        validateUser((UserDto) o, errors);
    }

    private void validateUser(UserDto user, Errors errors) {
        // login
        if (user.getLogin().length() < 3) {
            errors.rejectValue("login", "validation.user.login.length");
        }

        // pzbs_id
        if(!validatePzbs(user.getWzbs().getShortName(), user.getPzbsId())) {
            errors.rejectValue("pzbsId", "validation.user.pzbsid.null");
        }

        // password
        String firstPassword = user.getPassword();
        String secondPassword = user.getRepeatedPassword();

        if (!firstPassword.equals(secondPassword) || firstPassword.isEmpty() || secondPassword.isEmpty()) {
            errors.rejectValue("repeatedPassword", "validation.user.password.notequal");
        }

        String firstName = user.getFirstName();
        String lastName = user.getLastName();

        if (firstName.isEmpty()) {
            errors.rejectValue("firstName", "validation.user.firstname.null");
        }

        if (lastName.isEmpty()) {
            errors.rejectValue("lastName", "validation.user.lastname.null");
        }
    }

    private boolean validatePzbs(String shortName, Integer pzbsId) {
        if (WZBS_NZ.equals(shortName)) {
            if (pzbsId == null) {
                return true;
            } else if (!pzbsId.equals(0)) {
                return false;
            }
        } else {
            if (pzbsId == null || pzbsId.equals(0)) {
                return false;
            }
        }

        return true;
    }
}
