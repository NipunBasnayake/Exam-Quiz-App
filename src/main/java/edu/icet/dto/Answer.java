package edu.icet.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Answer {
    private Integer id;
    private Integer paperId;
    private Integer questionId;
    private String answer;
    private Boolean isCorrect;
}
