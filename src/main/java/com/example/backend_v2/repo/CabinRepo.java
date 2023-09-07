package com.example.backend_v2.repo;

import com.example.backend_v2.model.entity.Cabin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CabinRepo extends JpaRepository<Cabin, Long> {
	@Query("SELECT c FROM Cabin c WHERE c.discount != 0")
	Page<Cabin> findAllWithDiscount(Pageable pageable);

	@Query("SELECT c FROM Cabin c	WHERE c.discount = 0")
	Page<Cabin> findAllWithNoDiscount(Pageable pageable);

}