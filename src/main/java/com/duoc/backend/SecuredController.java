package com.duoc.backend;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

@RestController
public class SecuredController {

    @RequestMapping(value = "/greetings", produces = MediaType.TEXT_PLAIN_VALUE)
    public String greetings(
                @RequestParam(value = "name", defaultValue = "World") String name) {
                        return "Hello {" + name + "}";
                    }
}