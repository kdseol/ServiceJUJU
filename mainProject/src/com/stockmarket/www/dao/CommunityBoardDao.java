package com.stockmarket.www.dao;
//쿼리문...
import java.util.List;

import com.stockmarket.www.entity.CommunityBoard;

public interface CommunityBoardDao {
	List<CommunityBoard> getCommunityBoardList(int page, String Field, String Query, String Code);
	List<CommunityBoard> getReplyList(int boardId);
	
	CommunityBoard getCommunityBoardDetail(int id);

	int getReplyCnt(String field, String query, String stockName);
	int lastReplyNum(int boardId);
	
	int insertCommunityBoard(CommunityBoard communityBoard);
	int updateCommunityBoard(CommunityBoard communityBoard);
	int deleteCommunityBoard(int boardId);

	int insertReply(CommunityBoard insertReply);
	int updateReply(CommunityBoard updateReply);
	int deleteReply(int replyId);
	int deleteReplys(int boardId);
	
	int selectFavoriteBoard(CommunityBoard selectFavoriteBoard);
	int insertFavoriteBoard(CommunityBoard insertFavoriteBoard);
	int deleteFavoriteBoard(CommunityBoard deleteFavoriteBoard);
	List<CommunityBoard> getInterestBoardList(int loginId);
}
