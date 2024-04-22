package com.boot.test.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class Member{

	private int accountNo;
	private String name;
	private String email;
	private String pwd;
	private Date cdate;
	private Date ddate;
	
}
