package pl.gren.oze_app.model.dto.building;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildingInfoCreateDto {
    private String name;
    private Long clientId;
}
