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
@ApiModel(value = "FollowDto (팔로우 정보)")
public class FollowDto {
	
	@ApiModelProperty(value = "회원아이디") // 이게 모든 회원들의 id 
	@NonNull
	private String userId;
	@ApiModelProperty(value = "팔로우하는 사람 아이디")
	private String followerId;
	@ApiModelProperty(value = "팔로우하는 여행계획 아이디")
	private int tripId;
	@ApiModelProperty(value = "팔로우하는 여행지 아이디")
	private int locationId;

	// 생성자 1: userId, followerId 설정
    public FollowDto(@NonNull String userId, String followerId) {
        super();
        this.userId = userId;
        this.followerId = followerId;
    }
    
    // 생성자 2: userId, tripId 설정
    public FollowDto(int tripId, @NonNull String userId) {
        super();
        this.tripId = tripId;
        this.userId = userId;
    }
    
    // 생성자 3: userId, locationId 설정
    public FollowDto(@NonNull String userId, int locationId) {
        super();
        this.userId = userId;
        this.locationId = locationId;
    }
	

}
