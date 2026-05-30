package com.sahapathi.ai.repository;

import com.sahapathi.ai.entity.Attendance;
import com.sahapathi.ai.enums.AttendanceStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends MongoRepository<Attendance, String> {
    List<Attendance> findByClassroomIdAndDate(String classroomId, LocalDate date);
    List<Attendance> findByStudentIdAndClassroomId(String studentId, String classroomId);
    Optional<Attendance> findByStudentIdAndClassroomIdAndDate(String studentId, String classroomId, LocalDate date);

    long countByStudentIdAndClassroomIdAndStatus(String studentId, String classroomId, AttendanceStatus status);

    long countByStudentIdAndClassroomId(String studentId, String classroomId);

    List<Attendance> findByClassroomIdAndDateBetweenOrderByDateAsc(String classroomId, LocalDate startDate, LocalDate endDate);
}
