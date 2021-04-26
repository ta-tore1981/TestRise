package com.intesigroup.testcasefactory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.intesigroup.testcasefactory.domain.Focus;

public interface FocusRepository extends JpaRepository<Focus,Long>{
	@Modifying
	@Query("DELETE FROM Focus i WHERE i.id=?1")
	public void deleteById(long id);

}
