package com.example.ApiTourist;

import com.example.ApiTourist.repository.PaysRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class ApiTouristApplication implements CommandLineRunner {

	private  PaysRepository paysRepository;



	public  ApiTouristApplication(PaysRepository paysRepository){
		this.paysRepository =paysRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiTouristApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (paysRepository.findAll().size() == 0){
			paysRepository.ajoutpays();
		}


	}
}
