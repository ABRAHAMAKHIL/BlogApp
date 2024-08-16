package com.swipeup.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swipeup.blog.entity.Comment;
import com.swipeup.blog.entity.Post;
import com.swipeup.blog.entity.User;
import com.swipeup.blog.exception.ResourceNotFoundException;
import com.swipeup.blog.payload.commentDto;
import com.swipeup.blog.repositories.PostRepo;
import com.swipeup.blog.repositories.UserRepo;
import com.swipeup.blog.repositories.commentRepo;
import com.swipeup.blog.services.commentService;

@Service
public class commnetServiceImpl implements commentService {

	@Autowired
	private commentRepo commentRepo;

	

	@Autowired

	private UserRepo userRepo;

	@Autowired

	private PostRepo postRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public commentDto createComment(commentDto commentdto, Integer postId, Integer userId) {
		Comment comment = this.commentRepo.save(this.commentDtoTocomment(commentdto));
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));

		comment.setPost(post);
		comment.setUser(user);

		Comment savedComment = this.commentRepo.save(comment);
		return this.commentToDto(savedComment);
	}

	@Override
	public void deleteComment(Integer commentId) {

		Comment comment = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentId", commentId));
		this.commentRepo.delete(comment);

	}

	public commentDto commentToDto(Comment comment) {

		return this.mapper.map(comment, commentDto.class);

	}

	public Comment commentDtoTocomment(commentDto commentdto) {

		return this.mapper.map(commentdto, Comment.class);

	}

}
