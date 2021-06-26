package com.programmercommunity.hicks.blog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.programmercommunity.hicks.blog.model.Blog;

public interface BlogRepository extends MongoRepository<Blog, String> {

}
