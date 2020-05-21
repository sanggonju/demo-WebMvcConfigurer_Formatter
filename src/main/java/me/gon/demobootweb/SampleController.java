package me.gon.demobootweb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    /*
    @PathVariable 경로로 받을때
    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") Person person){
        return "hello " + person.getName();
    }*/


    //preHandler1 메서드에서 전처리작업
    //preHandler2
    //요청 처리
    //postHandler2
    //postHandler1
    //(뷰 랜더링) RestController의 경우는 생략됨
    //afterCompletion2
    //afterCompletion1
    //이런 세개의 전후 처리 Template를 제공함
    //postHandler와 afterComplition은 역순으로 호출됨.
    @GetMapping("/hello")
    public String hello(@RequestParam("id") Person person){
        return "hello " + person.getName();
    }
}
