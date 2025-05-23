document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.btn-delete').forEach(button => {
        button.addEventListener('click', function () {
            const testId = this.getAttribute('data-id');
            const courseId = this.getAttribute('data-course');

            Swal.fire({
                title: 'Are you sure you want to delete this test?',
                text: "This action cannot be undone!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#d33',
                cancelButtonColor: '#aaa',
                confirmButtonText: 'Delete',
                cancelButtonText: 'Cancel'
            }).then((result) => {
                if (result.isConfirmed) {
                    // Submit the hidden delete form
                    document.getElementById('deleteForm').submit();
                }
            });
        });
    });
});