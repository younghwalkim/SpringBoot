package org.ict.testjpa2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class HomeController {
    
    // main
    @GetMapping("/")
    public String welcome() {
        return "index";
    }

    // Board 목록
    @GetMapping("/boardpage")
    public String moveBoardPage() {
        return "/boardList";
    }

    // Board 등록
    @GetMapping("/boardwritepage")
    public String moveBoardWrite() {
        return "/boardWriteForm";
    }


    // Board 상세
    @GetMapping("/boardviewpage/{bno}")
    public ModelAndView moveBoardView(
            @PathVariable("bno") int boardNum,
            ModelAndView mv) {

        log.info("### boardNum :" + boardNum);

        mv.addObject("bno", boardNum);
        mv.setViewName("/boardView");

        return mv;
    }

    // Board 수정
    @GetMapping("/boardmodifypage/{bno}")
    public ModelAndView moveBoardModify(
            @PathVariable("bno") int boardNum,
            ModelAndView mv) {

        log.info("### boardNum :" + boardNum);

        mv.addObject("bno", boardNum);
        mv.setViewName("/boardModifyForm");

        return mv;
    }

    // Notice 목록
    @GetMapping("/noticepage")
    public String moveNoticePage() {
        return "/noticeList";
    }

    // member 가입
    @GetMapping("/memberpage")
    public String moveMemberPage() {
        return "/member";
    }

}
