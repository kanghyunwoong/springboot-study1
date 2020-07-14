package com.woong.study.web;

import com.woong.study.service.posts.PostService;
import com.woong.study.web.dto.PostResponseDto;
import com.woong.study.web.dto.PostSaveRequestDto;
import com.woong.study.web.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class PostsApiController {

  private final PostService postService;

  @PostMapping("/posts")
  public Long save(@RequestBody PostSaveRequestDto postSaveRequestDto) {
    return postService.save(postSaveRequestDto);
  }

  @PutMapping("/posts/{id}")
  public Long update(@PathVariable Long id, @RequestBody PostUpdateRequestDto postUpdateRequestDto) {
    return postService.update(id, postUpdateRequestDto);
  }

  @GetMapping("/posts/{id}")
  public PostResponseDto findById(@PathVariable Long id) {
    return postService.findById(id);
  }


  @DeleteMapping("/posts/{id}")
  public Long delete(@PathVariable Long id) {
    postService.delete(id);
    return id;
  }
}
