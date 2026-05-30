package com.sahapathi.ai.controller;

import com.sahapathi.ai.dto.*;
import com.sahapathi.ai.service.AIRecommendationService;
import com.sahapathi.ai.service.ai.AIProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
@Tag(name = "AI Services", description = "AI-powered translation, simplification, and recommendations")
public class AIController {

    private final AIProvider aiProvider;
    private final AIRecommendationService recommendationService;

    @PostMapping("/translate")
    @Operation(summary = "Translate text to target language")
    public ResponseEntity<ApiResponse<TranslateResponse>> translate(
            @Valid @RequestBody TranslateRequest request) {
        TranslateResponse response = aiProvider.translate(request.getText(), request.getLanguage());
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @PostMapping("/simplify")
    @Operation(summary = "Simplify text for easier understanding")
    public ResponseEntity<ApiResponse<SimplifyResponse>> simplify(
            @Valid @RequestBody SimplifyRequest request) {
        SimplifyResponse response = aiProvider.simplify(request.getText(), request.getLanguage());
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @PostMapping("/recommendations/generate/{classroomId}")
    @Operation(summary = "Generate AI recommendations for classroom")
    public ResponseEntity<ApiResponse<List<AIRecommendationResponse>>> generateRecommendations(
            @PathVariable String classroomId) {
        return ResponseEntity.ok(ApiResponse.success(recommendationService.generateRecommendations(classroomId)));
    }

    @GetMapping("/recommendations/{classroomId}")
    @Operation(summary = "Get active recommendations for classroom")
    public ResponseEntity<ApiResponse<List<AIRecommendationResponse>>> getRecommendations(
            @PathVariable String classroomId) {
        return ResponseEntity.ok(ApiResponse.success(recommendationService.getRecommendations(classroomId)));
    }

    @PutMapping("/recommendations/{id}/resolve")
    @Operation(summary = "Mark recommendation as resolved")
    public ResponseEntity<ApiResponse<Void>> resolveRecommendation(@PathVariable String id) {
        recommendationService.resolveRecommendation(id);
        return ResponseEntity.ok(ApiResponse.success("Recommendation resolved", null));
    }
}
