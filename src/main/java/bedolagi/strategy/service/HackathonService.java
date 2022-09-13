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

    private final TowerBuilderService towerBuilderService;

    private String towerId = "asdasjsddha123asd";

    @PostConstruct
    void foo() {

        TowerCDTO towerCDTO = towerBuilderService.buildTower(towerId);

//        ResponseEntity<String> status = towerFeignClient.getStatus(token, towerId);
//        ResponseEntity<String> response = towerFeignClient.sendTower(token, towerCDTO);
    }
}