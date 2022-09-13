package bedolagi.strategy.service;

import bedolagi.strategy.domain.LetterCDTO;
import bedolagi.strategy.domain.TowerCDTO;
import bedolagi.strategy.feign.TowerFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HackathonService {
    @Value("${hackathon.token}")
    private String token;
    private final TowerFeignClient towerFeignClient;

    private String towerId = "asdasjdha123asd";

    public char[][][] generateTower() {
        char[][][] tower = {
                {{'к','о', 'т'}, {0, 0, 0}, {0, 0, 0}},
                {{0, 0, 0}, {0, 0, 0},{0, 0, 0}},
                {{0, 0, 0}, {0, 0, 0},{0, 0, 0}}
        };
        return tower;
    }

    @PostConstruct
    void foo() {

        List<LetterCDTO> letters = new ArrayList<>();

        letters.add(new LetterCDTO(0,0,0, "к"));
        letters.add(new LetterCDTO(1,0,0, "о"));
        letters.add(new LetterCDTO(2,0,0, "т"));

        TowerCDTO towerCDTO = TowerCDTO.builder()
                .towerId(generateTowerId())
                .letters(towerToLetter(generateTower()))
                .build();

//        ResponseEntity<String> status = towerFeignClient.getStatus(token, towerId);
//        ResponseEntity<String> response = towerFeignClient.sendTower(token, towerCDTO);
    }

    public static String generateTowerId()
    {
        LocalDateTime genTime = LocalDateTime.now();
        return "tower-" + String.format("%s-%s-%s", genTime.getHour(), genTime.getMinute(), genTime.getSecond());
    }

    public static List<LetterCDTO> towerToLetter(char[][][] tower) {
        List<LetterCDTO> letters = new ArrayList<>();
        for(int i = 0; i < tower.length; i++) {
            for(int j = 0; j < tower[0].length; j++) {
                for(int k = 0; k < tower[0][0].length; k++) {
                    if(tower[i][j][k] != 0) {
                        letters.add(new LetterCDTO(i, j, k, "" + tower[i][j][k]));
                    }
                }
            }
        }
        return letters;
    }
}