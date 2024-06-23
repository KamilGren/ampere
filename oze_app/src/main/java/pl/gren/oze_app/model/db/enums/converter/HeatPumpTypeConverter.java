package pl.gren.oze_app.model.db.enums.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import pl.gren.oze_app.model.db.enums.HeatPumpType;

@Converter(autoApply = true)
public class HeatPumpTypeConverter implements AttributeConverter<HeatPumpType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(HeatPumpType type) {
        return type == null ? null : type.getId();
    }

    @Override
    public HeatPumpType convertToEntityAttribute(Integer id) {
        return id == null ? null : HeatPumpType.findById(id).orElseThrow();
    }

}
