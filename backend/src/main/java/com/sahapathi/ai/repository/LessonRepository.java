package com.sahapathi.ai.repository;

import com.sahapathi.ai.entity.Lesson;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends MongoRepository<Lesson, String> {
    List<Lesson> findByClassroomIdAndIsPublishedTrue(String classroomId);
    List<Lesson> findByClassroomId(String classroomId);
    String countByClassroomId(String classroomId);
}
