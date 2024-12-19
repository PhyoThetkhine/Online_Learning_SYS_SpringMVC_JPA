package system.model.entity;


import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.*;

import system.model.entity.CourseHasTeacher.Status;


@Entity
@Table(name = "app_announcement")
public class AppAnnouncement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "MEDIUMTEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "create_user_id", nullable = false)
    private User createTeacher;

    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Column(name = "update_date", nullable = false)
    private Date updateDate;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;
    
    @Column(name = "scheduled_at", nullable = true)
    private LocalDateTime scheduledAt;
    
    @Column(name = "published_at", nullable = true)
    private LocalDateTime publishedAt;
    

	public enum Status {
    	DRAFT,PUBLISHED,SCHEDULED
    }
	 @PrePersist
	    public void prePersist() {
	    	if (this.createDate == null) {
	            this.createDate = new Date(System.currentTimeMillis());
	        }
	        if (this.updateDate == null) {
	            this.updateDate = new Date(System.currentTimeMillis());
	        }
	    }
    
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getCreateTeacher() {
		return createTeacher;
	}

	public void setCreateTeacher(User createTeacher) {
		this.createTeacher = createTeacher;
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

    
}
