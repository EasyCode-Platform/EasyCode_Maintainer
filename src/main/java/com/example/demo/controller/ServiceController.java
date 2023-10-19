package com.example.demo.controller;

import io.kubernetes.client.openapi.apis.CoreV1Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {
    @Autowired
    private CoreV1Api api;
}
