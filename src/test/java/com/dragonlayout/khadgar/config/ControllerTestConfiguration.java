package com.dragonlayout.khadgar.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@TestConfiguration
public class ControllerTestConfiguration {

    @RestController
    static class TestController {

        @GetMapping("/handleHttpRequestMethodNotSupported")
        public void handleHttpRequestMethodNotSupported() {}

        @GetMapping("/handleRequestParamValidationFail")
        public void handleRequestParamValidationFail(@Valid RequestParamObject requestParamObject) {}

        @PostMapping("/handleRequestBodyValidationFail")
        public void handleRequestBodyValidationFail(@RequestBody @Valid RequestParamObject requestParamObject) {}
    }

    @RestController
    @Validated
    static class TestConstraintViolationController {
        @GetMapping("/handleConstraintViolation")
        public void handleConstraintViolation(@RequestParam @Min(18) Integer age) {}
    }

    public static class RequestParamObject {
        @Min(18)
        private Integer age;

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
