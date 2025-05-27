document.addEventListener('DOMContentLoaded', function() {
    // Lấy các phần tử DOM
    const addQuestionBtn = document.getElementById('addQuestionBtn');
    const questionModal = document.getElementById('questionModal');
    const questionTypeSelect = document.getElementById('questionType');
    const multipleChoiceSection = document.getElementById('multipleChoiceSection');
    const writingSection = document.getElementById('writingSection');
    const choicesContainer = document.getElementById('choicesContainer');
    const addChoiceBtn = document.getElementById('addChoice');
    const saveQuestionBtn = document.getElementById('saveQuestion');
    const questionForm = document.getElementById('questionForm');
    const exerciseId = document.getElementById('exerciseId').value;

    // Xử lý khi thay đổi loại câu hỏi
    questionTypeSelect.addEventListener('change', function() {
        const selectedType = this.value;

        // Ẩn tất cả các section
        multipleChoiceSection.style.display = 'none';
        writingSection.style.display = 'none';

        // Hiển thị section tương ứng
        if (selectedType === '1') { // Giả sử 1 là ID cho Multiple Choice
            multipleChoiceSection.style.display = 'block';
        } else if (selectedType === '2') { // Giả sử 2 là ID cho Writing
            writingSection.style.display = 'block';
        }
    });

    // Thêm lựa chọn mới cho Multiple Choice
    addChoiceBtn.addEventListener('click', function() {
        const choiceItem = document.createElement('div');
        choiceItem.className = 'choice-item mb-2';
        choiceItem.innerHTML = `
            <div class="input-group">
                <div class="input-group-text">
                    <input class="form-check-input correct-answer" type="radio" name="correctAnswer">
                </div>
                <input type="text" class="form-control choice-input" placeholder="Option text">
                <button class="btn btn-outline-danger remove-choice" type="button">
                    <i class="ri-delete-bin-line"></i>
                </button>
            </div>
        `;
        choicesContainer.appendChild(choiceItem);

        // Thêm sự kiện xóa cho nút remove
        choiceItem.querySelector('.remove-choice').addEventListener('click', function() {
            choicesContainer.removeChild(choiceItem);
        });
    });

    // Xử lý khi lưu câu hỏi
    saveQuestionBtn.addEventListener('click', function() {
        const questionType = questionTypeSelect.value;
        const questionContent = document.getElementById('questionContent').value;

        if (!questionType || !questionContent) {
            Swal.fire('Error', 'Please fill all required fields', 'error');
            return;
        }

        // Tạo đối tượng dữ liệu câu hỏi
        const questionData = {
            exerciseId: exerciseId,
            content: questionContent,
            questionTypeId: questionType
        };

        // Xử lý dữ liệu tùy theo loại câu hỏi
        if (questionType === '1') { // Multiple Choice
            const choices = Array.from(document.querySelectorAll('.choice-input')).map(input => input.value);
            const correctAnswerIndex = Array.from(document.querySelectorAll('.correct-answer')).findIndex(radio => radio.checked);

            if (choices.length < 2 || correctAnswerIndex === -1) {
                Swal.fire('Error', 'Please add at least 2 options and select a correct answer', 'error');
                return;
            }

            questionData.choices = choices;
            questionData.correctAnswerIndex = correctAnswerIndex;
        } else if (questionType === '2') { // Writing
            const sampleAnswer = document.getElementById('writingAnswer').value;
            questionData.sampleAnswer = sampleAnswer;
        }

        // Gửi dữ liệu đến server bằng AJAX
        fetch(`/course/${exerciseId}/questions/add`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-Requested-With': 'XMLHttpRequest'
            },
            body: JSON.stringify(questionData)
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    Swal.fire('Success', 'Question added successfully', 'success')
                        .then(() => {
                            // Đóng modal và làm mới trang
                            const modal = bootstrap.Modal.getInstance(questionModal);
                            modal.hide();
                            location.reload();
                        });
                } else {
                    Swal.fire('Error', data.message || 'Failed to add question', 'error');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                Swal.fire('Error', 'An error occurred while adding the question', 'error');
            });
    });

    // Reset form khi modal đóng
    questionModal.addEventListener('hidden.bs.modal', function() {
        questionForm.reset();
        choicesContainer.innerHTML = ''; // Xóa tất cả các lựa chọn
        multipleChoiceSection.style.display = 'none';
        writingSection.style.display = 'none';
    });
});