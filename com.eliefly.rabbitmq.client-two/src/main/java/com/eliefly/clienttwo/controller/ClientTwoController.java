package com.eliefly.clienttwo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClientTwoController
 *
 * @author eliefly
 * @date 2018-04-19
 */
@RestController
public class ClientTwoController {

    @RequestMapping(value = "/two/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello, i am client two";
    }

}
