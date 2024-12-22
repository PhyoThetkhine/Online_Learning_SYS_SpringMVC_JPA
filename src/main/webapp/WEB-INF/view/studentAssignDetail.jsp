<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Assignment Details</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
	rel="stylesheet">
<style>
body {
	margin: 0;
	font-family: Arial, sans-serif;
	display: flex;
}

.sidebar {
            width: 250px;
            background-color: #42627F;
            color: white;
            height: 100vh;
            padding: 20px 0;
            position: fixed;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .sidebar h2 {
            text-align: center;
            margin-bottom: 20px;
            font-size: 1.5em;
        }
        .sidebar ul {
            list-style-type: none;
            padding: 0;
            width: 100%;
        }
        .sidebar ul li {
            padding: 15px 20px;
            margin: 5px 0;
            cursor: pointer;
            display: flex;
            align-items: center;
        }
        .sidebar ul li:hover {
            background-color: #34495e;
        }
        .sidebar ul li a {
            text-decoration: none;
            color: white;
            display: flex;
            align-items: center;
            width: 100%;
        }
        .sidebar ul li a i {
            margin-right: 10px;
            font-size: 1.2em;
        }

  /* Navbar Styles */
        .navbar {
            position: fixed;
             background-color: #ecf0f1;
            top: 0;
            left: 250px; /* Matches sidebar width */
            width: calc(100% - 250px);
            z-index: 1000;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        /* Main Content */
        .main-content {
            margin-left: 250px; /* Matches sidebar width */
            margin-top: 70px; /* Space for navbar */
            padding: 20px;
            width: calc(100% - 250px);
        }
        .header {
            background-color: #ecf0f1;
            padding: 20px;
            margin-bottom: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
        }
.modal-fullscreen .modal-body {
        padding: 0; /* Remove padding for a truly fullscreen experience */
    }
    #filePreview {
        height: calc(100vh - 56px); /* Adjust height to account for modal header */
    }
     .material-container {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }
      .material-box {
    background-color: #fff;
    border: 1px solid #ddd;
    border-radius: 5px;
    padding: 15px;
    display: flex;
    justify-content: space-between; /* Ensures content and actions are spaced apart */
    align-items: center;
    gap: 20px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    transition: transform 0.3s;
    max-height: 180px;
    overflow: hidden;
}

.material-content {
    flex: 1; /* Take up available space */
}

.material-actions {
    display: flex;
    gap: 10px; /* Spacing between buttons */
}

    .material-box i {
    font-size: 2rem; /* Adjusted icon size */
    margin-right: 15px;
}

.material-content {
    flex: 1;
}

.material-title {
    font-size: 1.2em;
    font-weight: bold;
    margin: 0;
}

.material-meta {
    font-size: 0.9em;
    color: #666;
}

.material-actions .btn .fas {
    font-size: 1.2em; /* Increase the icon size */
    vertical-align: middle; /* Align icon properly */
    margin-right: 0.3em; /* Reduce space between the icon and the text */
}
.no-files {
    text-align: center;
    color: #888;
    font-size: 16px;
    margin: 20px 0;
}
.button-container {
    display: flex;           /* Use flexbox to align the buttons */
    justify-content: flex-start; /* Align buttons to the left */
    gap: 10px;               /* Space between buttons */
}

/* Button styling */
.upload-btn {
    width: auto;             /* Let the button width adjust to the content */
    max-width: 200px;        /* Limit the max width */
    padding: 8px 16px;       /* Optional: Adjust padding for better appearance */
    font-size: 14px;         /* Optional: Adjust font size */
}
</style>
</head>
<body>
	<div class="sidebar">
    <h2><i class="fas fa-user-graduate"></i> Student Dashboard</h2>
    <ul>
        <li>
            <a href="/LearingSYSLMS/Student/home">
                <i class="fas fa-tachometer-alt"></i> My Course
            </a>
        </li>
    <li>
			<a href="/LearingSYSLMS/logout">
				<i class="fas fa-sign-out-alt"></i> Logout
			</a>
		</li>
    </ul>
</div>
<nav class="navbar navbar-expand-lg">
            <div class="container-fluid">
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="/LearingSYSLMS/Student/studentCourseDeatail?courseId=${assignment.course.id}">
                                <i class="fas fa-stream"></i> Stream
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/LearingSYSLMS/Student/studentCoursePeople?courseId=${assignment.course.id}">
                                <i class="fas fa-users"></i> People
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>


	<!-- Main Content -->
	<div class="main-content">
		   <div class="card">
            <div class="card-body">
                <h3><i class="fas fa-file-alt"></i> Assignment Title: ${assignment.title}</h3>
                <p><strong>Description:</strong> ${assignment.description}</p>
                <p><strong>Teacher:</strong> ${assignment.createTeacher.name}</p>
            </div>
        </div>
        <br>
         <br>
             <h4><i class="fas fa-file-alt"></i> Assignment Files</h4>
        <section id="materials">
            <div class="container">
                <div class="material-container">
                 <c:if test="${empty Files}">
                <div class="no-files">
                    <p>No files available.</p>
                </div>
            </c:if>
                   <c:forEach var="file" items="${Files}">
					    <div class="material-box">
					        <div class="material-content">
					            <i class="fas fa-file"></i>
					            <span class="material-title">
            <script>
                // Extract the filename from the file URL
                document.write('${file.fileUrl}'.split('/').pop());
            </script>
        </span>
					        </div>
					        <div class="material-actions">
					            <button class="btn btn-secondary" onclick="viewFile('${file.fileUrl}')">
					                <i class="fas fa-eye"></i> View File
					            </button>
					            <a href="/LearingSYSLMS/Teacher/downloadAssignmentFile?fileId=${file.id}" class="btn btn-primary">
					                <i class="fas fa-download"></i> Download
					            </a>
					        </div>
					    </div>
					</c:forEach>
                </div>
            </div>
        </section>
        <br><br>
   <h4><i class="fas fa-tasks"></i> Your Submission</h4>
          <section id="materials">
            <div class="container">
                <div class="material-container">
                <div class="button-container">
     <c:if test="${empty submission}">
    <button type="button" class="btn btn-primary btn-sm upload-btn" data-bs-toggle="modal" data-bs-target="#uploadModal">
        <i class="fas fa-file-upload"></i> Upload Assignment
    </button>
    </c:if>

</div>
                    <c:forEach var="submission" items="${submission}">
                        <div class="material-box">
                            <i class="fas fa-book-open"></i>
                            <div class="material-content">
                            <h5>Your Submission</h5>
                            </div>
                            <div class="material-actions">
                             <a href="/LearingSYSLMS/Student/submissioinDetail?submissionID=${submission.id}" class="btn btn-secondary">
					                 <i class="fas fa-eye"></i> View Detail
					            </a>
							</div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section> 

		
		<!-- Modal for Upload Form -->
<div class="modal fade" id="uploadModal" tabindex="-1" aria-labelledby="uploadModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="uploadModalLabel">Upload Files</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <!-- Upload Form -->
                <form action="/LearingSYSLMS/Student/submitAssign" method="post" enctype="multipart/form-data">
                    <div class="mb-3">
                        <label for="fileUpload" class="form-label"><i class="fas fa-file-upload"></i> Select Files</label>
                        <input type="file" class="form-control" id="fileUpload" name="files" multiple required>
                    </div>
                    <input type="hidden" name="assignmentId" value="${assignment.id}">
                    <button type="submit" class="btn btn-primary">
                        <i class="fas fa-paper-plane"></i> Submit
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Modal for Upload Form -->
<div class="modal fade" id="NewUploadModal" tabindex="-1" aria-labelledby="NewUploadModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="uploadModalLabel">Upload Files</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <!-- Upload Form -->
                <form action="submitNewFileAssign" method="post" enctype="multipart/form-data">
                    <div class="mb-3">
                        <label for="fileUpload" class="form-label"><i class="fas fa-file-upload"></i> Select Files</label>
                        <input type="file" class="form-control" id="fileUpload" name="files" multiple required>
                    </div>
                    <input type="hidden" name="assignmentId" value="${assingments.id}">
                    <c:forEach var="file" items="${file}">
                    <input type="hidden" name="submissionId" value="${file.submission.id}">
                    </c:forEach>
                    <button type="submit" class="btn btn-primary">
                        <i class="fas fa-paper-plane"></i> Submit
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>

	</div>
	 <div class="modal fade" id="fileModal" tabindex="-1" aria-labelledby="fileModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-fullscreen">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="fileModalLabel">File Preview</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <iframe id="filePreview" src="" style="width: 100%; height: 90vh;" frameborder="0"></iframe>
            </div>
        </div>
    </div>
</div>
	
	<script>
    function viewFile(fileUrl) {
        const filePreview = document.getElementById('filePreview');

        // Check file type and generate URL for preview
        if (fileUrl.endsWith(".pdf")) {
            filePreview.src = fileUrl; // PDF files can be shown directly
            const modal = new bootstrap.Modal(document.getElementById('fileModal'));
            modal.show(); 
        } else if (fileUrl.endsWith(".pptx")) {
            let fileurl = "https://docs.google.com/gview?url=" + fileUrl + "&embedded=true";
            filePreview.src = fileurl;
            console.log("Preview URL:", fileurl); 
            const modal = new bootstrap.Modal(document.getElementById('fileModal'));
            modal.show(); // Debugging: Check constructed URL
        } else if (fileUrl.endsWith(".jpg") || fileUrl.endsWith(".jpeg") || fileUrl.endsWith(".png")) {
            filePreview.src = fileUrl;
            const modal = new bootstrap.Modal(document.getElementById('fileModal'));
            modal.show(); // Image files can be shown directly
        } else {
            alert("Preview not available for this file type.");
        }

        modal.show();
    }

</script>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
