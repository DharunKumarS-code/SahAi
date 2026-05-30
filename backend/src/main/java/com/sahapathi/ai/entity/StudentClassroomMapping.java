package com.sahapathi.ai.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import lombok.*;

import java.time.LocalDateTime;

@Document(collection = "student_classroom_mapping")
@CompoundIndexes({
    @CompoundIndex(name = "idx_student_classroom_unique", def = "{'student': 1, 'classroom': 1}", unique = true)
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentClassroomMapping {

    @Id
    private String id;

    @DocumentReference(lazy = true)
    private User student;

    @DocumentReference(lazy = true)
    private Classroom classroom;

    @CreatedDate
    private LocalDateTime joinedAt;

    @Builder.Default
    private Boolean isActive = true;
}
