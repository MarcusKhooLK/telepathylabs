package com.assessment.question2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.assessment.question2.services.PlanService;

@SpringBootApplication
public class Question2Application implements ApplicationRunner {

    @Autowired
    private PlanService planService;

    public static void main(String[] args) {
        SpringApplication.run(Question2Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (args.getSourceArgs().length > 2)
            planService.processFromFile(args.getSourceArgs()[0], args.getSourceArgs()[1]);
    }

}
