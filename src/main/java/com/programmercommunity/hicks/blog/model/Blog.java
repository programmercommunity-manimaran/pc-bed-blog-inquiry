package com.programmercommunity.hicks.blog.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("blog")
public class Blog {
	@Id
	private String id;
	private String title;
	private String subtitle;
	private String authorId; // user id
	private List<String> images;
	private List<String> contents;
	private List<String> hashtags;
	private long views;
	private long likes;
	private long noOfComment; // no of comments
	private boolean isPublic;
	private String addedDate;
	private String lastActiveDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
	public List<String> getContents() {
		return contents;
	}
	public void setContents(List<String> contents) {
		this.contents = contents;
	}
	public List<String> getHashtags() {
		return hashtags;
	}
	public void setHashtags(List<String> hashtags) {
		this.hashtags = hashtags;
	}
	public long getViews() {
		return views;
	}
	public void setViews(long views) {
		this.views = views;
	}
	public long getLikes() {
		return likes;
	}
	public void setLikes(long likes) {
		this.likes = likes;
	}
	public long getNoOfComment() {
		return noOfComment;
	}
	public void setNoOfComment(long noOfComment) {
		this.noOfComment = noOfComment;
	}
	public boolean isPublic() {
		return isPublic;
	}
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	public String getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(String addedDate) {
		this.addedDate = addedDate;
	}
	public String getLastActiveDate() {
		return lastActiveDate;
	}
	public void setLastActiveDate(String lastActiveDate) {
		this.lastActiveDate = lastActiveDate;
	}
	public Blog(String id, String title, String subtitle, String authorId, List<String> images, List<String> contents,
			List<String> hashtags, long views, long likes, long noOfComment, boolean isPublic, String addedDate,
			String lastActiveDate) {
		super();
		this.id = id;
		this.title = title;
		this.subtitle = subtitle;
		this.authorId = authorId;
		this.images = images;
		this.contents = contents;
		this.hashtags = hashtags;
		this.views = views;
		this.likes = likes;
		this.noOfComment = noOfComment;
		this.isPublic = isPublic;
		this.addedDate = addedDate;
		this.lastActiveDate = lastActiveDate;
	}
	public Blog() {
		super();
	}
	
}
