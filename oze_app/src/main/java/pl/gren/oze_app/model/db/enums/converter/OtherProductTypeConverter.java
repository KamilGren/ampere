package pl.gren.oze_app.model.db.enums.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import pl.gren.oze_app.model.db.enums.OtherProductType;

@Converter(autoApply = true)
public class OtherProductTypeConverter implements AttributeConverter<OtherProductType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OtherProductType type) {
        return type == null ? null : type.getId();
    }

    @Override
    public OtherProductType convertToEntityAttribute(Integer id) {
        return id == null ? null : OtherProductType.findById(id).orElseThrow();
    }

}
