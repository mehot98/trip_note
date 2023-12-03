package com.example.demo.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@ApiModel(value = "MemberDto (회원정보)", description = "아이디, 비번, 이름을 가진   Domain Class")
public class UserDto {
	@ApiModelProperty(value = "회원아이디")
	@NonNull
	private String userId;
	@ApiModelProperty(value = "회원비밀번호")
	@NonNull
	private String userPassword;
	@ApiModelProperty(value = "회원이름")
	private String userName;
	@ApiModelProperty(value = "이메일아이디")
	private String emailId;
	@ApiModelProperty(value = "이메일도메인")
	private String emailDomain;
	@ApiModelProperty(value = "가입일")
	private String joinDate;
	@ApiModelProperty(value = "팔로우수")
	private int followCount;
	@ApiModelProperty(value = "팔로워수")
	private int followerCount;
	@ApiModelProperty(value = "리프레쉬토큰")
	private String token;
	private byte[] profileImg;
	
	public UserDto(@NonNull String userId, String userName, int followCount, int followerCount) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.followCount = followCount;
		this.followerCount = followerCount;
	}
	public UserDto(@NonNull String userId, @NonNull String userPassword, String userName, int followCount,
			int followerCount, String token) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
		this.userName = userName;
		this.followCount = followCount;
		this.followerCount = followerCount;
		this.token = token;
	
	}
	
	
	
	
	
	
}
