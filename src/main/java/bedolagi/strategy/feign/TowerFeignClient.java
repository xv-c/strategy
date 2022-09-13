package bedolagi.strategy.feign;

import bedolagi.strategy.domain.TowerCDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "tower", url = "https://dtower-api.datsteam.dev/towers")
public interface TowerFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "")
    ResponseEntity<String> sendTower(@RequestHeader("token") String token, @RequestBody TowerCDTO tower);


    @RequestMapping(method = RequestMethod.GET, value = "/{towerId}")
    ResponseEntity<String> getStatus(@RequestHeader("token") String token, @PathVariable("towerId") String towerId);
}