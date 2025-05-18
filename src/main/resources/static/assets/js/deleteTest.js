document.addEventListener('DOMContentLoaded', function() {
    console.log('DOM loaded - Initializing event listeners');

    // Kiểm tra xem có nút delete nào không
    const deleteButtons = document.querySelectorAll('.delete-test-btn');
    console.log('Found delete buttons:', deleteButtons.length);

    // Sử dụng event delegation để bắt sự kiện từ nút delete
    document.body.addEventListener('click', function(event) {
        // Tìm nút delete gần nhất từ điểm click
        const deleteButton = event.target.closest('.delete-test-btn');

        if (deleteButton) {
            console.log('Delete button clicked');
            event.preventDefault(); // Ngăn chặn hành vi mặc định nếu có

            const testId = deleteButton.getAttribute('data-test-id');
            const courseId = deleteButton.getAttribute('data-course-id');
            const testName = deleteButton.getAttribute('data-test-name');

            console.log(`Xóa bài kiểm tra: ${testName} (ID: ${testId}, Course: ${courseId})`);

            // Hiển thị hộp thoại xác nhận
            Swal.fire({
                title: 'Bạn có chắc chắn?',
                text: `Bạn có thực sự muốn xóa bài kiểm tra "${testName}"? Hành động này không thể hoàn tác.`,
                icon: 'warning',
                showCancelButton: true,
                confirmButtonText: 'Có, xóa nó!',
                cancelButtonText: 'Không, hủy bỏ',
                confirmButtonColor: '#d33',
                cancelButtonColor: '#3085d6',
            }).then((result) => {
                if (result.isConfirmed) {
                    deleteTest(courseId, testId);
                } else {
                    console.log('Đã hủy xóa');
                }
            });
        }
    });

    // Function to send delete request
    function deleteTest(courseId, testId) {
        console.log(`Đang gửi yêu cầu xóa bài kiểm tra ${testId} của khóa học ${courseId}`);

        // Tạo và gửi yêu cầu DELETE
        fetch(`/admin/course/${courseId}/tests/test/${testId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                // Thêm CSRF token nếu cần
                // 'X-CSRF-TOKEN': getCSRFToken()
            }
        })
            .then(response => {
                console.log('Đã nhận phản hồi:', response);
                return response.json();
            })
            .then(data => {
                console.log('Dữ liệu trả về:', data);
                if (data.success) {
                    // Thành công - hiển thị thông báo và loại bỏ thẻ bài kiểm tra
                    Swal.fire({
                        title: 'Đã xóa!',
                        text: data.message,
                        icon: 'success'
                    }).then(() => {
                        // Làm mới trang để hiển thị danh sách đã cập nhật
                        window.location.reload();
                    });
                } else {
                    // Lỗi
                    Swal.fire({
                        title: 'Lỗi!',
                        text: data.message || 'Đã xảy ra lỗi khi xóa bài kiểm tra',
                        icon: 'error'
                    });
                }
            })
            .catch(error => {
                console.error('Lỗi:', error);
                Swal.fire({
                    title: 'Lỗi!',
                    text: 'Đã xảy ra lỗi không mong muốn. Vui lòng thử lại.',
                    icon: 'error'
                });
            });
    }
});