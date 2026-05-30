package com.sahapathi.ai.repository;

import com.sahapathi.ai.entity.QuizAttempt;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizAttemptRepository extends MongoRepository<QuizAttempt, String> {
    Optional<QuizAttempt> findByStudentIdAndQuizId(String studentId, String quizId);
    List<QuizAttempt> findByStudentId(String studentId);
    List<QuizAttempt> findByQuizId(String quizId);
    boolean existsByStudentIdAndQuizId(String studentId, String quizId);

    @Aggregation(pipeline = {
        "{ '$match': { 'quiz': ObjectId(?0), 'isCompleted': true } }",
        "{ '$group': { '_id': null, 'avgScore': { '$avg': '$percentage' } } }"
    })
    Double getAverageScoreByQuiz(String quizId);

    @Aggregation(pipeline = {
        "{ '$match': { 'student': ObjectId(?0), 'isCompleted': true } }",
        "{ '$group': { '_id': null, 'avgScore': { '$avg': '$percentage' } } }"
    })
    Double getAverageScoreByStudent(String studentId);
}
