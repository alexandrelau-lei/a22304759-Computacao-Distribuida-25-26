package pt.ulusofona.cd.primeiroprojeto.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pt.ulusofona.cd.primeiroprojeto.exception.CompanyNotFoundException;
import pt.ulusofona.cd.primeiroprojeto.mapper.CompanyMapper;
import pt.ulusofona.cd.primeiroprojeto.model.Company;
import pt.ulusofona.cd.primeiroprojeto.model.dto.CompanyRequest;
import pt.ulusofona.cd.primeiroprojeto.repository.CompanyRepository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository repo;

    // Create
    public Company createCompany(CompanyRequest request) {
        if (repo.existsByNameIgnoreCase(request.getCompanyName())) {
            throw new IllegalArgumentException("Company name must be unique");
        }
        if (repo.existsByEmailIgnoreCase(request.getEmail())) {
            throw new IllegalArgumentException("Company email must be unique");
        }

        Company company = CompanyMapper.toEntity(request);

        company.setId(UUID.randomUUID());
        company.setCreatedAt(Instant.now());
        company.setUpdatedAt(Instant.now());

        return repo.save(company);
    }

    // Get by ID
    public Company getCompanyById(UUID id) {
        return repo.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
    }

    // Get all
    public List<Company> getAllCompanies() {
        return repo.findAll();
    }

    // Update
    public Company updateCompany(UUID id, CompanyRequest request) {
        Company existing = getCompanyById(id);

        existing.setCompanyName(request.getCompanyName().trim());
        existing.setAddress(request.getAddress());
        existing.setDescription(request.getDescription());

        if (!existing.getEmail().equalsIgnoreCase(request.getEmail())
                && repo.existsByEmailIgnoreCase(request.getEmail())) {
            throw new IllegalArgumentException("Company email must be unique");
        }
        existing.setEmail(request.getEmail().toLowerCase());

        existing.setUpdatedAt(Instant.now());

        return repo.save(existing);
    }

    // Delete
    public void deleteCompany(UUID id) {
        if (!repo.findById(id).isPresent()) {
            throw new CompanyNotFoundException(id);
        }
        repo.deleteById(id);
    }
}
