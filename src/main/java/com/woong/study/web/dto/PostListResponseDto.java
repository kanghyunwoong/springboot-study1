package com.woong.study.web.dto;

import com.woong.study.domain.posts.Posts;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class PostListResponseDto {

  private Long id;
  private String title;
  private String author;
  private LocalDateTime modifiedDate;

  public PostListResponseDto(Posts entity) {
    this.id = entity.getId();
    this.title = entity.getTitle();
    this.author = entity.getAuthor();
    this.modifiedDate = entity.getModifiedDate();
  }

}
