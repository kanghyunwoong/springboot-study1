package com.woong.study.service.posts;

import com.woong.study.domain.posts.Posts;
import com.woong.study.domain.posts.PostsRepository;
import com.woong.study.web.dto.PostListResponseDto;
import com.woong.study.web.dto.PostResponseDto;
import com.woong.study.web.dto.PostSaveRequestDto;
import com.woong.study.web.dto.PostUpdateRequestDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostsRepository postsRepository;

  @Transactional
  public Long save(PostSaveRequestDto postSaveRequestDto) {
   return postsRepository.save(postSaveRequestDto.toEntity()).getId();
  }

  @Transactional
  public Long update(Long id, PostUpdateRequestDto postUpdateRequestDto) {
    Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없슴. id ="+id));
    posts.update(postUpdateRequestDto.getTitle(), postUpdateRequestDto.getContent());

    return id;
  }

  @Transactional
  public PostResponseDto findById(Long id) {
    Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없븜븜"));
    PostResponseDto postResponseDto = new PostResponseDto(entity);

    return postResponseDto;
  }

  @Transactional(readOnly = true)
  public List<PostListResponseDto> findAllDesc() {
    return postsRepository.findAllDesc().stream().map(PostListResponseDto::new).collect(Collectors.toList());
  }

  @Transactional
  public void delete(Long id) {
    Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없븜븜"));

    postsRepository.delete(posts);
  }
}
