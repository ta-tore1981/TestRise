package com.intesigroup.testcasefactory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.intesigroup.testcasefactory.domain.Approccio;

@Repository
public interface ApproccioRepository extends JpaRepository<Approccio,Long> {

}
