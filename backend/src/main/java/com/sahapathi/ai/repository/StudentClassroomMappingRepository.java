package com.sahapathi.ai.repository;

import com.sahapathi.ai.entity.StudentClassroomMapping;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentClassroomMappingRepository extends MongoRepository<StudentClassroomMapping, String> {
    List<StudentClassroomMapping> findByStudentIdAndIsActiveTrue(String studentId);
    List<StudentClassroomMapping> findByClassroomIdAndIsActiveTrue(String classroomId);
    Optional<StudentClassroomMapping> findByStudentIdAndClassroomId(String studentId, String classroomId);
    boolean existsByStudentIdAndClassroomId(String studentId, String classroomId);
    String countByClassroomIdAndIsActiveTrue(String classroomId);
}
