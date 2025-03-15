package edu.icet.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Question {
    private Integer id;
    private Integer examId;
    private String text;
    private String options;
    private String correctAnswer;
    private Integer points;

}
