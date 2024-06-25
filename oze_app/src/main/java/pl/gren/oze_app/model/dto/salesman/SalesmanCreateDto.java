package pl.gren.oze_app.model.dto.salesman;

import lombok.Data;

@Data
public class SalesmanCreateDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private Integer role;
}
