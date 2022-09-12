package com.example.mydaily.dtos;



import com.example.mydaily.dataaccess.CommentDao;
import com.example.mydaily.entities.Diary;
import com.example.mydaily.entities.Post;

import lombok.Data;

@Data
public class DiaryResponse {

	private Long id;
	private String text;
	private String date;
	private String username;
	private boolean ishidden;
	private Long commentAmount;
	
	public DiaryResponse(Diary entity,CommentDao commentDao) {
		this.id=entity.getId();
		this.text=entity.getText();
		this.date=entity.getDate().toString();
		this.username=entity.getUser().getUserName();
		this.ishidden=entity.isIshidden();
		this.commentAmount = (long) commentDao.findAllCommentByDiaryId(id).size();
		
	}
}
