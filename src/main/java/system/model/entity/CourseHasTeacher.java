package system.model.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import javax.persistence.*;

import system.model.entity.CourseHasStudent.Status;

@Entity
@Table(name = "course_has_teacher")
public class CourseHasTeacher {
    @EmbeddedId
    private CourseHasTeacherPK id;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @MapsId("teacherId")
    @JoinColumn(name = "teacher_id")
    private User teacher;

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
    public CourseHasTeacherPK getId() {
		return id;
	}



	public void setId(CourseHasTeacherPK id) {
		this.id = id;
	}



	public Course getCourse() {
		return course;
	}



	public void setCourse(Course course) {
		this.course = course;
	}



	public User getTeacher() {
		return teacher;
	}



	public void setTeacher(User teacher) {
		this.teacher = teacher;
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
    public static class CourseHasTeacherPK implements Serializable {
        private Integer courseId;
        private Integer teacherId;
		public Integer getCourseId() {
			return courseId;
		}
		public void setCourseId(Integer courseId) {
			this.courseId = courseId;
		}
		public Integer getTeacherId() {
			return teacherId;
		}
		public void setTeacherId(Integer teacherId) {
			this.teacherId = teacherId;
		}
		@Override
		public int hashCode() {
			return Objects.hash(courseId, teacherId);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CourseHasTeacherPK other = (CourseHasTeacherPK) obj;
			return Objects.equals(courseId, other.courseId) && Objects.equals(teacherId, other.teacherId);
		}

       
    }
}