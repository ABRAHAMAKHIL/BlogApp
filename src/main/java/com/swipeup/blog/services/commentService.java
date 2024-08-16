package com.swipeup.blog.services;

import com.swipeup.blog.payload.commentDto;

public interface commentService {
	
	commentDto createComment(commentDto commentdto ,Integer postId,Integer userId);
	void deleteComment(Integer commentId);
	

}
