package iss.sa.team2.ad.CommandLineRunner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import iss.sa.team2.ad.model.Animetag;
import iss.sa.team2.ad.repository.AnimetagRepository;

import org.springframework.boot.CommandLineRunner;
import java.io.FileReader;
import java.io.Reader;

@Component
public class AnimetagDataLoader implements CommandLineRunner {

    @Autowired
    private AnimetagRepository animetagRepository;

    @Value("classpath:data/anime_tag.csv") // 指定 CSV 文件的路径
    private Resource animetagCsv;

    @Override
    public void run(String... args) throws Exception {
        if (animetagRepository.count() == 0) { // 只有在数据库为空时才加载数据
            try (Reader reader = new FileReader(animetagCsv.getFile())) {
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
                for (CSVRecord record : csvParser) {
                    Animetag animetag = new Animetag();
                    animetag.setTagName(record.get("tag_name"));
                    animetagRepository.save(animetag);
                }
            }
        }
    }
}
