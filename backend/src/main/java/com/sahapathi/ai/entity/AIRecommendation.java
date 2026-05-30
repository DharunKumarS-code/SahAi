package com.sahapathi.ai.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import lombok.*;

import java.time.LocalDateTime;

@Document(collection = "ai_recommendations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AIRecommendation {

    @Id
    private String id;

    @DocumentReference(lazy = true)
    @Indexed
    private User student;

    @DocumentReference(lazy = true)
    @Indexed
    private Classroom classroom;

    private String type; // e.g., 'LOW_PARTICIPATION', 'IMPROVEMENT', 'ATTENDANCE_CONCERN'

    private String message;

    private String priority; // 'LOW', 'MEDIUM', 'HIGH', 'CRITICAL'

    @Builder.Default
    private Boolean isResolved = false;

    @CreatedDate
    private LocalDateTime createdAt;
}
