package org.ict.testjpa2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String welcome() {
        return "index";
    }

    @GetMapping("/boardpage")
    public String moveBoardPage() {
        return "/boardList";
    }

    @GetMapping("/noticepage")
    public String moveNoticePage() {
        return "/noticeList";
    }

    @GetMapping("/memberpage")
    public String moveMemberPage() {
        return "/member";
    }


}
