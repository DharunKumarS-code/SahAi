package com.sahapathi.ai.service.ai;

import com.sahapathi.ai.dto.SimplifyResponse;
import com.sahapathi.ai.dto.TranslateResponse;

/**
 * AI Service interface - abstraction layer for AI providers.
 * Implementations: MockAIServiceImpl, OpenAIServiceImpl, GeminiServiceImpl, etc.
 */
public interface AIProvider {

    TranslateResponse translate(String text, String targetLanguage);

    SimplifyResponse simplify(String text, String language);

    String generateRecommendation(String studentName, String context);
}
