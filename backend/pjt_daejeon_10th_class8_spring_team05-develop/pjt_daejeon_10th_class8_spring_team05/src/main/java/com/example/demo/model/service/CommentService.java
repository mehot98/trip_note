package com.example.demo.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.example.demo.model.dto.CommentDto;

public interface CommentService {
		
	// board에 관한 댓글 => BoardTrip을 board로 고정
//	public int writeBoardComment(CommentDto commentDto, int boardTripId) throws SQLException;
	public int writeBoardComment(CommentDto commentDto) throws SQLException;
	public List<CommentDto> listBoardComment(int boardTripId) throws SQLException;
	public int deleteBoardComment(int boardTripId, int commentId) throws SQLException;
	
	// trip에 관한 댓글 => BoardTrip을 trip으로 고정
	public int writeTripComment(CommentDto commentDto) throws SQLException;
	public List<CommentDto> listTripComment(int boardTripId) throws SQLException;
	public int deleteTripComment(int boardTripId, int commentId) throws SQLException;
}
