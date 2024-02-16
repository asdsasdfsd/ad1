package iss.sa.team2.ad.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private LocalDate signInTime;

    public Attendance() {
    }

    public Attendance(User user, LocalDate signInTime) {
        this.user = user;
        this.signInTime = signInTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getSignInTime() {
        return signInTime;
    }

    public void setSignInTime(LocalDate signInTime) {
        this.signInTime = signInTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), user, signInTime);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Attendance that = (Attendance) obj;
        return Objects.equals(getId(), that.getId()) &&
               Objects.equals(user, that.user) &&
               Objects.equals(signInTime, that.signInTime);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}