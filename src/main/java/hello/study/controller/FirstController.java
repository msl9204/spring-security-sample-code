package hello.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @PostMapping("/a")
    public String first() {
        return "first!";
    }

    @PostMapping("/b")
    public String validationApi() {
        return "validation";
    }
}
