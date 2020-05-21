package me.gon.demobootweb;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    //Formatter 이용을 위해서는 해당 설정이 필요하지만
    //SpringBoot 에서는 formatter파일을 Bean으로 등록만해주면 됨.

    /*  @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new PersonFormatter());
    }*/
}
