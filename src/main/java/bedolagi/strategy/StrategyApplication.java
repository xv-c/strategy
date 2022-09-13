package bedolagi.strategy;

import bedolagi.strategy.impl.GeneralBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableFeignClients
public class StrategyApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(StrategyApplication.class);
	}

}