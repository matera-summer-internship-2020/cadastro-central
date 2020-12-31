package com.matera.cadastrocentral;

import com.matera.cadastrocentral.address.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CadastroCentralApplication {


	public static void main(String[] args) {
		SpringApplication.run(CadastroCentralApplication.class, args);
	}

}
