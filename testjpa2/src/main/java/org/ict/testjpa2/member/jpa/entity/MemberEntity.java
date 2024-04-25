package org.ict.testjpa2.member.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity     // JPA가 관리함, 테이블과 DTO(VO) 클래스 매팅시 반드시 필요함.
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="MEMBER")    // 매핑할 테이블 이름 지정함, board 테이블을 자동으로 만들어주기도 함.
public class MemberEntity {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USERID")
    private String userId;

    @Column(name="USERPWD")
    private String userPwd;

    @Column(name="USERNAME")
    private String userName;

    @Column(name="GENDER")
    private String gender;

    @Column(name="AGE")
    private int age;

    @Column(name="PHONE")
    private String phone;

    @Column(name="EMAIL")
    private String email;

    @Column(name="ENROLL_DATE")
    private java.util.Date enrollDate;

    @Column(name="LASTMODIFIED")
    private java.util.Date lastModified;

    @Column(name="SIGNTYPE")
    private String signType;

    @Column(name="ADMIN_YN")
    private String adminYN;

    @Column(name="LOGIN_OK")
    private String loginOk;

    @Column(name="PHOTO_FILENAME")
    private String photoFileName;

}
