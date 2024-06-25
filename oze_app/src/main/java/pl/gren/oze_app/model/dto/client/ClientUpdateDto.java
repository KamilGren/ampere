package pl.gren.oze_app.model.dto.client;

import lombok.Data;

@Data
public class ClientUpdateDto {
    private String name;
    private String address;
    private String note;
    private String phone;
    private Long salesmanId;
}
