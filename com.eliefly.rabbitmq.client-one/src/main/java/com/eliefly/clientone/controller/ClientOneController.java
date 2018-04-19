package com.eliefly.clientone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClientOneController
 *
 * @author eliefly
 * @date 2018-04-19
 */
@RestController
public class ClientOneController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello, i am client one";
    }

}
