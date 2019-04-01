package service.extraService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class Config {

    @Bean
    @Primary
    public Lasagna getLasagna(){
        return new Lasagna();
    }

    @Bean
    public Box getBox(){
        return new Box();
    }
}
