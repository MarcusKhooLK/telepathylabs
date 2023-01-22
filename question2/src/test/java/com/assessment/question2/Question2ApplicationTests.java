package com.assessment.question2;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.nio.file.Files;
import java.nio.file.Paths;

import com.assessment.question2.models.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
public class Question2ApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    void findBestPlanTest() throws Exception {
        String url = "/plan";
        String jsonString = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/src/test/resources/test_data.json")));
        MvcResult result = mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status);
        String content = result.getResponse().getContentAsString();

        jsonString = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/src/test/resources/expected_result.json")));

        Response actual = Response.convert(content);
        Response expected = Response.convert(jsonString);

        assertEquals(expected.getPrice(), actual.getPrice());
        assertArrayEquals(expected.getPlanNames().toArray(), actual.getPlanNames().toArray());
    }
}
