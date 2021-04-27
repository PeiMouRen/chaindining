package com.rhythm.user.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyControlerAdvice {
    @ExceptionHandler(value = UnauthorizedException.class)
    public String UnauthorizedException() {
        return "nopermission";
    }
}
