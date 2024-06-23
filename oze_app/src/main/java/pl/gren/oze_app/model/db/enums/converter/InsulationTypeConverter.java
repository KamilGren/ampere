package pl.gren.oze_app.model.db.enums.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import pl.gren.oze_app.model.db.enums.InsulationType;

@Converter(autoApply = true)
public class InsulationTypeConverter implements AttributeConverter<InsulationType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(InsulationType type) {
        return type == null ? null : type.getId();
    }

    @Override
    public InsulationType convertToEntityAttribute(Integer id) {
        return id == null ? null : InsulationType.findById(id).orElseThrow();
    }

}
