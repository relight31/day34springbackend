package com.vttp.csf.springangulardemo.controllers;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vttp.csf.springangulardemo.models.Registration;
import com.vttp.csf.springangulardemo.models.Response;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrationRESTController {
    private Logger logger = Logger.getLogger(RegistrationRESTController.class.getName());

    @PostMapping(path = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE)
    // @CrossOrigin(origins = "*")
    public ResponseEntity<String> register(@RequestBody String payload) {
        Response resp;
        Registration reg;

        logger.info("Payload = " + payload);
        String id = UUID.randomUUID().toString().substring(0, 8);

        // read payload
        try {
            reg = Registration.createRegistration(payload);
            reg.setId(id);
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
            resp = new Response();
            resp.setCode(400);
            resp.setMessage(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(resp.toJson().toString());
        }

        System.out.println("-----------------> ");
        resp = new Response();
        resp.setCode(201);
        resp.setMessage(id);
        resp.setData(reg.toJson().toString());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(resp.toJson().toString());
    }
}
