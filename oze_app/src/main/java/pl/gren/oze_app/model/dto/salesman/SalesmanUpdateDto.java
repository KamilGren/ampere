package pl.gren.oze_app.model.dto.salesman;

import lombok.Data;

@Data
public class SalesmanUpdateDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Integer role;
}
