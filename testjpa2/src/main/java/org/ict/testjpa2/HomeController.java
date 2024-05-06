package org.ict.testjpa2;

import lombok.extern.slf4j.Slf4j;
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
    @GetMapping("/boards")
    public ModelAndView moveBoardPage(ModelAndView mv) {
        log.info("# B-1. HomeController > boardlist() : ");
        mv.setViewName("/board/boardList");
        return mv;
    }

    // Board 등록
    @GetMapping("/boardwritepage")
    public String moveBoardWrite() {
        return "/board/boardWriteForm";
    }

    // Board 상세
    @GetMapping("/boardviewpage/{bno}")
    public ModelAndView moveBoardView(
            @PathVariable("bno") int boardNum,
            ModelAndView mv) {

        log.info("### boardNum :" + boardNum);

        mv.addObject("bno", boardNum);
        mv.setViewName("/board/boardView");

        return mv;
    }

    // Board 수정
    @GetMapping("/boardmodifypage/{bno}")
    public ModelAndView moveBoardModify(
            @PathVariable("bno") int boardNum,
            ModelAndView mv) {

        log.info("### boardNum :" + boardNum);

        mv.addObject("bno", boardNum);
        mv.setViewName("/board/boardModifyForm");

        return mv;
    }

    // Notice 목록
    @GetMapping("/noticepage")
    public String moveNoticePage() {
        return "/notice/noticeList?page=1&limit=10";
    }

    // Notice 상세
    @GetMapping("/noticeviewpage/{no}")
    public ModelAndView moveNoticeView(
            @PathVariable("no") int noticeNum,
            ModelAndView mv) {

        log.info("### noticeNo :" + noticeNum);

        mv.addObject("no", noticeNum);
        mv.setViewName("/notice/noticeView");

        return mv;
    }

    // member list
    @GetMapping("/mlistpage")
    public String moveMemberListPage() {
        return "/member/list";
    }

    // member inserForm
    @GetMapping("/mwritepage")
    public String moveMemberWritePage() {
        return "/member/writeForm";
    }

    // member view
    @GetMapping("/mviewpage/{userid}")
    public ModelAndView moveMemberViewPage(
            @PathVariable("userid") String userid,
            ModelAndView mv) {

        log.info("### member view :" + userid);

        mv.addObject("userId", userid);
        mv.setViewName("/member/view");

        return mv;
    }


}
