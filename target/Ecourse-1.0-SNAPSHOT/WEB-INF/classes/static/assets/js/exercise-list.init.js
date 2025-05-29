$(document).ready(function() {
    console.log('Document ready');

    // Xử lý click event cho nút delete
    $('.delete-exercise-btn').on('click', function(e) {
        e.preventDefault();
        console.log('Delete button clicked');

        const exerciseId = $(this).data('exercise-id');
        const exerciseName = $(this).data('exercise-name');
        const deleteForm = $('#deleteForm-' + exerciseId);

        console.log('Exercise ID:', exerciseId, 'Exercise Name:', exerciseName);
        console.log('Form found:', deleteForm.length > 0);

        // Hiển thị SweetAlert để xác nhận
        Swal.fire({
            title: 'Are you sure ?',
            html: `Are you sure to delete this quiz : "<strong>${exerciseName}</strong>"?<br>You won't be able to revert this!`,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            cancelButtonColor: '#6c757d',
            confirmButtonText: 'Yes, delete it!',
            cancelButtonText: 'Cancel',
            reverseButtons: true
        }).then((result) => {
            if (result.isConfirmed) {
                console.log('User confirmed delete, submitting form');
                deleteForm.submit();
            }
        });
    });
});

// Xử lý sự kiện nhập vào ô tìm kiếm
document.addEventListener("DOMContentLoaded", () => {
    let debounceTimeout;
    const searchBox = document.getElementById("search-box");

    searchBox.addEventListener("input", (event) => {
        clearTimeout(debounceTimeout);
        debounceTimeout = setTimeout(() => {
            const query = event.target.value.toLowerCase();
            const url = new URL(window.location.href);

            if(query) {
                url.searchParams.set("name", query);
            } else {
                url.searchParams.delete("name");
            }
            url.searchParams.set("page", "1");
            window.location.href = url.toString();
        }, 1000);
    });

})