package com.swipeup.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swipeup.blog.payload.ApiResponse;
import com.swipeup.blog.payload.commentDto;
import com.swipeup.blog.services.commentService;

@RestController
@RequestMapping("api/comment")
public class CommentController {

	@Autowired
	private commentService commentservice;

	@PostMapping("/user/{userId}/post/{postId}")
	public ResponseEntity<commentDto> createComment(@RequestBody commentDto commentdto, @PathVariable Integer postId,
			@PathVariable Integer userId) {

		commentDto SavedComment = this.commentservice.createComment(commentdto, postId, userId);

		return new ResponseEntity<commentDto>(SavedComment, HttpStatus.CREATED);

	}

	@DeleteMapping("/{commentId}")
	public ResponseEntity<ApiResponse> deleteResponse(@PathVariable Integer commentId) {

		this.commentservice.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Successfully", true), HttpStatus.OK);

	}

}
