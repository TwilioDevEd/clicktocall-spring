package com.twilio.clicktocall;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CallController {

    @RequestMapping("index")
    public String index() {
        return "index";
    }
}
