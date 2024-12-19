package system.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import system.model.DTO.AssignmentDTO;
import system.model.DTO.CourseDTO;
import system.model.DTO.CourseHasStudentDTO;
import system.model.DTO.CourseHasTeacherDTO;
import system.model.DTO.MaterialDTO;
import system.model.DTO.MaterialFileDTO;
import system.model.DTO.UserDTO;
import system.service.CloudinaryService;
import system.service.CourseHasStudentService;
import system.service.CourseHasTeacherService;
import system.service.CourseService;
import system.service.AssignmentService;
import system.service.MaterialFileService;
import system.service.MaterialService;
import system.service.UserService;

@Controller
@RequestMapping("/Teacher")
public class TeacherController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private CourseService courseService;
	
	 @Autowired
	    private CourseHasStudentService courseHasStudentService;

	 @Autowired
	 private CourseHasTeacherService courseHasTeacherService;
	 
	 @Autowired
	 private CloudinaryService cloudinaryService;
	 
	 @Autowired
	 private MaterialService materialService;
	 
	 @Autowired
	 private MaterialFileService materialFileService;
	 @Autowired
	 private AssignmentService AssignmentService;
	@RequestMapping("/home")
	public String teacherHome(Model model,HttpSession session) {
		UserDTO userDTO = (UserDTO) session.getAttribute("teacher");
		if(userDTO == null) {
			System.out.println("session is null");
			return "redirect:/";
		}
		List<CourseDTO> courses = courseService.findCoursesByTeacherId(userDTO.getId());
		model.addAttribute("courseList", courses);
		return "teacherHome";
	}
	
	 @GetMapping("/teacherCourseDetails")
	    public String getCourseDetails(@RequestParam("courseId") int courseId, Model model,HttpSession session) {
		 UserDTO userDTO = (UserDTO) session.getAttribute("teacher");
			if(userDTO == null) {
				System.out.println("session is null");
				return "redirect:/";
			}
	        CourseDTO course = courseService.findById(courseId);
	        List<CourseHasStudentDTO> enrolledStudents = courseHasStudentService.findActiveStudentByCourse(courseId);
	        List<CourseHasTeacherDTO> assignedTeachers = courseHasTeacherService.findAssignedTeacherByCourse(courseId);
	        List<AssignmentDTO> assignments = AssignmentService.findAssignmentsByCourseId(courseId);
	        List<MaterialDTO> materials = materialService.findMaterialsByCourseId(courseId);
	        model.addAttribute("loginuser", userDTO);
	        model.addAttribute("course", course);
	        model.addAttribute("enrolledStudents", enrolledStudents);
	        model.addAttribute("assignedTeachers", assignedTeachers);
	        model.addAttribute("assignments", assignments);
	        model.addAttribute("materials", materials);
	        return "teacherCourseDetail"; 
	    }
	 @GetMapping("/classWork/{courseId}")
	 public String classWork(@PathVariable("courseId")Integer courseID,HttpSession session,Model model) {
		 UserDTO userDTO = (UserDTO) session.getAttribute("teacher");
			if(userDTO == null) {
				System.out.println("session is null");
				return "redirect:/";
			}
			CourseDTO course = courseService.findById(courseID);
			List<MaterialDTO> materialList = materialService.findMaterialsByCourseId(courseID);
			model.addAttribute("course", course);
			model.addAttribute("materialList", materialList);
			model.addAttribute("MaterialDTO",new MaterialDTO());
			model.addAttribute("MaterialFileDTO",new MaterialFileDTO());
		return "teacherClasswork";
		 
	 }
	 
		 @GetMapping("/assignments/{courseId}")
		 public String showAssignments(@PathVariable("courseId")Integer courseID,HttpSession session,Model model) {
			 UserDTO userDTO = (UserDTO) session.getAttribute("teacher");
				if(userDTO == null) {
					System.out.println("session is null");
					return "redirect:/";
				} 
				CourseDTO course = courseService.findById(courseID);
				List<AssignmentDTO> assingments = AssignmentService.findAssignmentsByCourseId(courseID);
				model.addAttribute("course", course);
				model.addAttribute("assingments", assingments);
				model.addAttribute("AssignmentDTO", new AssignmentDTO());
				return "teacherAssignment";
		 }
		 

		 @PostMapping("/createassignment")
		 public String saveAssignment(
		     @ModelAttribute("AssignmentDTO") AssignmentDTO assignmentDTO,
		     @RequestParam("courseId") Integer courseId,
		     @RequestParam(value = "includeFiles", required = false) String includeFiles,
		     @RequestParam(value = "dueDates", required = false) String dueDate,HttpSession session
		 ) {
			 UserDTO userDTO = (UserDTO) session.getAttribute("teacher");
				if(userDTO == null) {
					System.out.println("session is null");
					return "redirect:/";
				} 

			 if (!"yes".equalsIgnoreCase(includeFiles)) {
			        assignmentDTO.setFiles(null);
			    }
			 if (dueDate != null && !dueDate.isEmpty()) {
			        try {
			            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
			            LocalDateTime parsedDueDate = LocalDateTime.parse(dueDate, formatter);
			            assignmentDTO.setDueDate(parsedDueDate);
			        } catch (DateTimeParseException e) {
			            // Handle invalid date format
			            System.out.println("Invalid due date format: " + dueDate);
			        }
			    }
			 assignmentDTO.setCreateTeacherID(userDTO.getId());
		     assignmentDTO.setCourseID(courseId);
		     AssignmentService.save(assignmentDTO);
		     return "redirect:/Teacher/teacherAssignment/" + courseId;
		 }
		 
	 @PostMapping("/createMaterial")
	 public String createMaterial(@ModelAttribute("MaterialDTO") MaterialDTO materialDTO,
							 		@RequestParam("courseId")Integer courseId,
							 		HttpSession session,
							 		@RequestParam("files") List<MultipartFile> files) {
		 UserDTO userDTO = (UserDTO) session.getAttribute("teacher");
			if(userDTO == null) {
				System.out.println("session is null");
				return "redirect:/";
			}
			materialDTO.setCourseID(courseId);
			materialDTO.setCreateTeacherID(userDTO.getId());
			materialDTO.setFiles(files);
			materialService.save(materialDTO);
			return "redirect:/Teacher/classWork/" + courseId;
	 }

	 
	 @GetMapping("/materialDetail/{materialId}")
	 public String showMaterialDetail(@PathVariable("materialId")Integer materialID,HttpSession session,Model model) {
		 UserDTO userDTO = (UserDTO) session.getAttribute("teacher");
			if(userDTO == null) {
				System.out.println("session is null");
				return "redirect:/";
			}
			MaterialDTO material = materialService.findById(materialID);
			List<MaterialFileDTO> files = materialFileService.getFilesByMaterialId(materialID);
			model.addAttribute("Files", files);
			model.addAttribute("material", material);
			return "teacherMaterialDetail";
	 }
	 
	 

}

	 


