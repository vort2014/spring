package io.github.vort2014.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by vort on 09.07.2017.
 */
@RunWith(SpringRunner.class)

// alternative to @ContextConfiguration, doesn't work if there is no @RunWith(SpringRunner.class)
// starts on server.port = 8000, can be also RANDOM_PORT
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

// the full Spring application context is started, but without the tomcat server
@SpringBootTest

// replacement of  @Autowired protected MockMvc mockMvc without @Before
@AutoConfigureMockMvc

// you can use this to start only web layer (@Controller beans)
// instead of @SpringBootTest + @AutoConfigureMockMvc
//@WebMvcTest

// add snippets for restdocs as replacement for JUnitRestDocumentation
@AutoConfigureRestDocs(outputDir = "target/snippets")

// all database changes will be rolled back after end of any test
@Transactional
@ActiveProfiles("test")
public abstract class ApplicationIT {

    protected static String PATH = "{ClassName}/{methodName}";

//    @Rule
//    public JUnitRestDocumentation jUnitRestDocumentation = new JUnitRestDocumentation("target/snippets");
//    @Autowired
//    protected WebApplicationContext webApplicationContext;
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;

//    @Before
//    public void setUp() {
//        mockMvc = webAppContextSetup(webApplicationContext)
//                .apply(documentationConfiguration(jUnitRestDocumentation))    // we use @AutoConfigureRestDocs
//                .alwaysDo(document(PATH, preprocessResponse(prettyPrint())))  // overrides by concrete test
//                .build();
//    }

    protected String getJson(String fileName) throws IOException {
        try (InputStream inputStream = getClass().getResourceAsStream(fileName)) {
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        }
    }
}