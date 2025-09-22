package pt.ulusofona.cd.primeiroprojeto.repository;

import org.springframework.stereotype.Repository;
import pt.ulusofona.cd.primeiroprojeto.model.Company;

import java.util.Optional;

@Repository
public class CompanyRepository extends InMemoryRepository<Company> {

    public Optional<Company> findByNameIgnoreCase(String name) {
        return findAll().stream()
                .filter(c -> c.getCompanyName().equalsIgnoreCase(name))
                .findFirst();
    }

    public boolean existsByNameIgnoreCase(String name) {
        return findByNameIgnoreCase(name).isPresent();
    }

    public Optional<Company> findByEmailIgnoreCase(String email) {
        return findAll().stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public boolean existsByEmailIgnoreCase(String email) {
        return findByEmailIgnoreCase(email).isPresent();
    }
}
