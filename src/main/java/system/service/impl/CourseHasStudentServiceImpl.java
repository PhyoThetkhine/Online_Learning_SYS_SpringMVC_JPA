package system.service.impl;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import system.model.DTO.CourseHasStudentDTO;
import system.model.entity.Course;
import system.model.entity.CourseHasStudent;
import system.model.entity.User;
import system.model.entity.CourseHasStudent.CourseHasStudentPK;
import system.model.mapper.CourseHasStudentMapper;
import system.model.repo.CourseHasStudentRepository;
import system.model.repo.CourseRepository;
import system.model.repo.UserRepository;
import system.service.CourseHasStudentService;

@Service
public class CourseHasStudentServiceImpl implements CourseHasStudentService{
	@Autowired
    private CourseHasStudentRepository courseHasStudentRepository;

    @Autowired
    private CourseHasStudentMapper courseHasStudentMapper;
    @Autowired
	private UserRepository userRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Override
	public List<CourseHasStudentDTO> findActiveStudentByCourse(Integer courseId) {
		List<CourseHasStudent> entities = courseHasStudentRepository.findActiveStudentByCourse(courseId);
        return entities.stream()
                       .map(courseHasStudentMapper::toDTO)
                       .collect(Collectors.toList());
	}
	
	@Override
	public List<CourseHasStudentDTO> findDropStudentByCourse(Integer courseId) {
		List<CourseHasStudent> entities = courseHasStudentRepository.findDropStudentByCourse(courseId);
        return entities.stream()
                       .map(courseHasStudentMapper::toDTO)
                       .collect(Collectors.toList());
	}

	@Override
	public CourseHasStudentDTO getCourseHasStudentById(CourseHasStudentPK id) {
		CourseHasStudent entity = courseHasStudentRepository.findById(id);
        return courseHasStudentMapper.toDTO(entity);
	}	
	@Override
	public void saveCourseHasStudent(CourseHasStudentDTO dto) {
		User student = userRepository.findById(dto.getStudentId());
		dto.setStudent(student);
		Course course = courseRepository.findById(dto.getCourseId());
		dto.setCourse(course);
		User createAdmin = userRepository.findById(dto.getCreateAdminId());
		dto.setCreateAdmin(createAdmin);
		CourseHasStudent entity = courseHasStudentMapper.toEntity(dto);
        courseHasStudentRepository.save(entity);
	}
	@Override
	public void deleteCourseHasStudent(CourseHasStudentPK id) {
		 courseHasStudentRepository.deleteById(id);
	}

	@Override
	public void reEnrollCourseHasStudent(CourseHasStudentPK id) {
		courseHasStudentRepository.reEnrollById(id);
	}
	@Override
	public List<CourseHasStudentDTO> getAll() {
	    List<CourseHasStudent> entities = courseHasStudentRepository.getAll();
	    return entities.stream()
	                   .map(courseHasStudentMapper::toDTO)
	                   .collect(Collectors.toList());
	}

	

}
