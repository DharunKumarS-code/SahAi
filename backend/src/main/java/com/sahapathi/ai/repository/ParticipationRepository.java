package com.sahapathi.ai.repository;

import com.sahapathi.ai.entity.Participation;
import com.sahapathi.ai.enums.ParticipationEventType;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.bson.Document;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ParticipationRepository extends MongoRepository<Participation, String> {
    List<Participation> findByStudentIdAndClassroomId(String studentId, String classroomId);

    @Aggregation(pipeline = {
        "{ '$match': { 'student': ObjectId(?0), 'classroom': ObjectId(?1) } }",
        "{ '$group': { '_id': null, 'total': { '$sum': '$score' } } }"
    })
    Long getTotalScoreByStudentAndClassroom(String studentId, String classroomId);

    List<Participation> findByClassroomIdAndEventDateBetweenOrderByEventDateAsc(String classroomId, LocalDate startDate, LocalDate endDate);

    @Aggregation(pipeline = {
        "{ '$match': { 'student': ObjectId(?0), 'classroom': ObjectId(?1) } }",
        "{ '$group': { '_id': '$eventType', 'count': { '$sum': 1 } } }"
    })
    List<Document> countByEventType(String studentId, String classroomId);

    @Aggregation(pipeline = {
        "{ '$match': { 'classroom': ObjectId(?0), 'eventDate': { '$gte': ?1, '$lte': ?2 } } }",
        "{ '$group': { '_id': '$eventDate', 'total': { '$sum': '$score' } } }",
        "{ '$sort': { '_id': 1 } }"
    })
    List<Document> getDailyScores(String classroomId, LocalDate startDate, LocalDate endDate);

    Long countByStudentIdAndClassroomIdAndEventType(String studentId, String classroomId, ParticipationEventType eventType);
}
