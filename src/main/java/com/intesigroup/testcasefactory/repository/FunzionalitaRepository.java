package com.intesigroup.testcasefactory.repository;

import java.util.List;

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
	
	@Modifying
	@Query("select f from Funzionalita f where f.interfaccia.progetto.id=?1")
	public List<Funzionalita> findByProgettoId(long id);
	
	public List<Funzionalita> findByInterfacciaId(long id);
	
}
