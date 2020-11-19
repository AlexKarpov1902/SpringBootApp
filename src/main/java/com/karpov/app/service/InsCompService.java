package com.karpov.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karpov.app.exception.RecordNotFoundException;
import com.karpov.app.model.InsCompEntity;
import com.karpov.app.repository.InsCompRepository;

@Service
public class InsCompService {
	
	@Autowired
	InsCompRepository repository;
	
	public List<InsCompEntity> getAllCompany(){                           //получить список всех компаний
		List<InsCompEntity> result = (List<InsCompEntity>) repository.findAll();
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<InsCompEntity>();
		}
	}
	
	public InsCompEntity getCompanyById(Long id) throws RecordNotFoundException{  //Найти компанию по ID
		Optional<InsCompEntity> company = repository.findById(id);
		if(company.isPresent()) {
			return company.get();
		} else {
			throw new RecordNotFoundException("Нет записей с данным ID");
		}
	}
	
	public InsCompEntity createOrUpdateCompany(InsCompEntity entity){    //Внести новую или изменить существующую компанию 
		if(entity.getId()  == null) {                                  //Если не найден ID - добавить
			entity = repository.save(entity);
			return entity;
		} 
		else {
			Optional<InsCompEntity> company = repository.findById(entity.getId());
			if(company.isPresent()) {                           /////////////////////////////
				InsCompEntity newCompany = company.get();
				newCompany.setInn     (entity.getInn());
				newCompany.setOgrn    (entity.getOgrn());
				newCompany.setFullname(entity.getFullname());
                                newCompany.setAddress (entity.getAddress());
				newCompany = repository.save(newCompany);
				return newCompany;
			} else {
				entity = repository.save(entity);
				return entity;
			 }
		}
	} 
	
	public void deleteCompanyById(Long id) throws RecordNotFoundException{ 
		Optional<InsCompEntity> company = repository.findById(id);
		if(company.isPresent()){ 
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("Record not found with ID");
		}
 	} 
        
        public InsCompEntity getCompanyByOgrn(String ogrn){      //Найти компанию по ogrn или вернуть null
        	Optional<InsCompEntity> company = repository.findByOgrn(ogrn); 
                return company.orElse(null);
        }
        
        public InsCompEntity getCompanyByInn(String inn){           //Найти компанию по inn или вернуть null
                Optional<InsCompEntity> company = repository.findByInn(inn); 
                return company.orElse(null);
        }
         
        public List<InsCompEntity> getAllCompanyByName(String stroka){      //Найти компании содержащие подстроку в наименовании 
                  List result=   repository.findAllByFullnameContaining(stroka);
                  if(result.size() > 0) {
                                return result;
                  } else {
                                return new ArrayList<InsCompEntity>();
                  }
        }     
}