package com.doctors.athome.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.doctors.athome.repos.entities.PatientDTO;

@Service
public class PatientServiceImpl implements PatientService {
	
	
	private final MongoTemplate mongoTemplate;
	
	@Autowired
	public PatientServiceImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public List<PatientDTO> findAll() {
		return mongoTemplate.findAll(PatientDTO.class);
	}

	@Override
	public PatientDTO findByClinicianID(String clinicianID) {
		PatientDTO patient = null;
		Query query = new Query();
		query.addCriteria(Criteria.where("clinicianID").is(clinicianID));
		patient = mongoTemplate.findOne(query, PatientDTO.class);
		return patient;
	}

	@Override
	public PatientDTO findById(String patientID) {
		PatientDTO patient = null;
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(patientID));
		patient = mongoTemplate.findOne(query, PatientDTO.class);
		return patient;
	}

	@Override
	public PatientDTO save(PatientDTO patient) {
		mongoTemplate.save(patient);
		return patient;
	}

	@Override
	public void deleteByID(String ID) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(ID));
		mongoTemplate.remove(query, PatientDTO.class);

	}

	@Override
	public PatientDTO updatePatient(PatientDTO patient) {
		mongoTemplate.save(patient);
		return patient;
	}

}
