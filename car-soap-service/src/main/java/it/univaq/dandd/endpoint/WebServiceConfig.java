package it.univaq.dandd.endpoint;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true); // Enable transforming WSDL locations
        // Set up the servlet for handling SOAP requests at "/ws/*"
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    // WSDL will be exposed at "<host>:<port>/ws/carSellers.wsdl"
    @Bean(name = "carSellers")
    public Wsdl11Definition defaultWsdl11Definition() {
        SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
        
        // Reference the WSDL file for CarSellerService
        wsdl11Definition.setWsdl(new ClassPathResource("/wsdl/cars_sellers.wsdl"));
        return wsdl11Definition;
    }
}
