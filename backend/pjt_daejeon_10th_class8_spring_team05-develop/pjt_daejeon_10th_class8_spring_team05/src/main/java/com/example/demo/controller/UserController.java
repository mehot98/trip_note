package com.example.demo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.dto.UserDto;
import com.example.demo.model.service.UserService;
import com.example.demo.util.JWTUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

	private UserService userService;
	private JWTUtil jwtUtil;

	public UserController(UserService userService, JWTUtil jwtUtil) {
		super();
		this.userService = userService;
		this.jwtUtil = jwtUtil;
	}

	@GetMapping("/{userid}") // ID 확인
	public int idCheck(@PathVariable("userid") String userId) throws Exception {
		log.debug("idCheck userid : {}", userId);
		return userService.idCheck(userId);
	}

	@PostMapping("/join") // 회원가입
	public ResponseEntity<?> joinUser(@RequestBody UserDto userDto) {
		log.debug("userJoin userDto : {}", userDto);
		try {
			int isOk = userService.joinUser(userDto);
			return new ResponseEntity<Integer>(isOk, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	public int join(UserDto userDto) throws Exception {
		log.debug("userDto info : {}", userDto);
		return userService.joinUser(userDto);
	}

	// pathVariable 안 한게 url에 뜨게 하고 싶지 않아서 /modify/{userId} //
	// (@PathVariable("userid") String userId) 이거 까지 해줘야 함
	@PutMapping(value = "/modify", consumes="multipart/form-data") // 한명의 정보를 수정해줄 예정 => 수정해준 얘의 DTO만 반환하고 싶음
	public ResponseEntity<?> userModify(@RequestPart(value="userDto") UserDto userDto, @RequestPart(required = false ,value="profileImg") MultipartFile profileImg) throws IOException {
		System.out.println(userDto);
		System.out.println(profileImg);
		if(profileImg != null) userDto.setProfileImg(profileImg.getBytes());
		log.debug("userModify memberDto : {}", userDto);
		try {
			int success = userService.updateUser(userDto);
			return new ResponseEntity<Integer>(success, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

//	@GetMapping("/profile/{userid}") // 프로필에 뿌릴 UserDto 가져오기
//	public UserDto getUserProfile(@PathVariable("userid") String userId) throws Exception {
//		return userService.getUserProfile(userId);
//	}

	@GetMapping(value = "/list") // User 목록 가져옴
	public ResponseEntity<?> userList() {
		log.debug("userList call");
		try {
			List<UserDto> list = userService.listUser(null);
			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<List<UserDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}

	}

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userId}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userId") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userId,
			HttpServletRequest request) {
//		logger.debug("userId : {} ", userId);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
			log.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				UserDto memberDto = userService.userInfo(userId);
				resultMap.put("userInfo", memberDto);
				status = HttpStatus.OK;
			} catch (Exception e) {
				log.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			log.error("사용 불가능 토큰!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "로그아웃", notes = "회원 정보를 담은 Token을 제거한다.", response = Map.class)
	@GetMapping("/logout/{userId}")
	public ResponseEntity<?> removeToken(@PathVariable ("userId") @ApiParam(value = "로그아웃할 회원의 아이디.", required = true) String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			userService.deleRefreshToken(userId);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@ApiOperation(value = "Access Token 재발급", notes = "만료된 access token을 재발급받는다.", response = Map.class)
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody UserDto userDto, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refreshToken");
		log.debug("token : {}, memberDto : {}", token, userDto);
		if (jwtUtil.checkToken(token)) {
			if (token.equals(userService.getRefreshToken(userDto.getUserId()))) {
				String accessToken = jwtUtil.createAccessToken(userDto.getUserId());
				log.debug("token : {}", accessToken);
				log.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				status = HttpStatus.CREATED;
			}
		} else {
			log.debug("리프레쉬토큰도 사용불가!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@DeleteMapping(value = "/delete/{userid}")
	public ResponseEntity<?> userDelete(@PathVariable("userid") String userId) {
		log.debug("userDelete userid : {}", userId);
		try {
			int suc = userService.deleteUser(userId);
			return new ResponseEntity<Integer>(suc, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ApiOperation(value = "로그인", notes = "아이디와 비밀번호를 이용하여 로그인 처리.")
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) UserDto userDto) {
		log.debug("login user : {}", userDto);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			UserDto loginUser = userService.login(userDto);
			if (loginUser != null) {
				String accessToken = jwtUtil.createAccessToken(loginUser.getUserId());
				String refreshToken = jwtUtil.createRefreshToken(loginUser.getUserId());
				log.debug("access token : {}", accessToken);
				log.debug("refresh token : {}", refreshToken);

//				발급받은 refresh token을 DB에 저장.
				userService.saveRefreshToken(loginUser.getUserId(), refreshToken);

//				JSON으로 token 전달.
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);

				status = HttpStatus.CREATED;
			} else {
				resultMap.put("message", "아이디 또는 패스워드를 확인해주세요.");
				status = HttpStatus.UNAUTHORIZED;
			}

		} catch (Exception e) {
			log.debug("로그인 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
