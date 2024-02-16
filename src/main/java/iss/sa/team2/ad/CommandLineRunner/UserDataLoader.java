package iss.sa.team2.ad.CommandLineRunner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import iss.sa.team2.ad.enums.Gender;
import iss.sa.team2.ad.enums.UserPosition;
import iss.sa.team2.ad.enums.UserStatus;
import iss.sa.team2.ad.model.RegularUser;
import iss.sa.team2.ad.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class UserDataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository regularUserRepository;  // 替换为您的具体 Repository

    @Value("classpath:data/regular_user.csv")  // CSV 文件路径
    private Resource resource;

    @Override
    public void run(String... args) throws Exception {
        if (regularUserRepository.count() == 0) {  // 防止重复加载数据
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d");
            try (Reader reader = new InputStreamReader(resource.getInputStream())) {
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
                for (CSVRecord record : csvParser) {
                    RegularUser user = new RegularUser();
                    user.setId(record.get("Id"));
                    user.setAccount(record.get("Account"));
                    user.setPassword(record.get("Password"));
                    user.setPosition(UserPosition.valueOf(record.get("Position")));
                    user.setGender(Gender.valueOf(record.get("Gender")));
                    user.setMotto(record.get("Motto"));
                    user.setUserName(record.get("UserName"));
                    LocalDate birthday = LocalDate.parse(record.get("Birthday"), formatter);
                    user.setBirthday(birthday);
                    user.setProfileImage(record.get("ProfileImagePath"));
                    user.setStatus(UserStatus.valueOf(record.get("Status")));

                    // 保存实体
                    regularUserRepository.save(user);
                }
            }
        }
    }
}