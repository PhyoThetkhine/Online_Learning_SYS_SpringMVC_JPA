package system.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import system.model.DTO.RoleDTO;
import system.model.DTO.UserDTO;
import system.model.entity.CourseHasStudent.CourseHasStudentPK;
import system.model.entity.CourseHasTeacher.CourseHasTeacherPK;
import system.model.DTO.CourseDTO;
import system.model.DTO.CourseHasStudentDTO;
import system.model.DTO.CourseHasTeacherDTO;
import system.service.CourseHasStudentService;
import system.service.CourseHasTeacherService;
import system.service.CourseService;
import system.service.EmailService;
import system.service.PasswordService;
import system.service.RoleService;
import system.service.UserService;

@Controller
@RequestMapping("/Admin")
public class AdminController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private PasswordService passwordService;
	
	@Autowired
	private CourseService courseService;
	
	 @Autowired
	    private CourseHasStudentService courseHasStudentService;

	 @Autowired
	 private CourseHasTeacherService courseHasTeacherService;
	 
	 @Autowired
	    private  Cloudinary cloudinary;
	
	@RequestMapping("/home")
	public String adminHome(Model model,HttpSession session) {
		UserDTO userDTO = (UserDTO) session.getAttribute("admin");
		if(userDTO == null) {
			System.out.println("session is null");
			return "redirect:/";
		}
		List<CourseDTO> courses = courseService.findAll();
		model.addAttribute("courseList", courses);
		model.addAttribute("CourseDTO",new CourseDTO());
		return "adminHome";
	}
	@PostMapping("ExportStudentEnrollment")
    public void exportEnrollmentListToPdf(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=StudentEnrollList.pdf");

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // Title
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
            Paragraph title = new Paragraph("Student Enrollment List", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("\n"));

            // Table
            PdfPTable table = new PdfPTable(5); // Adjusted to match columns
            table.setWidthPercentage(100);
            table.setWidths(new float[]{2, 4, 4, 4, 3});

            Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            table.addCell(new PdfPCell(new Paragraph("No.", headerFont)));
            table.addCell(new PdfPCell(new Paragraph("Student Name", headerFont)));
            table.addCell(new PdfPCell(new Paragraph("Course", headerFont)));
            table.addCell(new PdfPCell(new Paragraph("Create Admin", headerFont)));
            table.addCell(new PdfPCell(new Paragraph("Status", headerFont)));

            List<CourseHasStudentDTO> studentsWithCourses = courseHasStudentService.getAll();
            for (int i = 0; i < studentsWithCourses.size(); i++) {
                CourseHasStudentDTO dto = studentsWithCourses.get(i);
                table.addCell(String.valueOf(i + 1)); // No.
                table.addCell(dto.getStudent().getName()); // Student Name
                table.addCell(dto.getCourse().getTitle()); // Course
                table.addCell(dto.getCreateAdminName()); // Admin Name
                table.addCell(dto.getStatus()); // Status
            }

            document.add(table);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
	@PostMapping("ExportTeacherEnrollment")
    public void exportTeacherEnrollmentListToPdf(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=TeacherEnrollList.pdf");

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // Title
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
            Paragraph title = new Paragraph("Teacher Enrollment List", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("\n"));

            // Table
            PdfPTable table = new PdfPTable(5); // Adjusted to match columns
            table.setWidthPercentage(100);
            table.setWidths(new float[]{2, 4, 4, 4, 3});

            Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            table.addCell(new PdfPCell(new Paragraph("No.", headerFont)));
            table.addCell(new PdfPCell(new Paragraph("Teacher Name", headerFont)));
            table.addCell(new PdfPCell(new Paragraph("Course", headerFont)));
            table.addCell(new PdfPCell(new Paragraph("Create Admin", headerFont)));
            table.addCell(new PdfPCell(new Paragraph("Status", headerFont)));

            List<CourseHasTeacherDTO> teachersWithCourses = courseHasTeacherService.getAll();
            for (int i = 0; i < teachersWithCourses.size(); i++) {
            	CourseHasTeacherDTO dto = teachersWithCourses.get(i);
                table.addCell(String.valueOf(i + 1)); // No.
                table.addCell(dto.getTeacher().getName()); // Student Name
                table.addCell(dto.getCourse().getTitle()); // Course
                table.addCell(dto.getCreateAdminName()); // Admin Name
                table.addCell(dto.getStatus()); // Status
            }

            document.add(table);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
	@GetMapping("/StudentEntrollment")
    public String getStudentEnrollments(Model model,HttpSession session) {
		UserDTO userDTO = (UserDTO) session.getAttribute("admin");
		if(userDTO == null) {
			System.out.println("session is null");
			return "redirect:/";
		}
            List<CourseHasStudentDTO> studentsEntrollList = courseHasStudentService.getAll();
            model.addAttribute("studentsEntrollList", studentsEntrollList);
       
        return "adminStudentEntrollMent"; // Ensure this corresponds to your JSP file name
    }
	@GetMapping("/TeacherEntrollment")
    public String TeacherEntrollment(Model model,HttpSession session) {
		UserDTO userDTO = (UserDTO) session.getAttribute("admin");
		if(userDTO == null) {
			System.out.println("session is null");
			return "redirect:/";
		}
            List<CourseHasTeacherDTO> TeachersEntrollList = courseHasTeacherService.getAll();
            model.addAttribute("TeachersEntrollList", TeachersEntrollList);
       
        return "adminTeacherEnrollMent"; 
    }
	@PostMapping("/changePassword")
	public String changePassword( @RequestParam("currentPassword") String currentPassword,
	                             @RequestParam("newPassword") String newPassword,
	                             Model model,HttpSession session) {
		UserDTO userDTO = (UserDTO) session.getAttribute("admin");
		if(userDTO == null) {
			System.out.println("session is null");
			return "redirect:/";
		}
	    boolean isChanged = userService.changePassword(userDTO.getId(), currentPassword, newPassword);
	    if (isChanged) {
	        model.addAttribute("message", "Password changed successfully.");
	    } else {
	        model.addAttribute("error", "Current password is incorrect.");
	    }
	    return "redirect:/Student/home"; 
	}
	
	@PostMapping("/createCourse")
	public String createCourse(@ModelAttribute("CourseDTO") CourseDTO courseDTO,
							@RequestParam("file") MultipartFile photo,Model model,HttpSession session) throws IOException {
		UserDTO userDTO = (UserDTO) session.getAttribute("admin");
		if(userDTO == null) {
			System.out.println("session is null");
			return "redirect:/";
		}
		
		Map uploadResult = cloudinary.uploader().upload(photo.getBytes(), ObjectUtils.asMap("resource_type", "image"));
        String imageUrl = (String) uploadResult.get("secure_url");
        courseDTO.setPhotoUrl(imageUrl);
        courseDTO.setCreateAdminId(userDTO.getId());
        courseService.save(courseDTO);
		return "redirect:/Admin/home"; 
	}
	
	 @GetMapping("/courseDetails")
	    public String getCourseDetails(@RequestParam("courseId") int courseId, Model model,HttpSession session) {
		 UserDTO userDTO = (UserDTO) session.getAttribute("admin");
			if(userDTO == null) {
				System.out.println("session is null");
				return "redirect:/";
			}
	        // Fetch course details from the service
	        CourseDTO course = courseService.findById(courseId);
	        List<CourseHasStudentDTO> enrolledStudents = courseHasStudentService.findActiveStudentByCourse(courseId);
	        List<CourseHasStudentDTO> DropStudents = courseHasStudentService.findDropStudentByCourse(courseId);
	        List<CourseHasTeacherDTO> assignedTeachers = courseHasTeacherService.findAssignedTeacherByCourse(courseId);
	        List<CourseHasTeacherDTO> DropTeachers = courseHasTeacherService.findDropTeacherByCourse(courseId);
	        List<UserDTO> studentList = userService.findStudentsNotEnrolledInCourse(courseId);
	        List<UserDTO> teacherList = userService.findTeachersNotAssignedToCourse(courseId);
	        model.addAttribute("DropTeachers", DropTeachers);
	        model.addAttribute("DropStudents", DropStudents);
	        model.addAttribute("studentList", studentList);
	        model.addAttribute("teacherList", teacherList);
	        model.addAttribute("course", course);
	        model.addAttribute("enrolledStudents", enrolledStudents);
	        model.addAttribute("assignedTeachers", assignedTeachers);
	        return "adminCourseDetails"; 
	    }
	 
	 @PostMapping("/assignStudent")
	 public String assignStudent(@RequestParam("courseId") Integer courseId,
             @RequestParam("studentId") Integer studentId,
             Model model,HttpSession session) {
		 UserDTO userDTO = (UserDTO) session.getAttribute("admin");
			if(userDTO == null) {
				System.out.println("session is null");
				return "redirect:/";
			}
		 CourseHasStudentDTO dto = new CourseHasStudentDTO();
		 dto.setCreateAdminId(userDTO.getCreateAdminId());
         dto.setCourseId(courseId);
         dto.setStudentId(studentId);
         courseHasStudentService.saveCourseHasStudent(dto);
         return "redirect:/Admin/courseDetails?courseId=" + courseId; 
	 }
	 @PostMapping("/assignTeacher")
	 public String assignTeacher(@RequestParam("courseId") Integer courseId,
             @RequestParam("teacherId") Integer teacherId,
             Model model,HttpSession session) {
		 UserDTO userDTO = (UserDTO) session.getAttribute("admin");
			if(userDTO == null) {
				System.out.println("session is null");
				return "redirect:/";
			}
		 CourseHasTeacherDTO dto = new CourseHasTeacherDTO();
		 dto.setCreateAdminId(userDTO.getCreateAdminId());
         dto.setCourseId(courseId);
         dto.setTeacherId(teacherId);
         courseHasTeacherService.saveCourseHasTeacher(dto);
         return "redirect:/Admin/courseDetails?courseId=" + courseId; 
	 }
	 
	 @PostMapping("/DeleteTeacherEntroll")
	 public String deleteTeacherAssign(@RequestParam("courseId") Integer courseId, @RequestParam("teacherId") Integer teacherId,HttpSession session) {
		 UserDTO userDTO = (UserDTO) session.getAttribute("admin");
			if(userDTO == null) {
				System.out.println("session is null");
				return "redirect:/";
			}
			CourseHasTeacherDTO dto = new CourseHasTeacherDTO();
			CourseHasTeacherPK id = new CourseHasTeacherPK();
		    id.setCourseId(courseId);
		    id.setTeacherId(teacherId);
		    courseHasTeacherService.deleteCourseHasTeacher(id);
		    return "redirect:/Admin/courseDetails?courseId=" + courseId; 
	 }
	 @PostMapping("/DeleteEntrollStudent")
	 public String DeleteEntrollStudent(@RequestParam("courseId") Integer courseId, @RequestParam("studentId") Integer studentId,HttpSession session) {
		 UserDTO userDTO = (UserDTO) session.getAttribute("admin");
			if(userDTO == null) {
				System.out.println("session is null");
				return "redirect:/";
			}
			CourseHasStudentDTO dto = new CourseHasStudentDTO();
			CourseHasStudentPK id = new CourseHasStudentPK();
		    id.setCourseId(courseId);
		    id.setStudentId(studentId);
		    courseHasStudentService.deleteCourseHasStudent(id);
		    return "redirect:/Admin/courseDetails?courseId=" + courseId; 
	 }
	 @PostMapping("/reenrolledStudent")
	 public String ReEntrollStudent(@RequestParam("courseId") Integer courseId, @RequestParam("studentId") Integer studentId,HttpSession session) {
		 UserDTO userDTO = (UserDTO) session.getAttribute("admin");
			if(userDTO == null) {
				System.out.println("session is null");
				return "redirect:/";
			}
			CourseHasStudentPK id = new CourseHasStudentPK();
		    id.setCourseId(courseId);
		    id.setStudentId(studentId);
		    courseHasStudentService.reEnrollCourseHasStudent(id);
		    return "redirect:/Admin/courseDetails?courseId=" + courseId; 
	 }
	 @PostMapping("/reenrolledTeacher")
	 public String ReEntrollTeacher(@RequestParam("courseId") Integer courseId, @RequestParam("teacherId") Integer studentId,HttpSession session) {
		 UserDTO userDTO = (UserDTO) session.getAttribute("admin");
			if(userDTO == null) {
				System.out.println("session is null");
				return "redirect:/";
			}
			CourseHasTeacherPK id = new CourseHasTeacherPK();
		    id.setCourseId(courseId);
		    id.setTeacherId(studentId);
		    courseHasTeacherService.reEnrollCourseHasTeacher(id);
		    return "redirect:/Admin/courseDetails?courseId=" + courseId; 
	 }
	 
	 @PostMapping("/courseEdit")
	 public String courseEdit(@RequestParam("courseId")Integer courseId,
			 @RequestParam("title")String title,
			 @RequestParam("description")String description,@RequestParam("status")String status,HttpSession session) {
		 UserDTO userDTO = (UserDTO) session.getAttribute("admin");
			if(userDTO == null) {
				return "redirect:/";
			}
		 CourseDTO dto = new CourseDTO();
		 dto.setId(courseId);
		 dto.setTitle(title);
		 dto.setDescription(description);
		 dto.setStatus(status);
		 courseService.update(dto);
		 return "redirect:/Admin/courseDetails?courseId=" + courseId; 
	 }
	 @PostMapping("/editCoursePhoto")
	 public String editCoursePhoto(@RequestParam("coursePhoto") MultipartFile coursePhoto,@RequestParam("courseId")Integer courseId,HttpSession session) throws IOException {
		 UserDTO userDTO = (UserDTO) session.getAttribute("admin");
			if(userDTO == null) {
				System.out.println("session is null");
				return "redirect:/";
			}
			Map uploadResult = cloudinary.uploader().upload(coursePhoto.getBytes(), ObjectUtils.asMap("resource_type", "image"));
	           String imageUrl = (String) uploadResult.get("secure_url");
	           CourseDTO dto = new CourseDTO();
	           dto.setId(courseId);
	           dto.setPhotoUrl(imageUrl);
	           courseService.update(dto);
			 return "redirect:/Admin/courseDetails?courseId=" + courseId; 
		 
	 }
	 
	
	//User Management Session 
	@RequestMapping("/StudentList")
	  public String showStudentList(Model model,HttpSession session) {
		UserDTO userDTO = (UserDTO) session.getAttribute("admin");
		if(userDTO == null) {
			System.out.println("session is null");
			return "redirect:/";
		}
		List<UserDTO> students =  userService.findUserWhereRoleIsStudent();
		model.addAttribute("students", students);
		model.addAttribute("student", new UserDTO());
		return "adminStudentList";
	  }
	@RequestMapping("/TeacherList")
	  public String showTeacherList(Model model,HttpSession session) {
		UserDTO userDTO = (UserDTO) session.getAttribute("admin");
		if(userDTO == null) {
			System.out.println("session is null");
			return "redirect:/";
		}
		List<UserDTO> teachers =  userService.findUserWhereRoleIsTeacher();
		System.out.println(teachers);
		model.addAttribute("teachers", teachers);
		return "adminTeacherList";
	  }
	@PostMapping("/addnewStudent")
	public String addNewStudent(@RequestParam("name") String name,@RequestParam("email") String email,Model model,HttpSession session) {
		UserDTO userDTO = (UserDTO) session.getAttribute("admin");
		if(userDTO == null) {
			return "redirect:/";
		}
		RoleDTO studentRole = roleService.getRoleByRoleName("Student");
		String genPassword = passwordService.generatePassword();
		UserDTO student = new UserDTO();
		student.setName(name); // Set the name
		student.setEmail(email);
		student.setPassword(genPassword);
		student.setRoleId(studentRole.getId());
		student.setCreateAdminId(userDTO.getId());
		userService.saveUser(student);
		emailService.sendPassword(student.getEmail(), genPassword);
		return "redirect:/Admin/StudentList";
	}
	@PostMapping("/addnewTeacher")
	public String addNewTeacher(@RequestParam("name") String name,@RequestParam("email") String email,Model model,HttpSession session) {
		UserDTO userDTO = (UserDTO) session.getAttribute("admin");
		if(userDTO == null) {
			return "redirect:/";
		}
		RoleDTO teacherRole = roleService.getRoleByRoleName("Teacher");
		String genPassword = passwordService.generatePassword();
		UserDTO teacher = new UserDTO();
		teacher.setName(name); // Set the name
		teacher.setEmail(email);
		teacher.setPassword(genPassword);
		teacher.setRoleId(teacherRole.getId());
		teacher.setCreateAdminId(userDTO.getId());
		userService.saveUser(teacher);
		emailService.sendPassword(teacher.getEmail(), genPassword);
		return "redirect:/Admin/TeacherList";
	}
	  @PostMapping("/updateStudentStatus")
	    public String updateStudentStatus(@RequestParam("userId") Integer userid,
	                                      @RequestParam("newStatus") String newStatus,HttpSession session) {
		  UserDTO userDTO = (UserDTO) session.getAttribute("admin");
			if(userDTO == null) {
				return "redirect:/";
			}
	        userService.updateStatus(userid, newStatus); // Call the updateStatus method
	        return "redirect:/Admin/StudentList"; // Redirect to the list of students or another relevant page
	    }
	  @PostMapping("/updateTeacherStatus")
	    public String updateTeacherStatus(@RequestParam("userId") Integer userid,
	                                      @RequestParam("newStatus") String newStatus,HttpSession session) {
		  UserDTO userDTO = (UserDTO) session.getAttribute("admin");
			if(userDTO == null) {
				return "redirect:/";
			}
	        userService.updateStatus(userid, newStatus); // Call the updateStatus method
	        return "redirect:/Admin/TeacherList"; // Redirect to the list of teachers or another relevant page
	    }
	
	  
	  
	

}
