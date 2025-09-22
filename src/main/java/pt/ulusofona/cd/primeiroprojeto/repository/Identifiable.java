package pt.ulusofona.cd.primeiroprojeto.repository;

import java.util.UUID;

public interface Identifiable {
    UUID getId();
    void setId(UUID id);
}