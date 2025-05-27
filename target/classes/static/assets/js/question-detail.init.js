function addAnswer() {
    const container = document.getElementById('answers-container');
    const index = container.children.length;
    const div = document.createElement('div');
    div.classList.add('answer-item', 'border', 'p-2', 'mb-2', 'rounded');

    div.innerHTML = `
            <label class="form-label">Option ${index + 1}</label>
            <input type="text" class="form-control mb-2" name="options" required/>
            <input type="hidden" name="answerIds" value="0"/> <!-- New answer -->
            <div class="form-check mb-2">
                <input class="form-check-input" type="radio" name="correctOption" value="${index}"/>
                <label class="form-check-label">Mark as Correct</label>
            </div>
            <button type="button" class="btn btn-danger btn-sm remove-answer-btn">Remove</button>
        `;

    container.appendChild(div);
    updateRadioValues();
}

// Xử lý xóa câu trả lời
document.addEventListener('click', function (e) {
    if (e.target && e.target.classList.contains('remove-answer-btn')) {
        e.target.closest('.answer-item').remove();
        updateRadioValues();
    }
});

// Cập nhật lại giá trị value của radio sau khi thêm/xóa
function updateRadioValues() {
    const answerItems = document.querySelectorAll('.answer-item');
    answerItems.forEach((item, idx) => {
        item.querySelector('label.form-label').innerText = `Option ${idx + 1}`;
        item.querySelector('input[type="radio"]').value = idx;
    });
}

document.addEventListener('click', function (e) {
    if (e.target && e.target.classList.contains('remove-answer-btn')) {
        const answerItem = e.target.closest('.answer-item');
        const answerIdInput = answerItem.querySelector('input[name="answerIds"]');
        const answerId = answerIdInput ? answerIdInput.value : null;

        if (answerId && answerId !== "0") { // 0 là đáp án mới chưa có trong DB
            const deletedIdsInput = document.getElementById('deletedAnswerIds');
            const currentValue = deletedIdsInput.value;
            deletedIdsInput.value = currentValue
                ? currentValue + "," + answerId
                : answerId;
        }

        answerItem.remove();
        updateRadioValues();
    }
});