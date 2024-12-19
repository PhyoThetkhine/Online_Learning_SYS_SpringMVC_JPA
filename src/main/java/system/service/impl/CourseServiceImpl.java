package system.service.impl;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import system.model.DTO.CourseDTO;
import system.model.entity.Course;
import system.model.entity.User;
import system.model.mapper.CourseMapper;
import system.model.repo.CourseRepository;
import system.model.repo.UserRepository;
import system.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
    private CourseMapper courseMapper;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private UserRepository userRepository;
	
	

    @Override
    public void save(CourseDTO courseDTO) {
    	User createAdmin = userRepository.findById(courseDTO.getCreateAdminId());
    	courseDTO.setCreateAdmin(createAdmin);
        Course course = courseMapper.toEntity(courseDTO);
        
        courseRepository.save(course);
    }

    @Override
    public CourseDTO findById(Integer id) {
        Course course = courseRepository.findById(id);  
        if (course != null) {
            return courseMapper.toDTO(course);  
        }
        return null;
    }

    @Override
    public List<CourseDTO> findAll() {
        List<Course> courses = courseRepository.findAll();  // Use repository to fetch all courses
        return courses.stream()
                      .map(courseMapper::toDTO)
                      .collect(Collectors.toList());
    }

    @Override
    public void update(CourseDTO courseDTO) {
        Course existingCourse = courseRepository.findById(courseDTO.getId());  // Fetch course via repository
        if (existingCourse != null) {
        	if(courseDTO.getPhotoUrl() != null) {
            	existingCourse.setPhotoUrl(courseDTO.getPhotoUrl());
            	 courseRepository.update(existingCourse); 
        	}else {
        	existingCourse.setTitle(courseDTO.getTitle());
        	existingCourse.setDescription(courseDTO.getDescription());
        	existingCourse.setStatus(Course.Status.valueOf(courseDTO.getStatus()));
        	existingCourse.setUpdateDate(new Date(System.currentTimeMillis()));
            courseRepository.update(existingCourse);  
        	}
        }
    }

    @Override
    public void delete(CourseDTO courseDTO) {
        courseRepository.delete(courseDTO.getId());  // Use repository to delete course by ID
    }

	@Override
	public List<CourseDTO> findCoursesByTeacherId(Integer teacherId) {
		List<Course> courses =courseRepository.findCoursesByTeacherId(teacherId);
		return courses.stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
	}
}
