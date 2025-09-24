package pt.ulusofona.cd.primeiroprojeto.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CompanyResponse {
    private UUID id;
    private String companyName;
    private String email;
    private String address;
    private String description;
}