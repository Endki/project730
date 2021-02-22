package com.bangba.project730.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="회원정보", description = "이메일, 비밀번호, 닉네임 등을 가진  Domain Class")
public class UserRankDto {
	
	@ApiModelProperty(value="회원 pk값")
	private int pk_user;
	@ApiModelProperty(value="회원 이메일")
	private String email;
	@ApiModelProperty(value="회원 비밀번호")
	private String password;
	@ApiModelProperty(value="회원 닉네임")
	private String user_name;
	@ApiModelProperty(value="회원 생년월일")
	private String birth;
	@ApiModelProperty(value="회원 전화번호")
	private String phone_number;
	@ApiModelProperty(value="관리자 여부")
	private boolean user_type;
	@ApiModelProperty(value="영구정지 여부")
	private boolean banned;
	@ApiModelProperty(value="회원 프로필 사진 경로")
	private String img_path;
	
	private int user_no;
	private int like_weekly;
	
	public int getPk_user() {
		return pk_user;
	}

	public void setPk_user(int pk_user) {
		this.pk_user = pk_user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public boolean isUser_type() {
		return user_type;
	}

	public void setUser_type(boolean user_type) {
		this.user_type = user_type;
	}

	public boolean isBanned() {
		return banned;
	}

	public void setBanned(boolean banned) {
		this.banned = banned;
	}
 
	public String getImg_path() {
		return img_path;
	}

	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	
	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public int getLike_weekly() {
		return like_weekly;
	}

	public void setLike_weekly(int like_weekly) {
		this.like_weekly = like_weekly;
	}

}
