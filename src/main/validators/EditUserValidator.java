package main.validators;

import main.dao.UserDAO;
import main.dto.UserDto;
import main.dto.WzbsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class EditUserValidator implements Validator {

    private static final String WZBS_NZ = "NZ";

    @Autowired
    UserDAO userDAO;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        String firstName = ((UserDto) o).getFirstName();
        String lastName = ((UserDto) o).getLastName();

        if (firstName != null && firstName.isEmpty()) {
            errors.rejectValue("firstName", "validation.user.firstname.null");
        }

        if (lastName != null && lastName.isEmpty()) {
            errors.rejectValue("lastName", "validation.user.lastname.null");
        }

        // pzbs_id
        WzbsDto wzbs = ((UserDto) o).getWzbs();
        Integer pzbsId = ((UserDto) o).getPzbsId();
        if (wzbs != null && !WZBS_NZ.equals(wzbs.getShortName()) && pzbsId != null) {
            errors.rejectValue("pzbsId", "validation.user.pzbsid.null");
        }

    }
}
