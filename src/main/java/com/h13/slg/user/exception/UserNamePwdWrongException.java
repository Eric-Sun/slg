package com.h13.slg.user.exception;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-1-26
 * Time: 下午5:02
 * To change this template use File | Settings | File Templates.
 */
public class UserNamePwdWrongException extends Exception{
    public UserNamePwdWrongException() {
        super();
    }

    public UserNamePwdWrongException(String s) {
        super(s);
    }

    public UserNamePwdWrongException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
