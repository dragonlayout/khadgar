package com.dragonlayout.khadgar.interceptor;

import com.dragonlayout.khadgar.common.Response;
import com.dragonlayout.khadgar.common.ResponseCode;
import com.dragonlayout.khadgar.config.ControllerTestConfiguration;
import com.dragonlayout.khadgar.config.ControllerTestConfiguration.RequestParamObject;
import com.dragonlayout.khadgar.config.MockMvcTestConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 集成测试 {@link GlobalExceptionHandler} 全局异常处理
 * We should also describe code blocks in the Given, When and Then format.
 * In addition, it helps to differentiate the test into three parts: input, action and output.
 * First, the code block corresponding to the given section creates the test objects, mocks the data and arranges input.
 * Next, the code block for the when section represents a specific action or test scenario.
 * Likewise, the then section points out the output of the code, which is verified against the expected result using assertions.
 */
@WebMvcTest
@Import({MockMvcTestConfiguration.class, ControllerTestConfiguration.class})
public class GlobalExceptionHandlerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Test when using wrong http request method, {@link HttpRequestMethodNotSupportedException} will be handle, and
     * return {@link Response<Object>}
     */
    @Test
    public void givenWrongRequestMethod_whenHandleException_thenReturnHttpMethodNotSupport() throws Exception {
        Response<Object> expectResult = Response.fail(ResponseCode.Client.REQUEST_METHOD_INVALID, "POST");
        this.mockMvc.perform(MockMvcRequestBuilders.post("/handleHttpRequestMethodNotSupported"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof HttpRequestMethodNotSupportedException))
                .andExpect(content().string(objectMapper.writeValueAsString(expectResult)));
    }

    /**
     * Test when request param validation failed, {@link BindException} will be handle, and return {@link Response<>}
     */
    @Test
    public void givenWrongBind_whenHandleException_thenReturnRequestParametersInvalid() throws Exception {
        Response<Object> expectResult = Response.fail(ResponseCode.Client.REQUEST_PARAMETERS_INVALID, "");
        this.mockMvc.perform(MockMvcRequestBuilders.get("/handleRequestParamValidationFail")
                        .queryParam("age", "17"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof BindException))
        // TODO
//                .andExpect(content().string(Matchers.equalTo(objectMapper.writeValueAsString(expectResult))))
        ;
    }

    /**
     * Test when request body validation failed, {@link MethodArgumentNotValidException} will be handle, and return
     * {@link Response<>}
     */
    @Test
    public void givenWrongRequestMethod_whenHandleException_thenReturnMethodNotSupport() throws Exception {
        Response<Object> expectResult = Response.fail(ResponseCode.Client.REQUEST_PARAMETERS_INVALID, "POST");
        RequestParamObject requestParamObject = new RequestParamObject();
        requestParamObject.setAge(17);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/handleRequestBodyValidationFail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestParamObject)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
        // TODO
//                .andExpect(content().string(Matchers.equalTo(objectMapper.writeValueAsString(expectResult))))
        ;
    }

    /**
     * Test when method parameters validation failed, {@link ConstraintViolationException} will be handle, and return
     * {@link Response<>}
     */
    @Test
    public void givenInvalidMethodParam_whenHandleException_thenReturnConstraintViolation() throws Exception {
        Response<Object> expectResult = Response.fail(ResponseCode.Client.REQUEST_PARAMETERS_INVALID, "POST");
        this.mockMvc.perform(MockMvcRequestBuilders.get("/handleConstraintViolation")
                        .queryParam("age", "17"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof ConstraintViolationException))
        // TODO
//                .andExpect(content().string(Matchers.equalTo(objectMapper.writeValueAsString(expectResult))))
        ;
    }
}
