// 주소에 직접값을 넣어도 렌더링을 가능하게 해줌.
package com.example.backend.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpaForwardController {
    @RequestMapping(value = {
            "/{path:[^\\.]*}",
            "/{path:[^\\.]*}/{subPath:[^\\.]*}",
            "/{path:[^\\.]*}/{subPath:[^\\.]*}/{subSubPath:[^\\.]*}"
    })
    public String forward(){
        return "forward:/index.html";
    }
}
