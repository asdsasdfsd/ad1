package iss.sa.team2.ad.interfacemethods;

import iss.sa.team2.ad.model.Attendance;

import java.util.List;
import java.util.Optional;

public interface IAttendanceService {
    Optional<Attendance> findById(Long id);
    Attendance save(Attendance attendance);
    void deleteById(Long id);
    List<Attendance> findAll();
}
