package com.doctors.athome.repos;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.doctors.athome.repos.entities.OrganizationDTO;

public interface OrganizationRepository extends MongoRepository<OrganizationDTO, Long> {

}
