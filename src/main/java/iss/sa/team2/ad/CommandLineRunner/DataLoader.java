package iss.sa.team2.ad.CommandLineRunner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import iss.sa.team2.ad.model.Anime;
import iss.sa.team2.ad.repository.AnimeRepository;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private AnimeRepository animeRepository; // 替换为您的 Repository

    @Autowired
    private Environment env; // 用于访问应用程序的环境和属性

    @Value("classpath:data/anime.csv") // 指向您的 CSV 文件
    private Resource animeCsv;

    @Override
    public void run(String... args) throws Exception {
        // 确保数据库是空的，防止每次应用启动都重新加载数据
        if (animeRepository.count() == 0) {
            try (Reader reader = new InputStreamReader(animeCsv.getInputStream(), StandardCharsets.UTF_8)) {
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

                for (CSVRecord record : csvParser) {
                    Anime anime = new Anime();
                    anime.setName(record.get("name"));
                    anime.setCover(record.get("cover"));
                    anime.setDescription(record.get("description"));
                    anime.setVideoPlaybackSource(record.get("videoPlaybackSource"));
                    anime.setClicks(Integer.parseInt(record.get("clicks")));
                    anime.setAverageRating(Float.parseFloat(record.get("averageRating")));
                    anime.setReleaseDate(LocalDateTime.parse(record.get("releaseDate")));
                    anime.setEpisodes(Integer.parseInt(record.get("episodes")));
                    anime.setDirector(record.get("director"));
                    anime.setScript(record.get("script"));
                    anime.setCharacterDesign(record.get("characterDesign"));
                    anime.setSakugaDirector(record.get("sakugaDirector"));
                    anime.setProductionCompany(record.get("productionCompany"));

                    animeRepository.save(anime);
                }
            }
        }
    }

    private byte[] downloadImage(String imageUrl) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(imageUrl, byte[].class);
    }
}
