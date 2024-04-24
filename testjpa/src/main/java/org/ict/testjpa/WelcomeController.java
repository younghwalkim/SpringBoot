package org.ict.testjpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
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

    @GetMapping("/boardView/{bno}")
    public ModelAndView boardView(
            @PathVariable("bno") int boardNum,
            ModelAndView mv) {

        log.info("### boardNum :" + boardNum);

        mv.addObject("bno", boardNum);
        mv.setViewName("/boardView");

        return mv;
    }

    @GetMapping("/boardModifyForm/{bno}")
    public ModelAndView boardModifyForm(
            @PathVariable("bno") int boardNum,
            ModelAndView mv) {

        log.info("### boardNum :" + boardNum);

        mv.addObject("bno", boardNum);
        mv.setViewName("/boardModifyForm");

        return mv;
    }

}
