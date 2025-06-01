// document.addEventListener('DOMContentLoaded', function() {
//     // Delete question confirmation
//     document.querySelectorAll('.delete-question').forEach(button => {
//         button.addEventListener('click', function(e) {
//             e.preventDefault();
//             const questionId = this.getAttribute('data-id');
//             const deleteUrl = this.getAttribute('data-url');
//
//             Swal.fire({
//                 title: 'Are you sure?',
//                 text: "You won't be able to revert this!",
//                 icon: 'warning',
//                 showCancelButton: true,
//                 confirmButtonColor: '#3085d6',
//                 cancelButtonColor: '#d33',
//                 confirmButtonText: 'Yes, delete it!'
//             }).then((result) => {
//                 if (result.isConfirmed) {
//                     window.location.href = deleteUrl;
//                 }
//             });
//         });
//     });
// });


document.addEventListener('DOMContentLoaded', function() {
    // Delete question confirmation with POST request
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
                    // Tạo form động để gửi POST request
                    const form = document.createElement('form');
                    form.method = 'POST';
                    form.action = deleteUrl;

                    // Thêm CSRF token nếu Spring Security được sử dụng
                    const csrfToken = document.querySelector('meta[name="_csrf"]');
                    const csrfHeader = document.querySelector('meta[name="_csrf_header"]');

                    if (csrfToken && csrfHeader) {
                        const csrfInput = document.createElement('input');
                        csrfInput.type = 'hidden';
                        csrfInput.name = '_csrf';
                        csrfInput.value = csrfToken.getAttribute('content');
                        form.appendChild(csrfInput);
                    }

                    // Thêm form vào body và submit
                    document.body.appendChild(form);
                    form.submit();
                }
            });
        });
    });

    // Delete exercise confirmation (nếu cần)
    const deleteExerciseBtn = document.getElementById('delete-exercise-btn');
    if (deleteExerciseBtn) {
        deleteExerciseBtn.addEventListener('click', function(e) {
            e.preventDefault();
            const deleteUrl = this.getAttribute('data-url');

            Swal.fire({
                title: 'Are you sure?',
                text: "You won't be able to revert this exercise!",
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
    }
});