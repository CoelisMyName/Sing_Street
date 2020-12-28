package com.alina.singstreet.repository;

import com.alina.singstreet.domain.User;

public class LoginResult {
    Success<User> success;

    Error<Integer> error;

    public LoginResult() {
    }

    public LoginResult(Success<User> success) {
        this.success = success;
    }

    public LoginResult(Error<Integer> e) {
        error = e;
    }

    public Error<Integer> getError() {
        return error;
    }

    public Success<User> getSuccess() {
        return success;
    }

    public static class Success<T> {
        public T data;

        public Success(T t) {
            data = t;
        }
    }

    public static class Error<T> {
        public T message;

        public Error(T t) {
            message = t;
        }
    }
}
