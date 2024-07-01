package pl.gren.oze_app.model.dto.contracts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantityUpdateDTO {
    private long contractId;
    private long productId;
    private int quantity;
}
