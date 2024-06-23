package pl.gren.oze_app.model.db.enums.converter;

import jakarta.persistence.Converter;
import jakarta.persistence.AttributeConverter;
import pl.gren.oze_app.model.db.enums.FuelType;

@Converter(autoApply = true)
public class FuelTypeConverter implements AttributeConverter<FuelType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(FuelType type) {
        return type == null ? null : type.getId();
    }

    @Override
    public FuelType convertToEntityAttribute(Integer id) {
        return id == null ? null : FuelType.findById(id).orElseThrow();
    }

}
