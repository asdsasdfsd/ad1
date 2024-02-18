package iss.sa.team2.ad.dto;

import java.util.List;

public class PredictionResponse {
    private List<String> recommendedAnimes;

    // 构造函数
    public PredictionResponse() {
    }

    public PredictionResponse(List<String> recommendedAnimes) {
        this.recommendedAnimes = recommendedAnimes;
    }

    // Getter 和 Setter
    public List<String> getRecommendedAnimes() {
        return recommendedAnimes;
    }

    public void setRecommendedAnimes(List<String> recommendedAnimes) {
        this.recommendedAnimes = recommendedAnimes;
    }
}
