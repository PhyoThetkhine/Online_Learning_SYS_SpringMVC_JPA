package system.model.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

import system.model.entity.User.Status;

@Entity
@Table(name = "course_has_student")
public class CourseHasStudent {
    @EmbeddedId
    private CourseHasStudentPK id;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne
    @JoinColumn(name = "create_admin_id", nullable = false)
    private User createAdmin;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Column(name = "update_date", nullable = false)
    private Date updateDate;

    public enum Status {
        ACTIVE, DROP
    }
    
    @PrePersist
    public void prePersist() {
    	if (this.createDate == null) {
            this.createDate = new Date(System.currentTimeMillis());
        }
        if (this.updateDate == null) {
            this.updateDate = new Date(System.currentTimeMillis());
        }
        if (this.status == null) {
            this.status = Status.ACTIVE; // Default value
        }
    }

    public CourseHasStudentPK getId() {
		return id;
	}

	public void setId(CourseHasStudentPK id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public User getCreateAdmin() {
		return createAdmin;
	}

	public void setCreateAdmin(User createAdmin) {
		this.createAdmin = createAdmin;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Embeddable
    public static class CourseHasStudentPK implements Serializable {
        public Integer getCourseId() {
			return courseId;
		}
		public void setCourseId(Integer courseId) {
			this.courseId = courseId;
		}
		public Integer getStudentId() {
			return studentId;
		}
		public void setStudentId(Integer studentId) {
			this.studentId = studentId;
		}
		private Integer courseId;
        private Integer studentId;

        // Getters and Setters, equals() and hashCode() methods
    }
}

