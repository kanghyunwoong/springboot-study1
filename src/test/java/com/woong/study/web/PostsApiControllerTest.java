package com.woong.study.web;

import static org.assertj.core.api.Assertions.assertThat;

import com.woong.study.domain.posts.Posts;
import com.woong.study.domain.posts.PostsRepository;
import com.woong.study.web.dto.PostSaveRequestDto;
import com.woong.study.web.dto.PostUpdateRequestDto;
import java.util.List;
import javax.xml.ws.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private PostsRepository postsRepository;

  @Test
  public void 등록테스트() {
    //given
    String title ="title";
    String content = "내용";
    String author = "작가";

    PostSaveRequestDto postSaveRequestDto = PostSaveRequestDto.builder().title(title).content(content).author(author).build();

    //when
    String url  = "http://localhost:"+port+"/api/v1/posts";
    ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,postSaveRequestDto,Long.class);


    //then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isGreaterThan(0L);

    List<Posts>  posts = postsRepository.findAll();

    assertThat(posts.get(0).getTitle()).isEqualTo(title);
    assertThat(posts.get(0).getContent()).isEqualTo(content);
    assertThat(posts.get(0).getAuthor()).isEqualTo(author);

  }

  @Test
  public void 수정_테스트() {
    //given
    Posts savedPosts =  postsRepository.save(Posts.builder().title("제목").content("내용").author("작가").build());

    Long updateid = savedPosts.getId();

    String url = "http://localhost:"+port+"/api/v1/posts/"+updateid;

    String aTitle = "제제목";
    String aContent = "내내용";
    String aAuthor = "작작가";

    PostUpdateRequestDto postUpdateRequestDto = PostUpdateRequestDto.builder().title(aTitle).content(aContent).build();

    HttpEntity<PostUpdateRequestDto> requestDtoHttpEntity = new HttpEntity<>(postUpdateRequestDto);

    //when
    ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT,requestDtoHttpEntity,Long.class);


    //then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isGreaterThan(0L);

    List<Posts> postsList = postsRepository.findAll();

    assertThat(postsList.get(0).getTitle()).isEqualTo(aTitle);
    assertThat(postsList.get(0).getContent()).isEqualTo(aContent);


  }
}