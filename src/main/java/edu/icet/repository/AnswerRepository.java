package edu.icet.repository;

import edu.icet.entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Integer> {
    List<AnswerEntity> findAnswersByPaperId(Integer paperId);

    AnswerEntity findAnswersByQuestionId(Integer questionId);
}
