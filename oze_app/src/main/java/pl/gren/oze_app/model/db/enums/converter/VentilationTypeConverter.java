package pl.gren.oze_app.model.db.enums.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import pl.gren.oze_app.model.db.enums.VentilationType;

@Converter(autoApply = true)
public class VentilationTypeConverter implements AttributeConverter<VentilationType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(VentilationType type) {
        return type == null ? null : type.getId();
    }

    @Override
    public VentilationType convertToEntityAttribute(Integer id) {
        return id == null ? null : VentilationType.findById(id).orElseThrow();
    }

}
