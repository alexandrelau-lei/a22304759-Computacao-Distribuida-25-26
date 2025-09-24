package pt.ulusofona.cd.primeiroprojeto.mapper;

import pt.ulusofona.cd.primeiroprojeto.model.Company;
import pt.ulusofona.cd.primeiroprojeto.model.dto.CompanyRequest;
import pt.ulusofona.cd.primeiroprojeto.model.dto.CompanyResponse;

public class CompanyMapper {

    public static Company toEntity(CompanyRequest dto) {
        Company c = new Company();
        c.setCompanyName(dto.getCompanyName().trim());
        c.setEmail(dto.getEmail().toLowerCase());
        c.setAddress(dto.getAddress());
        c.setDescription(dto.getDescription());
        return c;
    }

    public static CompanyResponse toResponse(Company entity) {
        CompanyResponse r = new CompanyResponse();
        r.setId(entity.getId());
        r.setCompanyName(entity.getCompanyName());
        r.setEmail(entity.getEmail());
        r.setAddress(entity.getAddress());
        r.setDescription(entity.getDescription());
        return r;
    }
}