package com.swipeup.blog.payload;

import java.util.List;

import lombok.Data;


@Data
public class FinalPostResponse {
	
	
	private List<PostDto> postRecords;
	
	private int pageNumber;
	private int pageSize;
	private long totalElements;
	private int totalpages;
	private boolean lastPage;

}
