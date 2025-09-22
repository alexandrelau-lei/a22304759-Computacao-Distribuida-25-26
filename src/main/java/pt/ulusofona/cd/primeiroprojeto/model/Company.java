package pt.ulusofona.cd.primeiroprojeto.model;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import pt.ulusofona.cd.primeiroprojeto.repository.Identifiable;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class Company implements Identifiable {
    private UUID id;

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

    private Instant createdAt;
    private Instant updatedAt;
}
