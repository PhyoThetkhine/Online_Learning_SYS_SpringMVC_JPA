package system.model.entity;

import java.sql.Date;

import javax.persistence.*;

import system.model.entity.CourseHasTeacher.Status;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "description", nullable = false, columnDefinition = "MEDIUMTEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Column(name = "update_date", nullable = false)
    private Date updateDate;

    @Column(name = "photo_url", nullable = false, length = 100)
    private String photoUrl;

    @ManyToOne
    @JoinColumn(name = "create_admin_id", nullable = false)
    private User createAdmin;

    public enum Status {
        COMPLETED, ACTIVE
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public User getCreateAdmin() {
		return createAdmin;
	}

	public void setCreateAdmin(User createAdmin) {
		this.createAdmin = createAdmin;
	}

   
}
