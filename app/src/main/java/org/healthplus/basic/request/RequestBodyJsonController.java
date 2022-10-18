package org.healthplus.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.healthplus.basic.HelloData;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * {"username":"hello", "age":20} content-type: application/json
 */
@Slf4j
@Controller // view return 인데
public class RequestBodyJsonController {

  private ObjectMapper objectMapper = new ObjectMapper();

  @PostMapping("/request-body-json-v1")
  public void requestBodyJsonV1(HttpServletRequest request,
      HttpServletResponse response) throws IOException {
    ServletInputStream inputStream = request.getInputStream();// json type data input
    String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

    log.info("messageBody = {}", messageBody);
    HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
    log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());

    response.getWriter().write("OOK");
  }

  // responsebody를 통해 http body로 Return
  @ResponseBody
  @PostMapping("/request-body-json-v2")
  public String requestBodyJsonV2(@RequestBody String messageBody) throws
      IOException {
    HelloData data = objectMapper.readValue(messageBody, HelloData.class);
    log.info("username={}, age={}", data.getUsername(), data.getAge());
    return "ok";
  }

  /*
   * 문자로 변환하고 다시 json으로 변환하는 과정이 불편하다. @ModelAttribute처럼 한번에 객체로 변환할 수는 없을까?
   * HelloData에 @RequestBody 를 생략하면 @ModelAttribute 가 적용되어버린다. HelloData data @ModelAttribute HelloData data따라서 생략하면 HTTP 메시지 바디가 아니라 요청 파라미터를 처리하게 된다.
   * */
  @ResponseBody
  @PostMapping("/request-body-json-v3")
  public String requestBodyJsonV3(@RequestBody HelloData data) {
    log.info("username={}, age={}", data.getUsername(), data.getAge());
    return "ok";
  }

  /*
   * String을 반환하는 경우 - View or HTTP 메시지@ResponseBody 가 없으면
   *  response/hello 로 뷰 리졸버가 실행되어서 뷰를 찾고, 렌더링 한다. @ResponseBody 가 있으면 뷰 리졸버를 실행하지 않고, HTTP 메시지 바디에 직접 response/hello 라는 문자가 입력된다.
   * */
}
