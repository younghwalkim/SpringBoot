package org.ict.testjpa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "ajaxHome";
    }

    @GetMapping("/boardList")
    public String boardList() {
        return "/boardList";
    }

    @GetMapping("/boardWriteForm")
    public String boardWriteForm() {
        return "/boardWriteForm";
    }

}
