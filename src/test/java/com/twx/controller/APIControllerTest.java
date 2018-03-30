package com.twx.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilderSupport;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class APIControllerTest {

    private MockMvc mvc;

    @Autowired
    private APIController controller;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    @Test
    public void query() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders.post("/api/query")
                .param("warningTitle","");
        MvcResult mvcResult = mvc.perform(rb).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
    }

    @Test
    public void dashbord() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders.get("/api/dashbord");
        MvcResult mvcResult = mvc.perform(rb).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
    }

}