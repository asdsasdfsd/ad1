package iss.sa.team2.ad.CommandLineRunner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import iss.sa.team2.ad.model.Usertag;
import iss.sa.team2.ad.repository.UsertagRepository;

import org.springframework.boot.CommandLineRunner;
import java.io.InputStreamReader;
import java.io.Reader;

@Component
public class UserTagDataLoader implements CommandLineRunner {

    @Autowired
    private UsertagRepository userTagRepository;  // 替换为您的具体 Repository

    @Value("classpath:data/user_tag.csv")  // CSV 文件路径
    private Resource resource;

    @Override
    public void run(String... args) throws Exception {
        if (userTagRepository.count() == 0) {  // 防止重复加载数据
            try (Reader reader = new InputStreamReader(resource.getInputStream())) {
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
                for (CSVRecord record : csvParser) {
                    Usertag tag = new Usertag();
                    tag.setId(Long.parseLong(record.get("Id")));
                    tag.setTagName(record.get("tagName"));

                    userTagRepository.save(tag);
                }
            }
        }
    }
}