package com.sahapathi.ai.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassroomResponse {
    private String id;
    private String name;
    private String subject;
    private String grade;
    private String description;
    private String inviteCode;
    private String teacherId;
    private String teacherName;
    private String studentCount;
    private String lessonCount;
    private String quizCount;
    private Boolean isActive;
    private LocalDateTime createdAt;
}
