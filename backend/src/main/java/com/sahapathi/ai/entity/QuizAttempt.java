package com.sahapathi.ai.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import lombok.*;

import java.time.LocalDateTime;

@Document(collection = "quiz_attempts")
@CompoundIndexes({
    @CompoundIndex(name = "idx_attempt_unique", def = "{'student': 1, 'quiz': 1}", unique = true)
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizAttempt {

    @Id
    private String id;

    @DocumentReference(lazy = true)
    private User student;

    @DocumentReference(lazy = true)
    private Quiz quiz;

    private Integer score;

    private Integer totalMarks;

    private Double percentage;

    @Builder.Default
    private Boolean isCompleted = false;

    @CreatedDate
    private LocalDateTime startedAt;

    private LocalDateTime submittedAt;
}
