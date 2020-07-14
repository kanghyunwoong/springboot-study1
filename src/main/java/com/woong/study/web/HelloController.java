package com.woong.study.web;

import com.woong.study.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/study")
public class HelloController {

  @GetMapping("/hello/dto")
  public HelloResponseDto hello(@RequestParam("name") String name, @RequestParam("amount") int amount) {

    return new HelloResponseDto(name,amount);
  }

}
