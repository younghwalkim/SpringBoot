package org.ict.testjwt.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ict.testjwt.common.SearchDate;
import org.ict.testjwt.config.ApplicationConfig;
import org.ict.testjwt.jwt.JwtTokenUtil;
import org.ict.testjwt.member.model.dto.MemberDto;
import org.ict.testjwt.member.model.service.MemberService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.ict.testjwt.jwt.JwtTokenUtil.creatToken;

@Slf4j
@RestController
//@RequestMapping("/members")
@RequiredArgsConstructor
@CrossOrigin     //리액트 애플리케이션(포트가 다름)의 자원 요청을 처리하기 위함
public class MemberController {

    private final MemberService memberService;
    private final ApplicationConfig passwordEncode;

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(
            @RequestBody MemberDto memberDto){

        // 토큰 객체 생성.
        Map<String,String> token = new HashMap<>();

        MemberDto member = memberService.selectMember(memberDto.getUserId());

        // 암호화된 비밀번호 동일한지 확인
        if(passwordEncode.matches(memberDto.getUserPwd(), member.getUserPwd())){

            // 로그인 토큰생성
            String accessToken = creatToken();
            String refreshToken = JwtTokenUtil.createRefreshToken();

            token.put("access_token", accessToken);
            token.put("refreshToken", refreshToken);

            // 토큰을 DB에 저장 or radis 에 저장할 수도 있음.
            // memberService.insertToken(refreshToken);

        } else {
            // 로그인 실패 상태코드 리턴
            token.put("error","888");
        }

        return ResponseEntity.ok().body(token);
    }



    // 목록
    @GetMapping("/list")
    public ResponseEntity<ArrayList<MemberDto>> selectList(
            @RequestParam(name="page") int page,
            @RequestParam(name="limit") int limit){

        log.info("/members/list : " + page + ", " + limit);

        //JPA 가 제공하는 Pageable 객체를 사용함
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.DESC, "enrollDate"));

        //페이지에 출력할 목록 조회해 옴    => 응답 처리
        return new ResponseEntity<>(memberService.selectList(pageable), HttpStatus.OK);
    }

    // 상세
    @GetMapping("/mdetail/{userId}")
    public ResponseEntity<MemberDto> selectMemberDetail(@PathVariable("userId") String userId){
        log.info("/members/" + userId + "요청");
        return new ResponseEntity<>(memberService.selectMember(userId), HttpStatus.OK);
    }

    // 등록
    @PostMapping
    public ResponseEntity<Void> insertMember(@RequestBody MemberDto memberDto){
        log.info("insertMember : " + memberDto);
        memberService.insertMember(memberDto);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    // 삭제
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteMember(@PathVariable("userId") String userId){
        log.info("deleteMember : " + userId);
        memberService.deleteMember(userId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    // 아이디 검색
    @GetMapping("/userId")
    public ResponseEntity<List<MemberDto>> selectSearchUserId(
            @RequestParam(name="keyword") String keyword,
            @RequestParam(name="page") int page,
            @RequestParam(name="limit") int limit){

        log.info("/members/userId : " + keyword + ", " + page + ", " + limit);

        //JPA 가 제공하는 Pageable 객체를 사용함
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.DESC, "userId"));

        //페이지에 출력할 목록 조회해 옴    => 응답 처리
        return new ResponseEntity<>(memberService.selectSearchUserid(keyword, pageable), HttpStatus.OK);
    }

    // 성별 검색
    @GetMapping("/gender")
    public ResponseEntity<List<MemberDto>> selectSearchGender(
            @RequestParam(name="keyword") String keyword,
            @RequestParam(name="page") int page,
            @RequestParam(name="limit") int limit){

        log.info("/members/gender : " + keyword + ", " + page + ", " + limit);

        //JPA 가 제공하는 Pageable 객체를 사용함
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.DESC, "userId"));

        //페이지에 출력할 목록 조회해 옴    => 응답 처리
        return new ResponseEntity<>(memberService.selectSearchGender(keyword, pageable), HttpStatus.OK);
    }

    // 가입일 검색
    @GetMapping("/enrollDate")
    public ResponseEntity<List<MemberDto>> selectSearchEnrollDate(
            @RequestParam SearchDate searchDate,
            @RequestParam(name="page") int page,
            @RequestParam(name="limit") int limit){

        log.info("/members/enrollDate : " + page + ", " + limit);

        //JPA 가 제공하는 Pageable 객체를 사용함
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.DESC, "userId"));

        //페이지에 출력할 목록 조회해 옴    => 응답 처리
        return new ResponseEntity<>(memberService.selectSearchEnrollDate(searchDate, pageable), HttpStatus.OK);
    }

}
