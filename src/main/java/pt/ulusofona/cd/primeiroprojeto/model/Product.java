package pt.ulusofona.cd.primeiroprojeto.model;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import pt.ulusofona.cd.primeiroprojeto.repository.Identifiable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class Product implements Identifiable {
    private UUID id;

    @NotBlank
    @Size(min = 3, max = 120)
    private String name;

    private String description;

    @NotBlank
    private String sku;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal price;

    @Min(0)
    private int stock;

    private String currency = "EUR";

    private Instant createdAt;
    private Instant updatedAt;
}