// Dropzone
Dropzone.autoDiscover = false;
var courseId = document.getElementById("courseSelect").value;

var dropzone = new Dropzone("#dropzoneArea", {
    url: "/admin/course/" + courseId + "/lesson/create",
    autoProcessQueue: false,
    uploadMultiple: true,
    parallelUploads: 2,
    paramName: "lessonAttachments",
    addRemoveLinks: true,
    maxFilesize: 5, // MB
});

// Gửi các field form cùng với file
dropzone.on("sendingmultiple", function(data, xhr, formData) {
    const formElements = document.querySelectorAll("#lessonForm [name]");
    formElements.forEach(input => {
        formData.append(input.name, input.value);
    });
});

// Submit form bằng Dropzone
document.getElementById("submitBtn").addEventListener("click", function(e) {
    e.preventDefault();
    if (dropzone.getQueuedFiles().length > 0) {
        dropzone.processQueue();
    } else {
        document.getElementById("lessonForm").submit();
    }
});