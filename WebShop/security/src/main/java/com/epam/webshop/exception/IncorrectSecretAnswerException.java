package com.epam.webshop.exception;

/**
 * Created by Andrey Yun on 22.02.14.
 */
public class IncorrectSecretAnswerException extends Exception {

    public IncorrectSecretAnswerException(String msg) {
        super(msg);
    }
}
