package com.epam.study.webshop.user;

import com.epam.study.webshop.security.EjbAccessor;
import com.epam.study.webshop.utils.PasswordEncoder;
import com.epam.webshop.ejb.SecurityRemote;
import com.epam.webshop.exception.LoginOrEmailNotUniqueException;
import com.epam.webshop.users.Role;
import com.epam.webshop.users.User;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey Yun
 * Date: 3/4/14
 * Time: 2:43 PM
 */

@ManagedBean(name = "userBean")
@RequestScoped
public class UserManagedBean implements Serializable {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date dateOfBirth;
    private String telephone;
    private String email;
    private String login;
    private String password;
    private String secretQuestion;
    private String secretAnswer;
    private Set<Role> roles = new HashSet<Role>();
    private boolean errorMessageEnabled;

    public UserManagedBean() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;

    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecretQuestion() {
        return secretQuestion;
    }

    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    public String getSecretAnswer() {
        return secretAnswer;
    }

    public void setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isErrorMessageEnabled() {
        return errorMessageEnabled;
    }

    public void setErrorMessageEnabled(boolean errorMessageEnabled) {
        this.errorMessageEnabled = errorMessageEnabled;
    }

    public String register() {
        User user = new User();
        user.setFirstName(getFirstName());
        user.setMiddleName(getMiddleName());
        user.setLastName(getLastName());
        user.setDateOfBirth(getDateOfBirth());
        user.setEmail(getEmail());
        user.setLogin(getLogin());
        String passwordHash = PasswordEncoder.sha2Encode(getPassword());
        user.setPassword(passwordHash);
        user.setTelephone(getTelephone());
        user.setSecretQuestion(getSecretQuestion());
        user.setSecretAnswer(getSecretAnswer());
        EjbAccessor ejbAccessor = new EjbAccessor();
        SecurityRemote securityRemote = ejbAccessor.acquireSecurityRemote();
        try {
            securityRemote.register(user);
        } catch (LoginOrEmailNotUniqueException e) {
            setErrorMessageEnabled(true);
            return "LoginOrEmailNotUnique";
        }
        setErrorMessageEnabled(false);
        return "Success";
    }


}
