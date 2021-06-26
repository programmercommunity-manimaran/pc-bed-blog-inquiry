package com.programmercommunity.hicks.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.programmercommunity.hicks.blog.exception.NotFound;
import com.programmercommunity.hicks.blog.model.Blog;
import com.programmercommunity.hicks.blog.model.Response;
import com.programmercommunity.hicks.blog.repository.BlogRepository;

@Service
public class BlogService {
	@Autowired
	BlogRepository blogRepository;

	public List<Blog> getAll() {
		return this.blogRepository.findAll();
	}

	public Blog getById(String id) {
		if (this.blogRepository.existsById(id)) {
			return this.blogRepository.findById(id).get();
		}
		throw new NotFound("Blog Not found");
	}

	public Response add(Blog blog) {
		if (this.blogRepository.existsById(blog.getId())) {
			throw new NotFound("Blog already exists");
		}
		this.blogRepository.save(blog);
		return new Response(200, "Success");
	}

	public Response update(Blog blog) {
		if (this.blogRepository.existsById(blog.getId())) {
			this.blogRepository.save(blog);
			return new Response(200, "Success");
		}
		throw new NotFound("Blog Not found");
	}

	public Response deleteById(String id) {
		if (this.blogRepository.existsById(id)) {
			this.blogRepository.deleteById(id);
			return new Response(200, "Success");
		}
		throw new NotFound("Blog Not found");
	}
}
