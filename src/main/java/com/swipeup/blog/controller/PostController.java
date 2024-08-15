package com.swipeup.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swipeup.blog.payload.ApiResponse;
import com.swipeup.blog.payload.PostDto;
import com.swipeup.blog.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/users/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDto> creatPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer categoryId,
			@PathVariable Integer userId) {

		PostDto createdPost = this.postService.createPost(postDto, categoryId, userId);
		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);

	}

	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable Integer postId) {

		PostDto updatedPost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/post/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@Valid @PathVariable Integer postId) {

		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Successfully", true), HttpStatus.OK);

	}

	@GetMapping("/post")
	public ResponseEntity<List<PostDto>> getAllPost(@RequestParam(value="pageNumber" , required = false,defaultValue = "1") Integer pageNumber, @RequestParam(value="pageSize" , required = false,defaultValue = "1") Integer pageSize) {

		List<PostDto> listOfposts = this.postService.getAllPost(pageNumber,pageSize);
		return new ResponseEntity<List<PostDto>>(listOfposts, HttpStatus.FOUND);

	}

	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getPostById(@Valid @PathVariable Integer postId) {

		PostDto post = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(post, HttpStatus.FOUND);

	}

	@GetMapping("/post/category/{catorgoryId}")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer catorgoryId) {

		List<PostDto> listOfpostsbyCategory = this.postService.getPostByCategory(catorgoryId);
		return new ResponseEntity<List<PostDto>>(listOfpostsbyCategory, HttpStatus.FOUND);

	}

	@GetMapping("/post/user/{userId}")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {

		List<PostDto> listOfpostsbyCategory = this.postService.getPostByCategory(userId);
		return new ResponseEntity<List<PostDto>>(listOfpostsbyCategory, HttpStatus.FOUND);

	}

}
