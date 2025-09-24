package pt.ulusofona.cd.primeiroprojeto.model.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyRequest {

    @NotBlank
    @Size(min = 3, max = 120)
    private String companyName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String address;

    @Size(max = 500)
    private String description;
}
