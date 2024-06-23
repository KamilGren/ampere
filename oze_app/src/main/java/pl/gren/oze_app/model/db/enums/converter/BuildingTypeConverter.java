package pl.gren.oze_app.model.db.enums.converter;

import jakarta.persistence.Converter;
import jakarta.persistence.AttributeConverter;
import pl.gren.oze_app.model.db.enums.BuildingType;

@Converter(autoApply = true)
public class BuildingTypeConverter implements AttributeConverter<BuildingType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(BuildingType type) {
        return type == null ? null : type.getId();
    }

    @Override
    public BuildingType convertToEntityAttribute(Integer id) {
        return id == null ? null : BuildingType.findById(id).orElseThrow();
    }

}
