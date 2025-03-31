package edu.icet.service;

import edu.icet.dto.Answer;

import java.util.List;

public interface AnswerService {
    Boolean createAnswer(Answer answer);

    Answer getAnswerById(Integer id);

    List<Answer> getAnswersByPaperId(Integer id);

    Answer getAnswerByQuestionId(Integer id);

    List<Answer> getAll();

    Boolean updateAnswer(Answer answer);

    Boolean deleteAnswerById(Integer id);
}
