package org.ict.testjpa2.member.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ict.testjpa2.member.model.dto.MemberDto;

import java.util.GregorianCalendar;

@Data
@Entity     // JPA가 관리함, 테이블과 DTO(VO) 클래스 매팅시 반드시 필요함.
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="MEMBER")    // 매핑할 테이블 이름 지정함, board 테이블을 자동으로 만들어주기도 함.
public class MemberEntity {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USERID", nullable = false, unique = true)
    private String userId;

    @Column(name="USERPWD", nullable = false)
    private String userPwd;

    @Column(name="USERNAME", nullable = false)
    private String userName;

    @Column(name="GENDER", nullable = false)
    private String gender;

    @Column(name="AGE", nullable = false)
    private int age;

    @Column(name="PHONE", nullable = false, unique = true)
    private String phone;

    @Column(name="EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name="ENROLL_DATE", nullable = false)
    private java.util.Date enrollDate;

    @Column(name="LASTMODIFIED", nullable = false)
    private java.util.Date lastModified;

    @Column(name="SIGNTYPE", columnDefinition = "direct")
    private String signType;

    @Column(name="ADMIN_YN", columnDefinition = "N")
    private String adminYN;

    @Column(name="LOGIN_OK", columnDefinition = "N")
    private String loginOk;

    @Column(name="PHOTO_FILENAME")
    private String photoFileName;

    @PrePersist  //jpa 로 넘어가지 전에 작동
    public void prePersist() {
        // enrollDate = new GregorianCalendar().getGregorianChange();    //현재 날짜 시간 적용
        // lastModified = new GregorianCalendar().getGregorianChange();  //현재 날짜 시간 적용

        GregorianCalendar calendar = new GregorianCalendar();
        this.enrollDate = calendar.getTime();
        this.lastModified = calendar.getTime();
    }

    // entity -> dto 로 변환
    public MemberDto toDto(){
        return MemberDto.builder()
                .userId(userId)
                .userPwd(userPwd)
                .userName(userName)
                .gender(gender)
                .age(age)
                .phone(phone)
                .email(email)
                .enrollDate(enrollDate)
                .lastModified(lastModified)
                .signType(signType)
                .adminYN(adminYN)
                .loginOk(loginOk)
                .photoFileName(photoFileName)
                .build();
    }

}
