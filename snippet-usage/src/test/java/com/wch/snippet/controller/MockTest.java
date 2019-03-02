package com.wch.snippet.controller;

import com.wch.snippet.SpringTestBase;
import org.apache.http.entity.ContentType;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * @author wch
 */
public class MockTest extends SpringTestBase {

    @Resource
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeClass
    public void BeforeClass() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    public void testQuery() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/multi")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    public void testError() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/error")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError()).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println(response.getStatus());
        System.out.println(response.getContentAsString());
    }

    public void testUpload() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.fileUpload("/file/upload")
                .file(new MockMultipartFile("file", "file.txt",
                        ContentType.MULTIPART_FORM_DATA.getMimeType(),
                        "text".getBytes(StandardCharsets.UTF_8))))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
