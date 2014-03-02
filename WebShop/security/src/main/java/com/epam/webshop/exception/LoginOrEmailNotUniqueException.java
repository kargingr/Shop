package com.epam.webshop.exception;

/**
 * Created by Andrey Yun on 22.02.14.
 */
public class LoginOrEmailNotUniqueException extends Exception {
    public LoginOrEmailNotUniqueException(String msg) {
        super(msg);
    }
}
