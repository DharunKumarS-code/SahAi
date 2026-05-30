package com.sahapathi.ai.entity;

import com.sahapathi.ai.enums.AttendanceStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "attendance")
@CompoundIndexes({
    @CompoundIndex(name = "idx_attendance_unique", def = "{'student': 1, 'classroom': 1, 'date': 1}", unique = true)
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attendance {

    @Id
    private String id;

    @DocumentReference(lazy = true)
    private User student;

    @DocumentReference(lazy = true)
    private Classroom classroom;

    @Indexed
    private LocalDate date;

    private AttendanceStatus status;

    private String remarks;

    @DocumentReference(lazy = true)
    private User markedBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
