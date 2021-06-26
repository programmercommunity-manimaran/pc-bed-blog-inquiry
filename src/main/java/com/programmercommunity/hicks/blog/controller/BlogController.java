package com.programmercommunity.hicks.blog.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import com.programmercommunity.hicks.blog.asset.Paths;
import com.programmercommunity.hicks.blog.model.Blog;
import com.programmercommunity.hicks.blog.model.Response;
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
	
	@GetMapping(Paths.BlogControllerPaths.GET_ALL)
	public List<Blog> getAll() {
		return this.BlogService.getAll();
	}

	@GetMapping(Paths.BlogControllerPaths.GET_BY_ID)
	public Blog getById(@PathVariable("id") String id) {
		return this.BlogService.getById(id);
	}

	@PostMapping(Paths.BlogControllerPaths.ADD)
	public Response add(@RequestBody Blog blog) {
		return this.BlogService.add(blog);
	}

	@PutMapping(Paths.BlogControllerPaths.UPDATE)
	public Response update(@RequestBody Blog blog) {
		return this.BlogService.update(blog);
	}

	@DeleteMapping(Paths.BlogControllerPaths.DELETE)
	public Response deleteById(@PathVariable("id") String id) {
		return this.BlogService.deleteById(id);
	}
}
