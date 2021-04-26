package com.intesigroup.testcasefactory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.intesigroup.testcasefactory.domain.Progetto;

@Repository
public interface  ProgettoRepository  extends JpaRepository<Progetto,Long>{
}
