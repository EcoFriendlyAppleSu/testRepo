package org.healthplus.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MappingController {

  @GetMapping({"/hello-basic", "/hello-go"})
  public String helloBasic() {
    log.info("helloworld");
    return "MappingController has two URL";
  }

  @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
  public String mappingGetV1() {
    log.info("mappingGetV1");
    return "ok";
  }

  @GetMapping("/mapping-get-v2")
  public String mappingGetV2() {
    log.info("mappingGetV2");
    return "ok";
  }

  @GetMapping("/mapping/{userId}")
  public String mappingPath(@PathVariable("userId") String data) {
    log.info("PathVariable Data = {}", data);
    return "OK";
  }

  @GetMapping("/mapping/{userId}/orders/{age}")
  public String mappingPath2(@PathVariable String userId, @PathVariable Integer age) {
    log.info("PathVar UserId = {}, Age = {}", userId, age);
    return "OK!";
  }

  @PostMapping(value = "/mapping-consume", consumes = "application/json")
  public String mappingConsumes() {
    log.info("mappingConsumes");
    return "ok";
  }
}
