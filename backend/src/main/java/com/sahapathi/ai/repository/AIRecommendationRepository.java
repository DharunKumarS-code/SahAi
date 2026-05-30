package com.sahapathi.ai.repository;

import com.sahapathi.ai.entity.AIRecommendation;
import com.sahapathi.ai.enums.RecommendationType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AIRecommendationRepository extends MongoRepository<AIRecommendation, String> {
    List<AIRecommendation> findByClassroomIdAndIsResolvedFalseOrderByCreatedAtDesc(String classroomId);
    List<AIRecommendation> findByStudentIdOrderByCreatedAtDesc(String studentId);
    List<AIRecommendation> findByClassroomIdAndTypeOrderByCreatedAtDesc(String classroomId, RecommendationType type);
    String countByClassroomIdAndIsResolvedFalse(String classroomId);
}
