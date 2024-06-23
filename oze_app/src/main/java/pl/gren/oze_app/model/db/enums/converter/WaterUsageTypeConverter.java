package pl.gren.oze_app.model.db.enums.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import pl.gren.oze_app.model.db.enums.WaterUsageType;

@Converter(autoApply = true)
public class WaterUsageTypeConverter implements AttributeConverter<WaterUsageType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(WaterUsageType type) {
        return type == null ? null : type.getId();
    }

    @Override
    public WaterUsageType convertToEntityAttribute(Integer id) {
        return id == null ? null : WaterUsageType.findById(id).orElseThrow();
    }

}
