package com.intesigroup.testcasefactory.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.intesigroup.testcasefactory.domain.TestCase;

@Repository
public interface TestCaseRepository extends JpaRepository<TestCase,Long>{
	@Modifying
	@Query("DELETE FROM TestCase i WHERE i.id=?1")
	public void deleteById(long id);

	public Long countByProgettoId(Long progettoId);
	
}
