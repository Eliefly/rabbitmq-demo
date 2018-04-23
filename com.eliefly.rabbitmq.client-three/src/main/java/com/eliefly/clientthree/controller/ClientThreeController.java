package com.eliefly.clientthree.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClientThreeController
 *
 * @author eliefly
 * @date 2018-04-19
 */
@RestController
public class ClientThreeController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        String msg = "hello, i am client three";
        return msg;
    }

}
