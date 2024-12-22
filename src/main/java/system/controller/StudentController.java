package system.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
import system.service.AssignmentFileService;
import system.service.AssignmentService;
import system.service.CourseHasStudentService;
import system.service.CourseHasTeacherService;
import system.service.CourseService;
import system.service.MaterialFileService;
import system.service.MaterialService;
import system.service.SubmissionFileService;
import system.service.SubmissionService;
import system.service.UserService;


@Controller
@RequestMapping("/Student")
public class StudentController {
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
	UserDTO userDTO = (UserDTO) session.getAttribute("student");
	if(userDTO == null) {
		System.out.println("session is null");
		return "redirect:/";
	}
	List<CourseDTO> courses = courseService.findCoursesByStudentId(userDTO.getId());
	model.addAttribute("courseList", courses);
	return "studentHome";
}
@PostMapping("/submitNewFileAssign")
public String uploadFiles(
        @RequestParam("files") List<MultipartFile> files,
        @RequestParam("submissionId") Integer submissionId,
        HttpSession session,
        Model model) {
  
        SubmissionFileDTO dto = new SubmissionFileDTO();
        dto.setSubmissionID(submissionId);
        dto.setFiles(files);

        submissionFileService.saveNewFile(dto);
		return "redirect:/Student/submissioinDetail?submissionID="+submissionId;
    }
@PostMapping("/changePassword")
public String changePassword( @RequestParam("currentPassword") String currentPassword,
                             @RequestParam("newPassword") String newPassword,
                             Model model,HttpSession session) {
	UserDTO userDTO = (UserDTO) session.getAttribute("student");
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
@GetMapping("/studentCourseDeatail")
public String getCourseDetails(@RequestParam("courseId") int courseId, Model model,HttpSession session) {
 UserDTO userDTO = (UserDTO) session.getAttribute("student");
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
    return "studentCourseDetail"; 
}
@GetMapping("/materialDetail/{materialId}")
public String showMaterialDetail(@PathVariable("materialId")Integer materialID,HttpSession session,Model model) {
	 UserDTO userDTO = (UserDTO) session.getAttribute("student");
		if(userDTO == null) {
			System.out.println("session is null");
			return "redirect:/";
		}
		MaterialDTO material = materialService.findById(materialID);
		List<MaterialFileDTO> files = materialFileService.getFilesByMaterialId(materialID);
		model.addAttribute("Files", files);
		model.addAttribute("material", material);
		return "studentMaterialDetail";
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

@GetMapping("/assignmentDetail/{assignmentId}")
public String showAssignmentDetail(@PathVariable("assignmentId") Integer assignmentId, HttpSession session, Model model) {
    UserDTO userDTO = (UserDTO) session.getAttribute("student");
    if (userDTO == null) {
        System.out.println("Session is null");
        return "redirect:/";
    }
    System.out.println(assignmentId+"id");
    AssignmentDTO assignment = AssignmentService.findById(assignmentId);
    List<AssignmentFileDTO> files = assignmentFileService.getFilesByAssignmentId(assignmentId);
    List<SubmissionDTO> submission = submissionService.getSubmissionByAssignmentIdAndStudentId(assignmentId,userDTO.getId());
    model.addAttribute("submission", submission);
    model.addAttribute("Files", files);
    model.addAttribute("assignment", assignment);
    return "studentAssignDetail";
}
@PostMapping("/submitAssign")
public String createMaterial(@RequestParam("assignmentId")Integer assignmentId,
						 		HttpSession session,
						 		@RequestParam("files") List<MultipartFile> files) {
	 UserDTO userDTO = (UserDTO) session.getAttribute("student");
		if(userDTO == null) {
			System.out.println("session is null");
			return "redirect:/";
		}
		SubmissionDTO submission = new SubmissionDTO();
		submission.setAssignmentID(assignmentId);
		submission.setCreateStudentID(userDTO.getId());
		submission.setFiles(files);
		submissionService.save(submission);
		return "redirect:/Student/assignmentDetail/" + assignmentId;
}
@GetMapping("/submissioinDetail")
public String showSubmissionDetail(@RequestParam("submissionID") Integer submissionID,HttpSession session,Model model) {
	 UserDTO userDTO = (UserDTO) session.getAttribute("student");
		if(userDTO == null) {
			System.out.println("session is null");
			return "redirect:/";
		} 
		SubmissionDTO submission = submissionService.findById(submissionID);
		List<SubmissionFileDTO> files = submissionFileService.getFilesBySubmissionId(submissionID);
		model.addAttribute("submission", submission);
		model.addAttribute("Files", files);
		return "studentSubmissionDetail";
}
@GetMapping("studentCoursePeople")
public String showPeople(@RequestParam("courseId") int courseId,Model model,HttpSession session) {
	 UserDTO userDTO = (UserDTO) session.getAttribute("student");
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
			return "studentCoursePeople";
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



}
