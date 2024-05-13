package pl.gren.oze_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.gren.oze_app.repository.HeatPumpRepository;
import pl.gren.oze_app.repository.ProductRepository;


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
