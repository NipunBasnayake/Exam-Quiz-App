package edu.icet.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Paper {
    private Integer id;
    private Integer userId;
    private Integer examId;
}
