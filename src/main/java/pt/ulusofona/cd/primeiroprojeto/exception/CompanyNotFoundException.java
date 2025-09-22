package pt.ulusofona.cd.primeiroprojeto.exception;

import java.util.UUID;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(UUID id) {
        super("Product not found with id: " + id);
    }
}
