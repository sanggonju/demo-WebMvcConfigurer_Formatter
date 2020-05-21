package me.gon.demobootweb;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)//junit4 @RunWith(SpringRunner.class)가 junit5에서는 이렇게 변경됨.
//@WebMvcTest
@SpringBootTest
@AutoConfigureMockMvc
//formatter를 bean으로 등록하여 test를 실행하면 오류가 나는데 @WebMvcTest 어노테이션을 사용하면
//web과 관련된 설정점보만 가져오기때문에(?) 오류가 발생하게 된다.
//이럴때는 @SpringBootTest로 변경하면 되는데 이럴경우 MockMvc가 자동으로 bean으로 등록되지 않아
//@AutoConfigureMockMvc으로 빈 설정을 해줘야함.
public class SampleControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    PersonRepository personRepository;
    @Test
    public void hello() throws Exception{
        Person person = new Person();
        person.setName("keesun");
        Person savedPerson = personRepository.save(person);
        /*
        pathVariable
        this.mockMvc.perform(MockMvcRequestBuilders.get("/hello/keesun"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().string("hello keesun"));*/
        //requestParam
        this.mockMvc.perform(MockMvcRequestBuilders.get("/hello").param("id",savedPerson.getId().toString()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().string("hello keesun"));
    }

    @Test
    public void helloStatic() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/index.html"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("hello index")));
    }

    @Test
    public void mobileStatic() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/mobile/index.html"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk( ))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("mobile index")))
                .andExpect(MockMvcResultMatchers.header().exists(HttpHeaders.CACHE_CONTROL));
    }
}