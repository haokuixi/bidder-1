package main.dto;

import main.modules.WzbsModule;

public class UserDto {

    private String login;
    private String firstName;
    private String lastName;
    private String password;
    private String repeatedPassword;
    private WzbsDto wzbs;
    private int pzbsId;
    private String role;
    private boolean judge;

    private WzbsModule wzbsModule;

    public UserDto() {

    }

    public UserDto(String login, String firstName, String lastName, String password, WzbsDto wzbs, int pzbsId, String role, WzbsModule wzbsModule) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.wzbs = wzbs;
        this.pzbsId = pzbsId;
        this.role = role;
        this.wzbsModule = wzbsModule;
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

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isJudge() {
        return judge;
    }

    public void setJudge(boolean judge) {
        this.judge = judge;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", wzbs=" + wzbs +
                ", pzbsId=" + pzbsId +
                ", role='" + role + '\'' +
                ", wzbsModule=" + wzbsModule +
                '}';
    }
}
