package org.healthplus.basic.request;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.healthplus.basic.HelloData;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class RequestParamController {

  /*
   * 반환 값이 없다면 view를 조회하지 않는다.
   * 접속 url에 ? 다음에 오는 Query Param은 request에서 읽어올 수 있다.
   * GetMapping, PostMapping을 명시하지 않았을 때 겸해서 사용 가능
   * */
  @RequestMapping("/request-param-v1")
  public void requestParamV1(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));
    log.info("username {}, age {}", username, age);
    response.getWriter().write("ok");
  }

  /*
   * POST 요청 시 넘어오는 인자값과 변수명이 같다면 @Requestparam 생략 가능하지만
   * 가독성을 위해 넣어주자. required를 true는 반드시 값을 요구한다. 인자값이 넘어올 때
   * */
  @ResponseBody
  @RequestMapping("/request-param-v2")
  public String requestParamV2(
      @RequestParam(value = "username", required = true) String username,
      @RequestParam(value = "age", required = false) int age
  ) {
    log.info("username {}, age {}", username, age);
    return "OK";
  }

  @ResponseBody
  @RequestMapping("/request-param-default")
  public String requestParamDefault(
      @RequestParam(required = true, defaultValue = "guest") String username,
      @RequestParam(required = false, defaultValue = "-1") int age) {
    log.info("username={}, age={}", username, age);
    return "ok";
  }

  /*
   * parameter를 Map으로 한번에 조회할 수 있다.
   * */
  @ResponseBody
  @RequestMapping("/request-param-map")
  public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
    log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
    return "OK";
  }

  /*
   * ModelAttribute로 객체정보를 한번에 전달할 수 있다.
   *
   * */
  @ResponseBody
  @RequestMapping("/model-attrebute-v1")
  public String modelAttributeV1(@ModelAttribute HelloData helloData) {
    log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
    return "ModelAttribute OK";
  }
}
