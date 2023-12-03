package com.example.demo.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "CommentDto : 댓글정보", description = "댓글의 상세 정보를 나타낸다.")
public class CommentDto {
	
	@ApiModelProperty(value = "댓글번호")
	private int commentId;
	@ApiModelProperty(value = "작성자 아이디")
	private String userId;
	@ApiModelProperty(value = "댓글내용")
	private String comment;
	@ApiModelProperty(value = "작성일")
	private String regtime;
	@ApiModelProperty(value = "게시판인지 여행인지")
	private String boardTrip;
	@ApiModelProperty(value = "게시판 혹은 여행의 아이디")
	private int boardTripId;
}
