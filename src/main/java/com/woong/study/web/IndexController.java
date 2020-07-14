package com.woong.study.web;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.woong.study.service.posts.PostService;
import com.woong.study.web.dto.PostResponseDto;
import com.woong.study.web.dto.PostSaveRequestDto;
import com.woong.study.web.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class IndexController {

  private final PostService postService;

  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("posts",postService.findAllDesc());
    return "index";
  }

  @GetMapping("/posts/save")
  public String postSave() {
    return "posts-save";
  }

  @GetMapping("/posts/update/{id}")
  public String postsUpdate(Model model,@PathVariable Long id) {
    PostResponseDto postResponseDto = postService.findById(id);
    model.addAttribute("post",postResponseDto);
    return "posts-update";
  }


}
