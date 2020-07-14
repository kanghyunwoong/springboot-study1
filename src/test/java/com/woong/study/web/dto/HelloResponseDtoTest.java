package com.woong.study.web.dto;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class HelloResponseDtoTest {

  @Test
  public void 롬복테스트() {
    //given
    String name ="test";
    int amount = 1000;

    //when
    HelloResponseDto helloResponseDto = new HelloResponseDto(name, amount);
    //then
    assertThat(helloResponseDto.getName()).isEqualTo(name);
    assertThat(helloResponseDto.getAmount()).isEqualTo(amount);

  }
}