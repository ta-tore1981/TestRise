package com.intesigroup.testcasefactory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.intesigroup.testcasefactory.domain.TipoTest;

@Repository
public interface TipoTestRepository extends JpaRepository<TipoTest,Long>{

}
