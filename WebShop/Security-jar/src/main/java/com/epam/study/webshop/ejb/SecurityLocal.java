package com.epam.study.webshop.ejb;

import com.epam.study.webshop.exception.*;
import com.epam.study.webshop.users.User;

import javax.ejb.Local;

/**
 * Created by Andrey Yun on 19.02.14.
 */
@Local
public interface SecurityLocal
{
    public User checkLoginPassword(String login, String password) throws LoginPasswordIncorrectException;
    public User register(User user) throws LoginOrEmailNotUniqueException;
    public String showSecretQuestion(String login, String email) throws NoLoginEmailCorrespondenceException;
    public boolean changePassword(String login, String answer, String newPassword) throws IncorrectSecretAnswerException;
}
