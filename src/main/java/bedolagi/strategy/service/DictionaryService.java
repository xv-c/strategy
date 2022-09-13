package bedolagi.strategy.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DictionaryService {


    @SneakyThrows
    public Set<String> getDictionary() {
        BufferedReader fileReader = new BufferedReader(new FileReader("src/main/resources/trash/words_only.json"));
        return fileReader.lines()
                .collect(Collectors.toSet());
    }
}