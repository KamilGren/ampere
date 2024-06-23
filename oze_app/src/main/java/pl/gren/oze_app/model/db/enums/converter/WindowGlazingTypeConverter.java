package pl.gren.oze_app.model.db.enums.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import pl.gren.oze_app.model.db.enums.WindowGlazingType;

@Converter(autoApply = true)
public class WindowGlazingTypeConverter implements AttributeConverter<WindowGlazingType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(WindowGlazingType type) {
        return type == null ? null : type.getId();
    }

    @Override
    public WindowGlazingType convertToEntityAttribute(Integer id) {
        return id == null ? null : WindowGlazingType.findById(id).orElseThrow();
    }

}
