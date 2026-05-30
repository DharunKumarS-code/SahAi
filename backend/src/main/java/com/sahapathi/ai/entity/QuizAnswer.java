package com.sahapathi.ai.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import lombok.*;

@Document(collection = "quiz_answers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizAnswer {

    @Id
    private String id;

    @DocumentReference(lazy = true)
    private QuizAttempt attempt;

    @DocumentReference(lazy = true)
    private Question question;

    private String selectedOption;

    private Boolean isCorrect;
}
