package pl.gren.oze_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class OzeAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(OzeAppApplication.class, args);

//		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(OzeAppApplication.class, args);
//
//		ProductRepository productRepository = configurableApplicationContext.getBean(ProductRepository.class);
//		ProductRepository heatPumpRepository = configurableApplicationContext.getBean(HeatPumpRepository.class);

	}

}
