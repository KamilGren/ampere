package pl.gren.oze_app.model.db.entity.embedded;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class TemperatureSpecification {
    private BigDecimal negative20C;
    private BigDecimal negative15C;
    private BigDecimal negative7C;
    private BigDecimal negative2C;
    private BigDecimal positive2C;
    private BigDecimal positive7C;
    private BigDecimal positive12C;
    private BigDecimal positive15C;
    private BigDecimal positive20C;
}
