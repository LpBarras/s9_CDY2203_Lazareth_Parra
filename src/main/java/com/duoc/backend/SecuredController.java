package com.duoc.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SecuredController {

    @GetMapping(value = "/greetings", produces = "text/plain")
public String greetings(
        @RequestParam(value = "name", defaultValue = "World") String name) {

    return "Hello {" + name + "}";
}
}