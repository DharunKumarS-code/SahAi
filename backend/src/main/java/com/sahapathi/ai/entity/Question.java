package com.sahapathi.ai.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import lombok.*;

@Document(collection = "questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {

    @Id
    private String id;

    @DocumentReference(lazy = true)
    @Indexed
    private Quiz quiz;

    private String questionText;

    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    private String correctOption; // 'A', 'B', 'C', or 'D'

    @Builder.Default
    private Integer points = 1;

    private String explanation;

    private Integer sortOrder;
}
