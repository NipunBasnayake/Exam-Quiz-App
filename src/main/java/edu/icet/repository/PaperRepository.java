package edu.icet.repository;

import edu.icet.entity.PaperEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaperRepository extends JpaRepository<PaperEntity, Integer> {
}
