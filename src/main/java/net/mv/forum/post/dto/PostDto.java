package net.mv.forum.post.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.mv.forum.post.domain.Post;
import net.mv.forum.serializer.JsonDateSerializer;

public class PostDto {

	private Long id;
	private Long forumId;
	private String author;
	private String text;
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date created;
	
	public PostDto(){
		
	}
	
	

	public PostDto(Post post) {
		this.id = post.getId();
		this.forumId = post.getForum().getId();
		this.author = post.getUser().getUsername();
		this.text = post.getText();
		this.created = post.getDateAuthored();
	}



	@Override
	public String toString() {
		return "PostDto [id=" + id + ", author=" + author + ", text=" + text + ", created=" + created + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

}
