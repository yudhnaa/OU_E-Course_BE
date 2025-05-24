if (document.querySelector("#profile-img-file-input")) {
    document.querySelector("#profile-img-file-input").addEventListener("change", function () {
        var preview = document.querySelector(".user-profile-image");
        var fileInput = document.querySelector(".profile-img-file-input");
        var file = fileInput.files[0];
        var reader = new FileReader();

        reader.addEventListener("load", function () {
            preview.src = reader.result;
        });

        if (file) {
            reader.readAsDataURL(file);

            // Tạo DataTransfer mới để gán file vào input ẩn
            var dataTransfer = new DataTransfer();
            dataTransfer.items.add(file);
            document.querySelector("#imagePicker").files = dataTransfer.files;
        }
    });
}
