package edu.icet.service.impl;

import edu.icet.dto.Answer;
import edu.icet.entity.AnswerEntity;
import edu.icet.repository.AnswerRepository;
import edu.icet.service.AnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    final AnswerRepository answerRepository;
    final ModelMapper modelMapper;

    @Override
    public Boolean createAnswer(Answer answer) {
        if (answer == null) {
            log.error("Cannot create null answer");
            throw new IllegalArgumentException("Answer cannot be null");
        }

        try {
            AnswerEntity savedAnswer = answerRepository.save(modelMapper.map(answer, AnswerEntity.class));
            return answerRepository.existsById(savedAnswer.getId());
        } catch (Exception e) {
            log.error("Error creating answer: {}", e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Answer getAnswerById(Integer id) {
        if (id == null || id <= 0) {
            log.error("Invalid answer ID: {}", id);
            throw new IllegalArgumentException("Answer ID must be a positive integer");
        }

        try {
            Optional<AnswerEntity> optionalAnswer = answerRepository.findById(id);
            return optionalAnswer.map(entity -> modelMapper.map(entity, Answer.class)).orElse(null);
        } catch (Exception e) {
            log.error("Error retrieving answer with ID {}: {}", id, e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<Answer> getAnswersByPaperId(Integer id) {
        if (id == null || id <= 0) {
            log.error("Invalid paper ID: {}", id);
            throw new IllegalArgumentException("Paper ID must be a positive integer");
        }

        try {
            List<AnswerEntity> answersByPaperId = answerRepository.findAnswersByPaperId(id);
            List<Answer> answers = new ArrayList<>();
            answersByPaperId.forEach(answerEntity -> answers.add(modelMapper.map(answerEntity, Answer.class)));
            return answers;
        } catch (Exception e) {
            log.error("Error retrieving answers for paper ID {}: {}", id, e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    @Override
    public Answer getAnswerByQuestionId(Integer id) {
        if (id == null || id <= 0) {
            log.error("Invalid question ID: {}", id);
            throw new IllegalArgumentException("Question ID must be a positive integer");
        }

        try {
            AnswerEntity answerEntity = answerRepository.findAnswersByQuestionId(id);
            return answerEntity != null ? modelMapper.map(answerEntity, Answer.class) : null;
        } catch (Exception e) {
            log.error("Error retrieving answer for question ID {}: {}", id, e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<Answer> getAll() {
        try {
            List<AnswerEntity> all = answerRepository.findAll();
            List<Answer> answers = new ArrayList<>();
            all.forEach(answerEntity -> answers.add(modelMapper.map(answerEntity, Answer.class)));
            return answers;
        } catch (Exception e) {
            log.error("Error retrieving all answers: {}", e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    @Override
    public Boolean updateAnswer(Answer answer) {
        if (answer == null) {
            log.error("Cannot update null answer");
            throw new IllegalArgumentException("Answer cannot be null");
        }

        if (answer.getId() == null || answer.getId() <= 0) {
            log.error("Invalid answer ID for update: {}", answer.getId());
            throw new IllegalArgumentException("Answer ID must be a positive integer");
        }

        try {
            if (!answerRepository.existsById(answer.getId())) {
                log.warn("Cannot update answer with ID {} - not found", answer.getId());
                return false;
            }

            AnswerEntity savedEntity = answerRepository.save(modelMapper.map(answer, AnswerEntity.class));
            return Objects.equals(savedEntity.getId(), answer.getId());
        } catch (Exception e) {
            log.error("Error updating answer with ID {}: {}", answer.getId(), e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteAnswerById(Integer id) {
        if (id == null || id <= 0) {
            log.error("Invalid answer ID for deletion: {}", id);
            throw new IllegalArgumentException("Answer ID must be a positive integer");
        }

        try {
            if (!answerRepository.existsById(id)) {
                log.warn("Cannot delete answer with ID {} - not found", id);
                return false;
            }

            answerRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("Error deleting answer with ID {}: {}", id, e.getMessage(), e);
            return false;
        }
    }
}