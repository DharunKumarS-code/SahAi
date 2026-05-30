package com.sahapathi.ai.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import lombok.*;

import java.time.LocalDateTime;

@Document(collection = "quizzes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quiz {

    @Id
    private String id;

    private String title;

    private String description;

    @DocumentReference(lazy = true)
    @Indexed
    private Classroom classroom;

    @DocumentReference(lazy = true)
    private User createdBy;

    private Integer durationMinutes;

    @Builder.Default
    private Integer totalMarks = 0;

    @Builder.Default
    private Boolean isPublished = false;

    private LocalDateTime dueDate;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
