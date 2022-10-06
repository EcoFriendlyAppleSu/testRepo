package org.healthplus.basic.request;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RequestHttpMessageBodyController {

  @PostMapping("/request-body-string-v1")
  public void requestBodyString(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    ServletInputStream inputStream = request.getInputStream();
    String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

    log.info("messagebody = {}", messageBody); // console log
  }

  /*
   * OutputStream과 @RestController는 둘 다 body에 정보를 던진다.
   * */
  @PostMapping("/request-body-string-v2")
  public void requestBodyStringV2(InputStream inputStream, Writer responseWriter)
      throws IOException {
    String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
    log.info("messagebody = {}", messageBody);
    responseWriter.write("ok");
  }

  /*
   * HttpEntity : Http header, body 정보를 편리하게 조회
   * 메세지 바디 정보를 직접 조회 @RequestParam과 @ModelAttribute와는 상황 없는 것
   * 응답에서 HttpEntity 사용 시 body에 직접 반환 (like RestController)
   * */
  @PostMapping("/request-body-string-v3")
  public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) {
    String messageBody = httpEntity.getBody();// http message body get
    log.info("messageBody = {}", messageBody);
    return new HttpEntity<>("OK");
  }


  @PostMapping("/request-body-string-v4")
  public String requestBodyStringV4(@RequestBody String messageBody) {
    log.info("messageBody = {}", messageBody);
    return "OK";
  }
}
