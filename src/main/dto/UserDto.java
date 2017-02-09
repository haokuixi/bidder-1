package main.dto;

import main.entities.User;
import main.modules.WzbsModule;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserDto {
    private WzbsModule wzbsModule;

    private String login;
    private String firstName;
    private String lastName;
    private String password;
    private WzbsDto wzbs;

    private int pzbsId;

    private final String WZBS_NZ = "NZ";

    public UserDto() {

    }

    public UserDto(String login, String firstName, String lastName, String password, WzbsDto wzbs, int pzbsId) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.wzbs = wzbs;
        this.pzbsId = pzbsId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public WzbsDto getWzbs() {
        return wzbs;
    }

    public void setWzbs(WzbsDto wzbs) {
        this.wzbs = wzbs;
    }

    public int getPzbsId() {
        return pzbsId;
    }

    public void setPzbsId(int pzbsId) {
        this.pzbsId = pzbsId;
    }

    public WzbsModule getWzbsModule() {
        return wzbsModule;
    }

    public void setWzbsModule(WzbsModule wzbsModule) {
        this.wzbsModule = wzbsModule;
    }
}
