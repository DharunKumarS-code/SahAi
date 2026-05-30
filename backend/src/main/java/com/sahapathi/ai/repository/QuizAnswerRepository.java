package com.sahapathi.ai.repository;

import com.sahapathi.ai.entity.QuizAnswer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizAnswerRepository extends MongoRepository<QuizAnswer, String> {
    List<QuizAnswer> findByAttemptId(String attemptId);
}
