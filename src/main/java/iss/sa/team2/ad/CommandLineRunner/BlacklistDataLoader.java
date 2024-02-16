package iss.sa.team2.ad.CommandLineRunner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import iss.sa.team2.ad.model.Blacklist;
import iss.sa.team2.ad.repository.BlacklistRepository;

import java.io.InputStreamReader;
import java.io.Reader;

@Component
public class BlacklistDataLoader implements CommandLineRunner {

    @Autowired
    private BlacklistRepository blacklistRepository;

    @Value("classpath:data/blacklist.csv") // 指向 CSV 文件
    private Resource blacklistCsv;

    @Override
    public void run(String... args) throws Exception {
        if (blacklistRepository.count() == 0) { // 防止重复加载数据
            try (Reader reader = new InputStreamReader(blacklistCsv.getInputStream())) {
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
                for (CSVRecord record : csvParser) {
                    String words = record.get("words");
                    Blacklist blacklist = new Blacklist(words);
                    blacklistRepository.save(blacklist);
                }
            }
        }
    }
}