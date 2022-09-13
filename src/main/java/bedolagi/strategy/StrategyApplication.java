package bedolagi.strategy;

import bedolagi.strategy.impl.GeneralBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class StrategyApplication {

	public static void main(String[] args) throws Exception {
		BufferedReader fileReader = new BufferedReader(new FileReader("src/main/resources/trash/words_only.json"));
		List<String> words = fileReader.lines()
				.collect(Collectors.toList());

		new GeneralBuilder(words).build();
	}

}
