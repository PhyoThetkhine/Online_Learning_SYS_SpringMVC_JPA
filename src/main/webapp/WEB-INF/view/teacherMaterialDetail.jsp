<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Material Details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            display: flex;
        }
        /* Sidebar Styles */
        .sidebar {
            width: 250px;
            background-color: #8b5e3c;
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
        /* Main Content */
        .main-content {
            margin-left: 250px;
            padding: 20px;
            width: 100%;
        }
        .header {
            background-color: #ecf0f1;
            padding: 20px;
            margin-bottom: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .course-container {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
        }
        .course-box {
            background-color: #f8f9fa;
            border: 1px solid #ddd;
            border-radius: 5px;
            width: 300px;
            padding: 15px;
            text-align: center;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .course-box h3 {
            font-size: 1.5em;
            margin-bottom: 10px;
        }
        .course-box p {
            font-size: 0.9em;
            color: #666;
            margin-bottom: 15px;
        }
        .course-box .btn {
            background-color: #007bff;
            color: white;
            text-decoration: none;
            padding: 10px 15px;
            border-radius: 5px;
        }
        .course-box .btn:hover {
            background-color: #0056b3;
        }
        .modal-fullscreen .modal-body {
        padding: 0; /* Remove padding for a truly fullscreen experience */
    }
    #filePreview {
        height: calc(100vh - 56px); /* Adjust height to account for modal header */
    }
    </style>
</head>
<body>
<div class="sidebar">
    <h2><i class="fas fa-user-shield"></i> Teacher Dashboard</h2>
    <ul>
        <li>
            <a href="/Learing_management/TeacherHomeServlet">
                <i class="fas fa-tachometer-alt"></i> Dashboard
            </a>
        </li>
        <li>
            <a href="/Learing_management/ClassWorkServlet?courseId=${material.course.id}">
                <i class="fas fa-chalkboard-teacher"></i>  Class Work
            </a>
        </li>
         <li><a href="/Learing_management/Assignment?courseId=${material.course.id}"><i class="fas fa-book"></i>Assignment</a></li>
         <li>
			<a href="/Learing_management/Logout">
				<i class="fas fa-sign-out-alt"></i> Logout
			</a>
		</li>
    </ul>
</div>

    <div class="main-content">
        <div class="header">
            <h1><i class="fas fa-file-alt"></i> Material Details</h1>
        </div>
         <!-- Material Info -->
        <div class="card">
            <div class="card-body">
                <h3><i class="fas fa-file-alt"></i> Material Title: ${material.title}</h3>
                <p><strong>Description:</strong> ${material.description}</p>
                <p><strong>Teacher:</strong> ${material.createTeacher.name}</p>
            </div>
        </div>
        
         <section id="courses">
         <br>
            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#courseModal">Add New File</button>
           <br><br>
           <div class="course-container">
            <!-- Iterate over the course list -->
            <c:forEach var="file" items="${Files}">
				    <div class="course-box">
				        <img
				            src="https://res.cloudinary.com/dwerfxy6q/image/upload/v1733122153/xucigsgfrqcrcxrcybgr.png"
				            class="img-fluid rounded"
				            style="max-height: 200px; width: 100%; object-fit: cover;">
				        <a href="downloadMaterialFile?fileId=${file.id}" class="btn btn-primary">
				            <i class="fas fa-download"></i> Download
				        </a>
				        <button class="btn btn-secondary" onclick="viewFile('${file.fileUrl}')">
				            <i class="fas fa-eye"></i> View File
				        </button>
				    </div>
				</c:forEach>

        </div>
            
        </section>
           <div class="modal fade" id="courseModal" tabindex="-1" aria-labelledby="courseModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="courseModalLabel">Add New File</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="AddingNewMaterailFile" method="POST" enctype="multipart/form-data">
                    
                      <div class="mb-3">
                        <label for="file" class="form-label">Upload File</label>
                        <input type="file" class="form-control" id="file" name="files"  required>
                    </div>
                    <input type="hidden" name="materialId" value="${material.id}">
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Upload</button>
                    </div>
                </form>
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
        
       

    </div>
    
    <script>
    function viewFile(fileUrl) {
        const modal = new bootstrap.Modal(document.getElementById('fileModal'));
        const filePreview = document.getElementById('filePreview');

        // Check file type and generate URL for preview
        if (fileUrl.endsWith(".pdf")) {
            filePreview.src = fileUrl; // PDF files can be shown directly
        } else if (fileUrl.endsWith(".pptx")) {
        	let fileurl = "https://docs.google.com/gview?url=" + fileUrl + "&embedded=true";
        	filePreview.src = fileurl;
        	console.log("Preview URL:", fileurl); // Debugging: Check constructed URL
        	
        } else {
            filePreview.src = "about:blank"; // Empty for unsupported formats
        }

        modal.show();
    }
</script>
    

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
