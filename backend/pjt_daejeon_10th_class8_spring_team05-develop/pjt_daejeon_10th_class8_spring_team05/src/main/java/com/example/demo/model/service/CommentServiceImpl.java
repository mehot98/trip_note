package com.example.demo.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.model.dto.CommentDto;
import com.example.demo.model.mapper.CommentMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
//@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
	
	private CommentMapper commentMapper;
	
	// 생성자 주입이 안돼서 에러가 났었음
	public CommentServiceImpl(CommentMapper commentMapper) {
		super();
		this.commentMapper = commentMapper;
	}
	
	@Override
	public List<CommentDto> listBoardComment(int boardTripId) throws SQLException {
		return commentMapper.listBoardComment(boardTripId);
	}

	@Override
	public int deleteBoardComment(int boardTripId, int commentId) throws SQLException {
		return commentMapper.deleteBoardComment(boardTripId, commentId);
	}

	@Override
	public int writeTripComment(CommentDto commentDto) throws SQLException {
		return commentMapper.writeTripComment(commentDto);
	}

	@Override
	public List<CommentDto> listTripComment(int boardTripId) throws SQLException {
		List<CommentDto> listTrip = commentMapper.listTripComment(boardTripId);
		return listTrip;
	}

	@Override
	public int deleteTripComment(int boardTripId, int commentId) throws SQLException {
		return commentMapper.deleteTripComment(boardTripId, commentId);
	}

	@Override
	public int writeBoardComment(CommentDto commentDto) throws SQLException {
		return commentMapper.writeBoardComment(commentDto);
	}

}
