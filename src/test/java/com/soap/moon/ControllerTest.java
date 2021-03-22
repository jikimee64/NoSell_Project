package com.soap.moon;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@AutoConfigureMockMvc
//@ActiveProfiles("local")
@Transactional
@SpringBootTest(properties = "spring.config.location=" +
    "classpath:/application-dev.yml" +
    ",classpath:/application-secret.yml")
@ExtendWith({MockitoExtension.class, SpringExtension.class})
public class ControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {

    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",
            "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJub3NlbGxAbm9zZWxsLmNvbSIsImF1dGgiOiJST0xFX1VTRVIiLCJleHAiOjE2MTQ3NjM4ODd9.If8pyaec_UHS4Rbo9TkhNfJQ_mUVj62lBNoXesuYWYCr8OIDk5YnIc7Yw4-RDDbHsZKy57Jt5U6VgRq3g6ZmwQ");
        return httpHeaders;
    }

    public ResultActions read(final String uri, final ResultMatcher expect) throws Exception {
        return mockMvc.perform(get(uri)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(expect)
            .andDo(print());
    }

    public ResultActions readInHeader(final String uri, final ResultMatcher expect)
        throws Exception {
        return mockMvc.perform(get(uri)
            .accept(MediaType.APPLICATION_JSON)
            .headers(getHttpHeaders()))
            .andExpect(status().isOk())
            .andExpect(expect)
            .andDo(print());
    }

    public ResultActions readByPathVariables(final String uri, final Long id,
        final ResultMatcher expect) throws Exception {
        return mockMvc.perform(get(uri, id)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(expect)
            .andDo(print());
    }

    public ResultActions createOrReadByJsonParams(final String uri, final String jsonParams,
        final ResultMatcher expect) throws Exception {
        return mockMvc.perform(post(uri)
            .content(jsonParams)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(expect)
            .andDo(print());
    }


}
