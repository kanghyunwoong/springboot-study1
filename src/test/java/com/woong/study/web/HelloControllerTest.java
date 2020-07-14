package com.woong.study.web;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest
public class HelloControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void hello() throws Exception {
    String hello =  "hello";
    mockMvc.perform(get("/study/hello"))
        .andExpect(status().isOk())
        .andExpect(content().string(hello));
  }

  @Test
  public void 헬로테스트2() throws Exception {
    String name = "teset";
    int amount = 1000;

    mockMvc.perform(get("/study/hello/dto")
        .param("name",name)
        .param("amount",String.valueOf(amount)))
    .andExpect(status().isOk())
    .andExpect(jsonPath("$.name",is(name)))
    .andExpect(jsonPath("$.amount",is(amount)));


  }
}