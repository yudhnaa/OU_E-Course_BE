document.addEventListener('DOMContentLoaded', function() {
    // Delete question confirmation
    document.querySelectorAll('.delete-question').forEach(button => {
        button.addEventListener('click', function(e) {
            e.preventDefault();
            const questionId = this.getAttribute('data-id');
            const deleteUrl = this.getAttribute('data-url');

            Swal.fire({
                title: 'Are you sure?',
                text: "You won't be able to revert this!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, delete it!'
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location.href = deleteUrl;
                }
            });
        });
    });
});