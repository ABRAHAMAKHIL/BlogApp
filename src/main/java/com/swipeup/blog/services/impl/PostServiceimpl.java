package com.swipeup.blog.services.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.swipeup.blog.entity.Category;
import com.swipeup.blog.entity.Post;
import com.swipeup.blog.entity.User;
import com.swipeup.blog.exception.ResourceNotFoundException;
import com.swipeup.blog.payload.FinalPostResponse;
import com.swipeup.blog.payload.PostDto;
import com.swipeup.blog.repositories.CategoryRepo;
import com.swipeup.blog.repositories.PostRepo;
import com.swipeup.blog.repositories.UserRepo;
import com.swipeup.blog.services.PostService;

@Service
public class PostServiceimpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto, Integer categoryId, Integer userId) {
		Post post = this.PostDtoToPost(postDto);
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User", userId));

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User", categoryId));
		post.setDate(new Date());
		post.setImage("default.png");
		post.setCategory(category);
		post.setUser(user);

		Post newPost = this.postRepo.save(post);

		return this.PostToPostDto(newPost);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("POST", "PostId", postId));

		post.setContent(postDto.getContent());
		post.setTitle(postDto.getTitle());
		post.setImage(postDto.getImageName());

		Post newPost = this.postRepo.save(post);

		return this.PostToPostDto(newPost);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post", postId));

		this.postRepo.delete(post);

	}

	@Override
	public FinalPostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

		Sort sort = sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();

		
		FinalPostResponse postResponse = new FinalPostResponse();
		PageRequest p = PageRequest.of(pageNumber, pageSize,sort);

		Page<Post> Pagepost = this.postRepo.findAll(p);

		List<Post> post = Pagepost.getContent();

		List<PostDto> postDto = post.stream().map(pst -> this.PostToPostDto(pst)).collect(Collectors.toList());

		postResponse.setPostRecords(postDto);
		postResponse.setPageNumber(Pagepost.getNumber());
		postResponse.setPageSize(Pagepost.getSize());
		postResponse.setTotalElements(Pagepost.getTotalElements());
		postResponse.setTotalpages(Pagepost.getTotalPages());
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post", postId));
		return this.PostToPostDto(post);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category", categoryId));

		List<Post> post = this.postRepo.findByCategory(category);

		return post.stream().map(pst -> this.PostToPostDto(pst)).collect(Collectors.toList());
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User", userId));

		List<Post> usr = this.postRepo.findByUser(user);

		return usr.stream().map(postUser -> this.PostToPostDto(postUser)).collect(Collectors.toList());
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		
		List<Post> post =  this.postRepo.findByTitleContaining(keyword);
		List<PostDto> searchedPost = post.stream().map(postval->this.PostToPostDto(postval)).collect(Collectors.toList());
		return searchedPost;
	}

	public Post PostDtoToPost(PostDto postDto) {

		Post post = this.modelMapper.map(postDto, Post.class);

		return post;

	}

	public PostDto PostToPostDto(Post post) {

		PostDto postDto = this.modelMapper.map(post, PostDto.class);
		return postDto;
	}
	
	
	@Override
	public String uploadPostImage(String path, MultipartFile file) throws IOException {
		
		//fileName
		
		String fileName = file.getOriginalFilename();
		String randomUID = UUID.randomUUID().toString();
		String fileName1 = randomUID.concat(fileName).substring(fileName.lastIndexOf("."));
		
		//fullPath
		String filePath = path+File.separator+fileName1;
	
		//folder creations
		
		File f = new File(path);
		
		if(!f.exists()) {
			
			f.mkdir();
		}
		
		//copy
		
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		// TODO Auto-generated method stub
		return fileName1;
	}

}
