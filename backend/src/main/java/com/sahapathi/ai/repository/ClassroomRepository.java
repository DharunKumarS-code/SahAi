package com.sahapathi.ai.repository;

import com.sahapathi.ai.entity.Classroom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassroomRepository extends MongoRepository<Classroom, String> {
    List<Classroom> findByTeacherIdAndIsActiveTrue(String teacherId);
    Optional<Classroom> findByInviteCode(String inviteCode);
    String countByIsActiveTrue();
}
