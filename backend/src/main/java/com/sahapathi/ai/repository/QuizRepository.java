package com.sahapathi.ai.repository;

import com.sahapathi.ai.entity.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends MongoRepository<Quiz, String> {
    List<Quiz> findByClassroomId(String classroomId);
    List<Quiz> findByClassroomIdAndIsPublishedTrue(String classroomId);
    String countByClassroomId(String classroomId);
}
