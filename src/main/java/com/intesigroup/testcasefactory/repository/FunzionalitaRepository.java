package com.intesigroup.testcasefactory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.intesigroup.testcasefactory.domain.Funzionalita;

@Repository
public interface FunzionalitaRepository extends JpaRepository<Funzionalita,Long>{
	@Modifying
	@Query("DELETE FROM Funzionalita i WHERE i.id=?1")
	public void deleteById(long id);
}