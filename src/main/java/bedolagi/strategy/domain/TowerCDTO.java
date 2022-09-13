package bedolagi.strategy.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TowerCDTO {
    private String towerId;

    private List<LetterCDTO> letters;
}