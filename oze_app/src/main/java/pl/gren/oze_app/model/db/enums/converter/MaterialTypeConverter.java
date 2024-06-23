package pl.gren.oze_app.model.db.enums.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import pl.gren.oze_app.model.db.enums.MaterialType;

@Converter(autoApply = true)
public class MaterialTypeConverter implements AttributeConverter<MaterialType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(MaterialType type) {
        return type == null ? null : type.getId();
    }

    @Override
    public MaterialType convertToEntityAttribute(Integer id) {
        return id == null ? null : MaterialType.findById(id).orElseThrow();
    }

}
