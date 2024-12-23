package system.service.impl;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import system.model.DTO.CourseHasTeacherDTO;
import system.model.entity.Course;
import system.model.entity.CourseHasStudent;
import system.model.entity.CourseHasTeacher;
import system.model.entity.CourseHasTeacher.CourseHasTeacherPK;
import system.model.entity.User;
import system.model.mapper.CourseHasTeacherMapper;
import system.model.repo.CourseHasTeacherRepository;
import system.model.repo.CourseRepository;
import system.model.repo.UserRepository;
import system.service.CourseHasTeacherService;

@Service
public class CourseHasTeacherServiceImpl implements CourseHasTeacherService {
	@Autowired
	private CourseHasTeacherRepository courseHasTeacherRepository;

	@Autowired
	private CourseHasTeacherMapper courseHasTeacherMapper;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Override
	public List<CourseHasTeacherDTO> findAssignedTeacherByCourse(Integer courseId) {
		List<CourseHasTeacher> entities = courseHasTeacherRepository.findActiveTeacherByCourse(courseId);
		 return entities.stream()
                 .map(courseHasTeacherMapper::toDTO)
                 .collect(Collectors.toList());
	}

	@Override
	public CourseHasTeacherDTO getCourseHasTeacherById(CourseHasTeacherPK id) {
		CourseHasTeacher entity = courseHasTeacherRepository.findById(id);
        return courseHasTeacherMapper.toDTO(entity);
	}

	@Override
	public void saveCourseHasTeacher(CourseHasTeacherDTO dto) {
		User teacher = userRepository.findById(dto.getTeacherId());
		dto.setTeacher(teacher);
		Course course = courseRepository.findById(dto.getCourseId());
		dto.setCourse(course);
		User createAdmin = userRepository.findById(dto.getCreateAdminId());
		dto.setCreateAdmin(createAdmin);
		CourseHasTeacher entity = courseHasTeacherMapper.toEntity(dto);
		courseHasTeacherRepository.save(entity);
	}

	@Override
	public void deleteCourseHasTeacher(CourseHasTeacherPK id) {
		courseHasTeacherRepository.deleteById(id);
		
	}

	@Override
	public void reEnrollCourseHasTeacher(CourseHasTeacherPK id) {
		courseHasTeacherRepository.reEnrollById(id);
	}

	@Override
	public List<CourseHasTeacherDTO> findDropTeacherByCourse(Integer courseId) {
		List<CourseHasTeacher> entities = courseHasTeacherRepository.findDropTeacherByCourse(courseId);
		 return entities.stream()
                .map(courseHasTeacherMapper::toDTO)
                .collect(Collectors.toList());
	}
	@Override
	public List<CourseHasTeacherDTO> getAll() {
	    List<CourseHasTeacher> entities = courseHasTeacherRepository.findAll();
	    return entities.stream()
	                   .map(courseHasTeacherMapper::toDTO)
	                   .collect(Collectors.toList());
	}

}
