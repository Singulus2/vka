package com.devk.vka.repository;

import com.devk.vka.domain.Vka;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Vka entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VkaRepository extends JpaRepository<Vka, Long>, JpaSpecificationExecutor<Vka> {

}
