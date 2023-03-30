package com.fastcampus.finalprojectbe.home;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping(value = "/")
@ApiIgnore
public class homeController {

    @RequestMapping("/")
    public String homeRedirect() {
        return "redirect:/swagger-ui/index.html";
    }

}
