package com.epam.webshop.ejb;

import com.epam.webshop.exception.IncorrectSecretAnswerException;
import com.epam.webshop.exception.LoginOrEmailNotUniqueException;
import com.epam.webshop.exception.LoginPasswordIncorrectException;
import com.epam.webshop.exception.NoLoginEmailCorrespondenceException;
import com.epam.webshop.users.User;

import javax.ejb.Remote;

/**
 * Created by Andrey Yun on 19.02.14.
 */
@Remote
public interface SecurityRemote {
    public User checkLoginPassword(String login, String password) throws LoginPasswordIncorrectException;
    public User register(User user) throws LoginOrEmailNotUniqueException;
    public String showSecretQuestion(String login, String email) throws NoLoginEmailCorrespondenceException;
    public boolean changePassword(String login, String answer, String newPassword) throws IncorrectSecretAnswerException;
}
