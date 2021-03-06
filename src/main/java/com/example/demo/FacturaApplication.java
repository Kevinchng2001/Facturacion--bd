package com.example.demo;

import java.io.FileNotFoundException;


import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.demo.controlador.Index;

@SpringBootApplication
public class FacturaApplication {

	public static void main(String[] args) throws FileNotFoundException{
		//SpringApplication.run(FacturaApplication.class, args);
		/*
		ConfigurableApplicationContext contexto = new SpringApplicationBuilder(FacturaApplication.class)
	    .headless(false)
	    .web(WebApplicationType.NONE)
	    .run(args);
		
		Principal p = contexto.getBean(Principal.class);
		p.GUI();*/
		
		ConfigurableApplicationContext contexto = new SpringApplicationBuilder(FacturaApplication.class)
			    .headless(false)
			    .web(WebApplicationType.NONE)
			    .run(args);

				Index index= contexto.getBean(Index.class);
				index.setVisible(true);
	}

}