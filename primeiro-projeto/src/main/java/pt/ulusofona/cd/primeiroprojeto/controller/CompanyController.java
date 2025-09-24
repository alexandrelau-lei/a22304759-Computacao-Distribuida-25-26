package pt.ulusofona.cd.primeiroprojeto.controller;

import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import pt.ulusofona.cd.primeiroprojeto.mapper.CompanyMapper;
import pt.ulusofona.cd.primeiroprojeto.model.Company;
import pt.ulusofona.cd.primeiroprojeto.model.dto.CompanyRequest;
import pt.ulusofona.cd.primeiroprojeto.model.dto.CompanyResponse;
import pt.ulusofona.cd.primeiroprojeto.service.CompanyService;

import java.util.*;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {

    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CompanyResponse> create(
            @Valid @RequestBody CompanyRequest request
    ) {
        Company created = service.createCompany(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CompanyMapper.toResponse(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getById(@PathVariable UUID id) {
        Company company = service.getCompanyById(id);
        return ResponseEntity.ok(CompanyMapper.toResponse(company));
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getAll() {
        List<Company> companies = service.getAllCompanies();
        List<CompanyResponse> responseList = companies.stream()
                .map(CompanyMapper::toResponse)
                .toList();

        return ResponseEntity.ok(responseList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody CompanyRequest request
    ) {
        Company updated = service.updateCompany(id, request);
        return ResponseEntity.ok(CompanyMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }
}