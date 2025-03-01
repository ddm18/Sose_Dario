package it.univaq.dandd.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.generated.CarSellerPortType;
import com.example.generated.CarSellerService;

@Configuration
public class SoapClientConfig {

    @Bean
    public CarSellerPortType carSellerService() {
        // Create the client proxy for the SOAP service using the generated implementation class
        return new CarSellerService().getCarSellerPort();
    }
}
