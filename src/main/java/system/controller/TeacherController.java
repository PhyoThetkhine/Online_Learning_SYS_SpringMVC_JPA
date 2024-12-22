	package system.controller;
	
	import java.io.IOException;
	import java.io.InputStream;
	import java.net.URL;
	import java.time.LocalDateTime;
	import java.time.format.DateTimeFormatter;
	import java.time.format.DateTimeParseException;
	import java.util.List;
	
	import javax.servlet.http.HttpServletResponse;
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
	import system.model.DTO.AssignmentFileDTO;
	import system.model.DTO.CourseDTO;
	import system.model.DTO.CourseHasStudentDTO;
	import system.model.DTO.CourseHasTeacherDTO;
	import system.model.DTO.MaterialDTO;
	import system.model.DTO.MaterialFileDTO;
import system.model.DTO.SubmissionDTO;
import system.model.DTO.SubmissionFileDTO;
import system.model.DTO.UserDTO;
	import system.service.CloudinaryService;
	import system.service.CourseHasStudentService;
	import system.service.CourseHasTeacherService;
	import system.service.CourseService;
	import system.service.AssignmentFileService;
	import system.service.AssignmentService;
	import system.service.MaterialFileService;
	import system.service.MaterialService;
import system.service.SubmissionFileService;
import system.service.SubmissionService;
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
		 private MaterialService materialService;
		 
		 @Autowired
		 private MaterialFileService materialFileService;
		 @Autowired
		 private AssignmentService AssignmentService;
		 @Autowired
		 private AssignmentFileService assignmentFileService;
		 @Autowired
		 private SubmissionService submissionService;
		 @Autowired
		 private SubmissionFileService submissionFileService;
		 
		 
		 
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
	        List<AssignmentDTO> assignments = AssignmentService.findAssignmentsByCourseId(courseId);
	        List<MaterialDTO> materials = materialService.findMaterialsByCourseId(courseId);
	        model.addAttribute("loginuser", userDTO);
	        model.addAttribute("course", course);
	        model.addAttribute("assignments", assignments);
	        model.addAttribute("materials", materials);
	        return "teacherCourseDetail"; 
	    }
	 @PostMapping("/changePassword")
	 public String changePassword( @RequestParam("currentPassword") String currentPassword,
	                              @RequestParam("newPassword") String newPassword,
	                              Model model,HttpSession session) {
	 	UserDTO userDTO = (UserDTO) session.getAttribute("teacher");
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
	     return "redirect:/Teacher/home"; 
	 }
	 @GetMapping("teacherCoursePeople")
	 public String showPeople(@RequestParam("courseId") int courseId,Model model,HttpSession session) {
		 UserDTO userDTO = (UserDTO) session.getAttribute("teacher");
			if(userDTO == null) {
				System.out.println("session is null");
				return "redirect:/";
			}
			 CourseDTO course = courseService.findById(courseId);
		        List<CourseHasStudentDTO> enrolledStudents = courseHasStudentService.findActiveStudentByCourse(courseId);
		        List<CourseHasTeacherDTO> assignedTeachers = courseHasTeacherService.findAssignedTeacherByCourse(courseId);
		        model.addAttribute("course", course);
		        model.addAttribute("enrolledStudents", enrolledStudents);
		        model.addAttribute("assignedTeachers", assignedTeachers);
		        model.addAttribute("loginuser", userDTO);
				return "teacherCoursePeople";
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
	 @GetMapping("/downloadMaterialFile")
	 public void downloadMaterialFile(@RequestParam("fileId") Integer fileId, HttpServletResponse response) {
	     try {
	         // Retrieve file details by ID
	         MaterialFileDTO fileDTO = materialFileService.getMaterialFileById(fileId);

	         if (fileDTO != null) {
	             // Fetch file URL
	             String fileUrl = fileDTO.getFileUrl();

	             // Extract or generate a filename
	             String fileName = fileUrl.substring(fileUrl.lastIndexOf('/') + 1);
	             if (fileName.isEmpty()) {
	                 fileName = "downloaded_file"; // Default fallback
	             }

	             // Open input stream from the file URL
	             URL url = new URL(fileUrl);
	             InputStream inputStream = url.openStream();

	             // Set response headers for file download
	             response.setContentType("application/octet-stream");
	             response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

	             // Write file content to response
	             byte[] buffer = new byte[4096];
	             int bytesRead;
	             while ((bytesRead = inputStream.read(buffer)) != -1) {
	                 response.getOutputStream().write(buffer, 0, bytesRead);
	             }

	             inputStream.close();
	         } else {
	             response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
	         }
	     } catch (IOException e) {
	         e.printStackTrace();
	         try {
	             response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error downloading file");
	         } catch (IOException ioException) {
	             ioException.printStackTrace();
	         }
	     }
	 }
	 @GetMapping("/downloadsubmissionFile")
	 public void downloadSubmissionFile(@RequestParam("fileId") Integer fileId, HttpServletResponse response) {
	     try {
	    	 SubmissionFileDTO fileDTO = submissionFileService.getFileById(fileId);
	    	 
	         if (fileDTO != null) {
	             // Fetch file URL
	             String fileUrl = fileDTO.getFileUrl();

	             // Extract or generate a filename
	             String fileName = fileUrl.substring(fileUrl.lastIndexOf('/') + 1);
	             if (fileName.isEmpty()) {
	                 fileName = "downloaded_file"; // Default fallback
	             }

	             // Open input stream from the file URL
	             URL url = new URL(fileUrl);
	             InputStream inputStream = url.openStream();

	             // Set response headers for file download
	             response.setContentType("application/octet-stream");
	             response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

	             // Write file content to response
	             byte[] buffer = new byte[4096];
	             int bytesRead;
	             while ((bytesRead = inputStream.read(buffer)) != -1) {
	                 response.getOutputStream().write(buffer, 0, bytesRead);
	             }

	             inputStream.close();
	         } else {
	             response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
	         }
	     } catch (IOException e) {
	         e.printStackTrace();
	         try {
	             response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error downloading file");
	         } catch (IOException ioException) {
	             ioException.printStackTrace();
	         }
	     }
	 }
	 
	 @GetMapping("/submissioinDetail")
	 public String showSubmissionDetail(@RequestParam("submissionID") Integer submissionID,HttpSession session,Model model) {
		 UserDTO userDTO = (UserDTO) session.getAttribute("teacher");
			if(userDTO == null) {
				System.out.println("session is null");
				return "redirect:/";
			} 
			SubmissionDTO submission = submissionService.findById(submissionID);
			List<SubmissionFileDTO> files = submissionFileService.getFilesBySubmissionId(submissionID);
			model.addAttribute("submission", submission);
			model.addAttribute("Files", files);
			return "teacherSubmissionDetail";
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
		     return "redirect:/Teacher/teacherCourseDetails?courseId=" + courseId;
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
			return "redirect:/Teacher/teacherCourseDetails?courseId=" + courseId;
	 }
	 @PostMapping("/addingNewMaterailFile")
	    public String addNewMaterialFile(@RequestParam("files") List<MultipartFile> files,
	                                     @RequestParam("materialId") Integer materialId,HttpSession session,
	                                     Model model) {
		 UserDTO userDTO = (UserDTO) session.getAttribute("teacher");
			if(userDTO == null) {
				System.out.println("session is null");
				return "redirect:/";
			}
			MaterialFileDTO file = new MaterialFileDTO();
			file.setFiles(files);
			file.setMaterialID(materialId);
			materialFileService.addnewFile(file);
		 return "redirect:/Teacher/materialDetail/" + materialId;
	 }
	 
	 @PostMapping("/addNewAssignmentFile")
	 public String addNewAssignmentFile(@RequestParam("files") List<MultipartFile> files,
			 @RequestParam("assignmentId") Integer assignmentId,HttpSession session,
	                                     Model model) {
		 UserDTO userDTO = (UserDTO) session.getAttribute("teacher");
			if(userDTO == null) {
				System.out.println("session is null");
				return "redirect:/";
			}
			AssignmentFileDTO file = new AssignmentFileDTO();
			file.setFiles(files);
			file.setAssignmentID(assignmentId);
			assignmentFileService.addnewFile(file);
			return "redirect:/Teacher/assignmentDetail/" + assignmentId;
		 
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
	 
	 @PostMapping("/deleteMaterial/{materialId}")
	 public String delecteMaterial(@PathVariable("materialId")Integer materialID,HttpSession session,@RequestParam("courseId") Integer courseId) {
		 UserDTO userDTO = (UserDTO) session.getAttribute("teacher");
			if(userDTO == null) {
				System.out.println("session is null");
				return "redirect:/";
			}
			materialService.delete(materialID);
			return "redirect:/Teacher/teacherCourseDetails?courseId=" + courseId;
	 }
	 @PostMapping("/deleteAssignment/{assignmentId}")
	 public String delecteAssignment(@PathVariable("assignmentId")Integer assignmentId,HttpSession session,@RequestParam("courseId") Integer courseId) {
		 UserDTO userDTO = (UserDTO) session.getAttribute("teacher");
			if(userDTO == null) {
				System.out.println("session is null");
				return "redirect:/";
			}
			AssignmentService.delete(assignmentId);
			return "redirect:/Teacher/teacherCourseDetails?courseId=" + courseId;
	 }
	 @GetMapping("/assignmentDetail/{assignmentId}")
	 public String showAssignmentDetail(@PathVariable("assignmentId") Integer assignmentId, HttpSession session, Model model) {
	     UserDTO userDTO = (UserDTO) session.getAttribute("teacher");
	     if (userDTO == null) {
	         System.out.println("Session is null");
	         return "redirect:/";
	     }
	     System.out.println(assignmentId+"id");
	     AssignmentDTO assignment = AssignmentService.findById(assignmentId);
	     List<AssignmentFileDTO> files = assignmentFileService.getFilesByAssignmentId(assignmentId);
	     List<SubmissionDTO> submission = submissionService.findSubmissionsByAssignmentId(assignmentId);
	     model.addAttribute("submission", submission);
	     model.addAttribute("Files", files);
	     model.addAttribute("assignment", assignment);
	     return "teacherAssignDetail";
	 }
	 
	 
	 
	 

}

	 


