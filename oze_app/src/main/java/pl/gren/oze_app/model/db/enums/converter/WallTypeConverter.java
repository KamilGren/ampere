package pl.gren.oze_app.model.db.enums.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import pl.gren.oze_app.model.db.enums.WallType;

@Converter(autoApply = true)
public class WallTypeConverter implements AttributeConverter<WallType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(WallType type) {
        return type == null ? null : type.getId();
    }

    @Override
    public WallType convertToEntityAttribute(Integer id) {
        return id == null ? null : WallType.findById(id).orElseThrow();
    }

}


