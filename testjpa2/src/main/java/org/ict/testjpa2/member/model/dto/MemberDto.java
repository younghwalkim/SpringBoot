package org.ict.testjpa2.member.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ict.testjpa2.member.jpa.entity.MemberEntity;
import org.springframework.stereotype.Component;

@Data   //@ToString, @Getter, @Setter,...
@Builder
@Component
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {


    private String userId;
    private String userPwd;
    private String userName;
    private String gender;
    private int age;
    private String phone;
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private java.util.Date enrollDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private java.util.Date lastModified;

    private String signType;
    private String adminYN;
    private String loginOk;
    private String photoFileName;

    // dto --> entity 로 변환
    public MemberEntity toEntity(){
        return MemberEntity.builder()
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
