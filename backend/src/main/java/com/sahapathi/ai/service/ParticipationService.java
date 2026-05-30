package com.sahapathi.ai.service;

import com.sahapathi.ai.dto.ParticipationStatsResponse;
import com.sahapathi.ai.entity.Classroom;
import com.sahapathi.ai.entity.Participation;
import com.sahapathi.ai.entity.User;
import com.sahapathi.ai.enums.ParticipationEventType;
import com.sahapathi.ai.exception.ResourceNotFoundException;
import com.sahapathi.ai.repository.ClassroomRepository;
import com.sahapathi.ai.repository.ParticipationRepository;
import com.sahapathi.ai.repository.StudentClassroomMappingRepository;
import com.sahapathi.ai.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParticipationService {

    private final ParticipationRepository participationRepository;
    private final UserRepository userRepository;
    private final ClassroomRepository classroomRepository;
    private final StudentClassroomMappingRepository mappingRepository;

    @Transactional
    public void trackEvent(String studentId, String classroomId, String eventType, String description) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", studentId));
        Classroom classroom = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new ResourceNotFoundException("Classroom", "id", classroomId));

        Participation participation = Participation.builder()
                .student(student)
                .classroom(classroom)
                .eventType(ParticipationEventType.valueOf(eventType))
                .description(description)
                .eventDate(LocalDate.now())
                .score(1)
                .build();

        participationRepository.save(participation);
    }

    public ParticipationStatsResponse getStudentStats(String studentId, String classroomId) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", studentId));

        Long totalScore = participationRepository.getTotalScoreByStudentAndClassroom(studentId, classroomId);
        List<org.bson.Document> eventCounts = participationRepository.countByEventType(studentId, classroomId);

        Map<String, String> eventTypeCounts = new LinkedHashMap<>();
        for (org.bson.Document row : eventCounts) {
            eventTypeCounts.put(row.get("_id").toString(), row.get("count").toString());
        }

        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(30);
        List<org.bson.Document> dailyScoreRows = participationRepository.getDailyScores(classroomId, startDate, endDate);

        Map<String, String> dailyScores = new LinkedHashMap<>();
        for (org.bson.Document row : dailyScoreRows) {
            if(row.get("_id") != null) {
                dailyScores.put(row.get("_id").toString(), row.get("total").toString());
            }
        }

        return ParticipationStatsResponse.builder()
                .studentId(studentId)
                .studentName(student.getFullName())
                .classroomId(classroomId)
                .totalScore(totalScore != null ? String.valueOf(totalScore) : "0")
                .eventTypeCounts(eventTypeCounts)
                .dailyScores(dailyScores)
                .build();
    }

    public List<ParticipationStatsResponse> getClassroomStats(String classroomId) {
        return mappingRepository.findByClassroomIdAndIsActiveTrue(classroomId)
                .stream()
                .map(mapping -> getStudentStats(mapping.getStudent().getId(), classroomId))
                .collect(Collectors.toList());
    }
}
