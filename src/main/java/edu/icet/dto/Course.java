package edu.icet.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Course {
    private Integer id;
    private String name;
    private String description;
    private Integer teacherId;
    private String subject;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
