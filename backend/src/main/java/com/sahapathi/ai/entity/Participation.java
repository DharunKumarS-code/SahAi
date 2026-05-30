package com.sahapathi.ai.entity;

import com.sahapathi.ai.enums.ParticipationEventType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "participation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Participation {

    @Id
    private String id;

    @DocumentReference(lazy = true)
    @Indexed
    private User student;

    @DocumentReference(lazy = true)
    @Indexed
    private Classroom classroom;

    private ParticipationEventType eventType;

    private String description;

    @Builder.Default
    private Integer score = 1;

    private LocalDate eventDate;

    @CreatedDate
    private LocalDateTime createdAt;
}
