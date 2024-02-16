package iss.sa.team2.ad.CommandLineRunner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import iss.sa.team2.ad.model.Background;
import iss.sa.team2.ad.repository.BackgroundRepository;

import java.io.InputStreamReader;
import java.io.Reader;

@Component
public class BackgroundDataLoader implements CommandLineRunner {

    @Autowired
    private BackgroundRepository backgroundRepository;

    @Value("classpath:data/background.csv") // 指向 CSV 文件
    private Resource backgroundCsv;

    @Override
    public void run(String... args) throws Exception {
        if (backgroundRepository.count() == 0) { // 防止重复加载数据
            try (Reader reader = new InputStreamReader(backgroundCsv.getInputStream())) {
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
                for (CSVRecord record : csvParser) {
                    String name = record.get("Name");
                    String photoPath = record.get("PhotoPath");
                    Background background = new Background(name, photoPath);
                    backgroundRepository.save(background);
                }
            }
        }
    }
}
