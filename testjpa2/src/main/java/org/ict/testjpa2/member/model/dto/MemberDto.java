package org.ict.testjpa2.member.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

}
