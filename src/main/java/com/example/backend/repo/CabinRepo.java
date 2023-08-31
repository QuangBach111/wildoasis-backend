package com.example.backend.repo;

import com.example.backend.model.entity.Cabin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.stream.Stream;

public interface CabinRepo extends JpaRepository<Cabin, Long> {
	@Query("SELECT c FROM Cabin c")
	Stream<Cabin> getAll();
//	@Modifying
//	@Query(value = "DELETE FROM Cabin c where c.id = ?1")
//	Lo deleteById(Long id);
}