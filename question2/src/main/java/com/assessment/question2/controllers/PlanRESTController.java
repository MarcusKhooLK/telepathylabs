package com.assessment.question2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.question2.models.Response;
import com.assessment.question2.services.PlanService;

@RestController
@RequestMapping("/plan")
public class PlanRESTController {

    @Autowired
    private PlanService planService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postPlan(@RequestBody String payload) {

        Response result = planService.processFromJsonString(payload);
        return ResponseEntity.ok().body(result.getData().toString());
    }
}
