package org.ict.testjpa2.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ict.testjpa2.member.model.dto.MemberDto;
import org.ict.testjpa2.member.model.service.MemberService;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@CrossOrigin    // 리엑트 어플리케이션(포트다름)의 자원 요청을 처리하기 위함.
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    // list : 회원목록
    @GetMapping("/list")
    public ResponseEntity<ArrayList<MemberDto>> selectList(
            @RequestParam(name="page", required=false, defaultValue="1") int page,
            @RequestParam(name="limit", required=false, defaultValue="10") int limit){

        log.info("/members/list : " + page + ", " + limit);

        // JPA 가 제공하는 Pageable 객체를 사용함
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.DESC, "userId"));

        // 페이지에 출력할 목록 조회해 옴 => 응답 처리
        return new ResponseEntity<>(memberService.selectList(pageable), HttpStatus.OK);
    }

    // 등록처리
    @PostMapping
    public ResponseEntity<Void> insertMember(
            @RequestBody MemberDto memberDto){

        log.info("### insertBoard : " + memberDto);

        memberService.insertMember(memberDto);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    // 조회
    @GetMapping("/{userid}")
    public ResponseEntity<MemberDto> selectMemberDetail(
            @PathVariable("userid") String userid){

        log.info("/members/" + userid + "요청");

        return new ResponseEntity<>(memberService.selectMember(userid), HttpStatus.OK);
    }

    // 수정
    @PutMapping("/{userid}")  //요청 경로에 반드시 pk 에 해당하는 값을 전송해야 함 (안 보내면 에러)
    public ResponseEntity<Void> updateMember(
            @PathVariable("userid") String userid, @RequestBody MemberDto memberDto){

        log.info("updateMember : " + userid);

        memberService.updateMember(memberDto);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    // 삭제
    @DeleteMapping("/{userid}")
    public ResponseEntity<Void> deleteMember(
            @PathVariable("userid") String userid){

        log.info("### deleteMember : " + userid);

        memberService.deleteMember(userid);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    // 아이디 검색 : 목록 갯수 조회용
    @GetMapping("/countSearchUserId")
    public ResponseEntity<Long> getCountSearchUserId(
            @RequestParam(name="keyword") String keyword){

        log.info("### /members/countSearchUserId : " + keyword);

        return new ResponseEntity<>(memberService.getSearchUserIdCount(keyword), HttpStatus.OK);
    }

    // 아이디 검색 : 목록 조회용
    @GetMapping("/userid")
    public ResponseEntity<List<MemberDto>> selectSearchUserId(
            @RequestParam(name="keyword") String keyword,
            @RequestParam(name="page") int page,
            @RequestParam(name="limit") int limit){

        log.info("### /members/userid : " + keyword + ", " + page + ", " + limit);

        //JPA 가 제공하는 Pageable 객체를 사용함
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.DESC, "userId"));

        //페이지에 출력할 목록 조회해 옴    => 응답 처리
        return new ResponseEntity<>(memberService.selectSearchUserId(keyword, pageable), HttpStatus.OK);
    }

}
