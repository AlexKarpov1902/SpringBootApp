package com.karpov.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.karpov.app.model.InsCompEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface InsCompRepository extends CrudRepository<InsCompEntity, Long> {

   // @Query findByFirstnameContaining(String name)
        //(SELECT * FROM TABLE_COMPANY WHERE LOCATE('fullname', fullname))
    List<InsCompEntity> findAllByFullnameContaining(String fullname);
    
    Optional<InsCompEntity> findByOgrn(String ogrn);
    
    Optional<InsCompEntity> findByInn(String inn);
    
}
