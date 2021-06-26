package com.programmercommunity.hicks.blog.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.programmercommunity.hicks.blog.asset.Paths;
import com.programmercommunity.hicks.blog.asset.DateTimeFormats;
import com.programmercommunity.hicks.blog.asset.SearchSource;
import com.programmercommunity.hicks.blog.model.Blog;
import com.programmercommunity.hicks.blog.service.BlogService;

@RestController
public class BlogController {
	

	@Autowired
	BlogService BlogService;

	@ModelAttribute
	public void setResponseHeader(HttpServletResponse response){
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setHeader("Content-Type","application/json");
	}
	
	@GetMapping(Paths.BlogControllerPaths.SORT_BY_LIKES )
	public List<Blog> getSortedBlogsByLikes() {
		return sortByLikes(this.BlogService.getAll());
	}

	@GetMapping(Paths.BlogControllerPaths.SORT_BY_DATE)
	public List<Blog> getSortedBlogsByAddedDate() {
		return sortByAddedDate(this.BlogService.getAll());
	}
	
	@GetMapping(Paths.BlogControllerPaths.SEARCH)
	public List<Blog> searchByQuery(String query,String source){
		if( source.equalsIgnoreCase(SearchSource.BLOG) ) {
			return searchInBlog(query,this.BlogService.getAll());
		}
		else if( source.equalsIgnoreCase(SearchSource.AUTHOR) ) {
			return searchInAuthor(query,this.BlogService.getAll());
		}
		return new ArrayList<Blog>();
	}
	/*
	@GetMapping(Paths.BlogControllerPaths.FEED)
	public List<Blog> blogByFollowers(String query,String source){
		return sortByAddedDate(this.BlogService.getAll());
	}
	*/
	
	private Date fromString(String dateString,String format) {
		Date date =null;
		try {
			date=new SimpleDateFormat(format).parse(dateString);
		} catch (ParseException e) {
			date = Date.from(Instant.now());
		} 
		return date;
	}
	private List<Blog> sortByLikes(List<Blog> blogs){
		Comparator<Blog> compareByLikes = new Comparator<Blog>() {
		    @Override
		    public int compare(Blog o1, Blog o2) {
		        return o1.getLikes() < o2.getLikes() ? 1:-1;
		    }
		};
		Collections.sort(blogs, compareByLikes);
		return blogs;
	}
	private List<Blog> sortByAddedDate(List<Blog> blogs){
		 
		Comparator<Blog> compareByAddeddate = new Comparator<Blog>() {
		    @Override
		    public int compare(Blog o1, Blog o2) {
		    	String format = o1.getAddedDate().contains(" ") ? 
		    			DateTimeFormats.DATE_TIME_FORMAT:DateTimeFormats.DATE_FORMAT;
		    	Date o1Date = fromString(o1.getAddedDate(),format);
		    	format = o2.getAddedDate().contains(" ") ? 
		    			DateTimeFormats.DATE_TIME_FORMAT:DateTimeFormats.DATE_FORMAT;
		    	Date o2Date = fromString(o1.getAddedDate(),format);
		        return o2Date.compareTo(o1Date);
		    }
		};
		Collections.sort(blogs, compareByAddeddate);
		return blogs;
	}
	private List<Blog> searchInBlog(String query,List<Blog> blogs){
		return blogs.stream()
				.filter(blog->blog.getTitle().contains(query)||blog.getSubtitle().contains(query))
				.collect(Collectors.toList());
	}
	private List<Blog> searchInAuthor(String query,List<Blog> blogs){
		return blogs.stream()
				.filter(blog->blog.getAuthorId().equalsIgnoreCase(query))
				.collect(Collectors.toList());
	}
}
