package bedolagi.strategy.service;

import bedolagi.strategy.domain.LetterCDTO;
import bedolagi.strategy.domain.TowerCDTO;
import bedolagi.strategy.feign.TowerFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HackathonService {
    @Value("${hackathon.token}")
    private String token;
    private final TowerFeignClient towerFeignClient;

    private String towerId = "asdasjdha123asd";

    @PostConstruct
    void foo() {

        List<LetterCDTO> letters = new ArrayList<>();

        letters.add(new LetterCDTO(0,0,0, "к"));
        letters.add(new LetterCDTO(1,0,0, "о"));
        letters.add(new LetterCDTO(2,0,0, "т"));

        TowerCDTO towerCDTO = TowerCDTO.builder()
                .towerId(towerId)
                .letters(letters)
                .build();

//        ResponseEntity<String> status = towerFeignClient.getStatus(token, towerId);
        ResponseEntity<String> response = towerFeignClient.sendTower(token, towerCDTO);
    }
}