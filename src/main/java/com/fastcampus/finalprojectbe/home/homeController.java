package com.fastcampus.finalprojectbe.home;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class homeController {

    @GetMapping("/")
    public String homeRedirect() {
        return "redirect:/swagger-ui/index.html";
    }

}
