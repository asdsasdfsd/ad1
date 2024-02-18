package iss.sa.team2.ad.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import iss.sa.team2.ad.dto.PredictionRequest;

@Controller
public class PredictionController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/recommendationForm")
    public String showRecommendationForm(Model model) {
        model.addAttribute("predictionRequest", new PredictionRequest());
        return "recommendation_form";
    }
    
    @PostMapping("/getRecommendation")
    public String getRecommendation(@ModelAttribute PredictionRequest request, Model model) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PredictionRequest> entity = new HttpEntity<>(request, headers);

        // 调用 Flask API 获取推荐动画
        List<String> recommendedAnimes = restTemplate.postForObject("http://192.168.68.115:5000/recommend", entity, List.class);

        model.addAttribute("recommendedAnimes", recommendedAnimes);
        return "recommendation_result"; 
    }
    
}
