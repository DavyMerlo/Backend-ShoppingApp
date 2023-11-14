package com.davy.restapi.authetication.controller;

import com.davy.restapi.authetication.request.AuthenticationRequest;
import com.davy.restapi.authetication.response.AuthenticationResponse;
import com.davy.restapi.authetication.service.AuthenticationServiceImpl;
import com.davy.restapi.authetication.request.RegisterRequest;
import com.davy.restapi.shared.handler.ResponseHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationServiceImpl service;

    @PostMapping("register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("authenticate")
    public ResponseEntity<?> register(
            @RequestBody AuthenticationRequest request){
        var data = service.authenticate(request);
        //return ResponseEntity.ok(service.authenticate(request));
        return ResponseHandler.generateResponse("successful", HttpStatus.OK, data);
    }

    @PostMapping("refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }
}
