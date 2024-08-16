package com.swipeup.blog.services;

import java.util.List;

import com.swipeup.blog.payload.FinalPostResponse;
import com.swipeup.blog.payload.PostDto;

public interface PostService {

	PostDto createPost(PostDto postDto, Integer categoryId, Integer userId);

	PostDto updatePost(PostDto postDto, Integer postId);

	void deletePost(Integer postId);

	FinalPostResponse getAllPost(Integer pageSize,Integer pageNumber,String sortBy,String sortDir);

	PostDto getPostById(Integer postId);

	List<PostDto> getPostByCategory(Integer categoryId);

	List<PostDto> getPostByUser(Integer userId);

	List<PostDto> searchPosts(String keyword);

}
