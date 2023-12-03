package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.BoardDto;
import com.example.demo.model.dto.CommentDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.service.CommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/comment")
@Api("댓글 컨트롤러")
@Slf4j
public class CommentController {

	private CommentService commentService;

	public CommentController(CommentService commentService) {
		super();
		this.commentService = commentService;
	}

	// 게시판 댓글
	@ApiOperation(value = "게시판 댓글삭제", response = String.class)
	@DeleteMapping("/board/{boardTripId}/{commentId}")
	public ResponseEntity<String> deleteBoardComment(
			@PathVariable("boardTripId") @ApiParam(value = "게시글 번호", required = true) int boardTripId,
			@PathVariable("commentId") @ApiParam(value = "댓글 번호", required = true) int commentId) throws Exception {
		log.info("deleteBoardComment - 호출");
		commentService.deleteBoardComment(boardTripId, commentId);
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "게시판 댓글작성")
	@PostMapping("/board")
	public ResponseEntity<?> writeBoardComment(
			@RequestBody @ApiParam(value = "게시글 정보.", required = true) CommentDto commentDto) {
		try {
			commentService.writeBoardComment(commentDto);
			return new ResponseEntity<Integer>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "게시판 댓글보기", response = BoardDto.class)
	@GetMapping("/board/{boardTripId}")
	public ResponseEntity<?> getBoardComment(
			@PathVariable("boardTripId") @ApiParam(value = "얻어올 글의 글번호.", required = true) int boardTripId)
			throws Exception {
		List<CommentDto> list = commentService.listBoardComment(boardTripId);
		log.info("listBoardComment - 호출 : " + boardTripId);
		return new ResponseEntity<List<CommentDto>>(list , HttpStatus.OK);
	}

	// 여행 댓글
	@ApiOperation(value = "여행 댓글삭제", response = String.class)
	@DeleteMapping("/trip/{boardTripId}/{commentId}")
	public ResponseEntity<String> deleteTripComment(
			@PathVariable("boardTripId") @ApiParam(value = "게시글 번호", required = true) int boardTripId,
			@PathVariable("commentId") @ApiParam(value = "댓글 번호", required = true) int commentId) throws Exception {
		log.info("deleteTripComment - 호출");
		commentService.deleteTripComment(boardTripId, commentId);
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "여행 댓글작성") // 게시판 댓글처럼 바꿔야 함.
	@PostMapping("/trip")
	public ResponseEntity<?> writeTripComment(
			@RequestBody @ApiParam(value = "여행정보.", required = true) CommentDto commentDto) {
		try {
			int cnt = commentService.writeTripComment(commentDto);
			return new ResponseEntity<Integer>(cnt, HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "여행 댓글보기", response = BoardDto.class)
	@GetMapping("/trip/{boardTripId}")
	public ResponseEntity<?> getTripComment(
			@PathVariable("boardTripId") @ApiParam(value = "얻어올 여행의 id.", required = true) int boardTripId)
			throws Exception {
		
		List<CommentDto> list = commentService.listTripComment(boardTripId);
		log.info("listTripComment - 호출 : " + boardTripId);
		return new ResponseEntity<List<CommentDto>>(list , HttpStatus.OK);
		}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
