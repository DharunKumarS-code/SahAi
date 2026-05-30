package com.sahapathi.ai.service.ai;

import com.sahapathi.ai.dto.SimplifyResponse;
import com.sahapathi.ai.dto.TranslateResponse;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;

/**
 * Mock AI Service Implementation.
 * Returns realistic mock responses for translation, simplification, and recommendations.
 * Replace with real AI provider (OpenAI, Gemini, Azure, HuggingFace) when ready.
 */
@Service
public class MockAIServiceImpl implements AIProvider {

    private static final Map<String, String> MOCK_TRANSLATIONS = Map.of(
        "Hindi", "यह एक अनुवादित पाठ है। मूल सामग्री को हिंदी में रूपांतरित किया गया है।",
        "Tamil", "இது மொழிபெயர்க்கப்பட்ட உரை. அசல் உள்ளடக்கம் தமிழில் மாற்றப்பட்டுள்ளது.",
        "Telugu", "ఇది అనువదించబడిన వచనం. అసలు కంటెంట్ తెలుగులోకి మార్చబడింది.",
        "Kannada", "ಇದು ಅನುವಾದಿತ ಪಠ್ಯ. ಮೂಲ ವಿಷಯವನ್ನು ಕನ್ನಡಕ್ಕೆ ಪರಿವರ್ತಿಸಲಾಗಿದೆ.",
        "Malayalam", "ഇത് വിവർത്തനം ചെയ്ത വാചകമാണ്. യഥാർത്ഥ ഉള്ളടക്കം മലയാളത്തിലേക്ക് പരിവർത്തനം ചെയ്തു."
    );

    private static final String[] RECOMMENDATIONS = {
        "Student shows declining participation over the past week. Consider one-on-one check-in.",
        "Student has missed 3 consecutive classes. Recommend reaching out to parents/guardians.",
        "Quiz scores have dropped by 20%. Student may need additional support in this topic.",
        "Student actively participates but struggles with written assessments. Consider alternative evaluation methods.",
        "Student excels in group activities. Consider assigning peer mentorship role.",
        "Language barrier detected. Student frequently uses translation feature. Consider providing bilingual materials.",
        "Student shows improvement in recent quizzes. Positive reinforcement recommended.",
        "Low engagement with lesson content. Interactive or visual materials may help."
    };

    @Override
    public TranslateResponse translate(String text, String targetLanguage) {
        String translated = MOCK_TRANSLATIONS.getOrDefault(targetLanguage,
                "[Mock Translation to " + targetLanguage + "] " + text);

        // For English, return as-is
        if ("English".equalsIgnoreCase(targetLanguage)) {
            translated = text;
        }

        return TranslateResponse.builder()
                .originalText(text)
                .translatedText(translated)
                .sourceLanguage("English")
                .targetLanguage(targetLanguage)
                .build();
    }

    @Override
    public SimplifyResponse simplify(String text, String language) {
        // Mock simplification - shorten and simplify the text
        String simplified = "Simply put: " + text.replaceAll("\\b(the|is|are|was|were|a|an)\\b", "")
                .replaceAll("\\s+", " ").trim();

        if (text.toLowerCase().contains("mitochondria")) {
            simplified = "Mitochondria helps produce energy for the cell. Think of it like a battery that powers everything the cell does.";
        } else if (text.toLowerCase().contains("photosynthesis")) {
            simplified = "Photosynthesis is how plants make their own food using sunlight, water, and air. The leaves capture sunlight and turn it into energy.";
        } else if (text.length() > 100) {
            simplified = text.substring(0, 80) + "... (simplified version: " + text.substring(0, 50) + " in easier words)";
        }

        return SimplifyResponse.builder()
                .originalText(text)
                .simplifiedText(simplified)
                .language(language != null ? language : "English")
                .build();
    }

    @Override
    public String generateRecommendation(String studentName, String context) {
        Random random = new Random();
        String recommendation = RECOMMENDATIONS[random.nextInt(RECOMMENDATIONS.length)];
        return recommendation.replace("Student", studentName);
    }
}
