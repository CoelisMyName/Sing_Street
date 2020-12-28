package com.alina.singstreet;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Service {
    static Service service;
    ExecutorService executorService = Executors.newFixedThreadPool(5);

    private Service() {
    }

    public static Service getInstance() {
        if (service == null) {
            service = new Service();
        }
        return service;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }
}
