package wanderhub.server;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelConfig {

    //ModelMapper
    //DTO <-> Entity Type Change
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
