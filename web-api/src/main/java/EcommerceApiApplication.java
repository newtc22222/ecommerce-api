import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SuppressWarnings("SpringBootApplicationSetup")
@SpringBootApplication
@ComponentScan(basePackages = { "com.hcmute.ecom" })
public class EcommerceApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(EcommerceApiApplication.class);
    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
//            }
//        };
//    }
}
