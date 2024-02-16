package iss.sa.team2.ad.controller;

import iss.sa.team2.ad.interfacemethods.IAttendanceService;
import iss.sa.team2.ad.model.Attendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/attendances")
public class AttendanceController {

	@Autowired
    private IAttendanceService attendanceService;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Attendance> findById(@PathVariable Long id) {
        Optional<Attendance> attendanceOptional = attendanceService.findById(id);
        return attendanceOptional.map(attendance -> new ResponseEntity<>(attendance, HttpStatus.OK))
                                  .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Attendance> save(@RequestBody Attendance attendance) {
        Attendance savedAttendance = attendanceService.save(attendance);
        return new ResponseEntity<>(savedAttendance, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        attendanceService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Attendance>> findAll() {
        List<Attendance> attendanceList = attendanceService.findAll();
        return new ResponseEntity<>(attendanceList, HttpStatus.OK);
    }
}
