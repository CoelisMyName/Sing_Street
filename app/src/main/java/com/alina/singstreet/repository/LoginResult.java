package com.alina.singstreet.repository;

import com.alina.singstreet.domain.User;

public class LoginResult {
    Success<User> success;

    Error<Integer> error;

    public LoginResult(){
    }

    public LoginResult(Success<User> success){
        this.success = success;
    }

    public LoginResult(Error<Integer> e){
        error = e;
    }

    public static class Success<T>{
        public Success(T t){
            data = t;
        }
        public T data;
    }

    public static class Error<T>{
        public Error(T t){
            message = t;
        }
        public T message;
    }

    public Error<Integer> getError() {
        return error;
    }

    public Success<User> getSuccess() {
        return success;
    }
}
