// Initialize modal manually to avoid Bootstrap conflicts
let addQuestionModal = null;

document.addEventListener('DOMContentLoaded', function() {

    // Initialize modal - Wait for Bootstrap to load
    setTimeout(() => {
        const modalElement = document.getElementById('addQuestionModal');
        if (modalElement) {
            if (typeof bootstrap !== 'undefined' && bootstrap.Modal) {
                addQuestionModal = new bootstrap.Modal(modalElement);
            } else {
                console.warn('Bootstrap not loaded, using jQuery fallback');
                if (typeof $ !== 'undefined') {
                    addQuestionModal = $(modalElement);
                }
            }
        }
    }, 100);

    // Handle modal show button click
    const addQuestionBtn = document.querySelector('[data-bs-toggle="modal"][data-bs-target="#addQuestionModal"]');
    if (addQuestionBtn) {
        addQuestionBtn.addEventListener('click', function (e) {
            e.preventDefault();
            e.stopPropagation();

            const modalElement = document.getElementById('addQuestionModal');

            if (typeof bootstrap !== 'undefined' && bootstrap.Modal) {
                if (!addQuestionModal) {
                    addQuestionModal = new bootstrap.Modal(modalElement);
                }
                addQuestionModal.show();
            } else if (typeof $ !== 'undefined' && $.fn.modal) {
                $(modalElement).modal('show');
            } else {
                showModalManually(modalElement);
            }
        });
    }

    // Manual modal functions (keeping same as before)
    function showModalManually(modalElement) {
        const backdrop = document.createElement('div');
        backdrop.className = 'modal-backdrop fade show';
        backdrop.id = 'manual-backdrop';
        document.body.appendChild(backdrop);

        modalElement.style.display = 'block';
        modalElement.classList.add('show');
        document.body.classList.add('modal-open');

        backdrop.addEventListener('click', function () {
            closeModalManually(modalElement);
        });
    }

    function closeModalManually(modalElement) {
        modalElement.style.display = 'none';
        modalElement.classList.remove('show');
        document.body.classList.remove('modal-open');

        const backdrop = document.getElementById('manual-backdrop');
        if (backdrop) {
            backdrop.remove();
        }
    }

    // Handle modal close buttons
    const modalElement = document.getElementById('addQuestionModal');
    if (modalElement) {
        modalElement.querySelectorAll('[data-bs-dismiss="modal"]').forEach(btn => {
            btn.addEventListener('click', function (e) {
                e.preventDefault();

                if (addQuestionModal && typeof addQuestionModal.hide === 'function') {
                    addQuestionModal.hide();
                } else if (typeof $ !== 'undefined' && $.fn.modal) {
                    $(modalElement).modal('hide');
                } else {
                    closeModalManually(modalElement);
                }
            });
        });

        document.addEventListener('keydown', function (e) {
            if (e.key === 'Escape' && modalElement.classList.contains('show')) {
                if (addQuestionModal && typeof addQuestionModal.hide === 'function') {
                    addQuestionModal.hide();
                } else if (typeof $ !== 'undefined' && $.fn.modal) {
                    $(modalElement).modal('hide');
                } else {
                    closeModalManually(modalElement);
                }
            }
        });
    }

    // Delete question confirmation
    document.querySelectorAll('.delete-question').forEach(button => {
        button.addEventListener('click', function (e) {
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
                    // ✅ Sử dụng fetch để gửi POST request thay vì GET
                    fetch(deleteUrl, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                        }
                    }).then(response => {
                        if (response.ok) {
                            return response.json();
                        } else {
                            throw new Error('Failed to delete question');
                        }
                    }).then(data => {
                        if (data.success) {
                            Swal.fire({
                                title: 'Deleted!',
                                text: 'Question has been removed from test.',
                                icon: 'success'
                            }).then(() => {
                                // Reload page hoặc remove row khỏi table
                                window.location.reload();
                            });
                        } else {
                            throw new Error(data.message || 'Unknown error occurred');
                        }
                    }).catch(error => {
                        console.error('Error:', error);
                        Swal.fire('Error', 'Failed to delete question: ' + error.message, 'error');
                    });
                }
            });
        });
    });

    // Delete test confirmation
    const deleteTestBtn = document.getElementById('delete-test-btn');
    if (deleteTestBtn) {
        deleteTestBtn.addEventListener('click', function () {
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
                    // Sử dụng fetch để gửi POST request
                    fetch(deleteUrl, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                        }
                    }).then(response => {
                        if (response.ok) {
                            window.location.reload(); // Reload trang sau khi xóa thành công
                        } else {
                            Swal.fire('Error', 'Failed to delete question', 'error');
                        }
                    }).catch(error => {
                        Swal.fire('Error', 'An error occurred: ' + error.message, 'error');
                    });
                }
            });
        });
    }

    // Select all functionality
    const selectAllCheckbox = document.getElementById('selectAll');
    const questionCheckboxes = document.querySelectorAll('.question-check');
    const selectedCountSpan = document.getElementById('selectedCount');
    const addSelectedBtn = document.getElementById('addSelectedQuestions');

    function updateSelectedCount() {
        const selectedCount = document.querySelectorAll('.question-check:checked:not(:disabled)').length;
        if (selectedCountSpan) selectedCountSpan.textContent = selectedCount;
        if (addSelectedBtn) addSelectedBtn.disabled = selectedCount === 0;
    }

    if (selectAllCheckbox) {
        selectAllCheckbox.addEventListener('change', function () {
            questionCheckboxes.forEach(checkbox => {
                if (!checkbox.disabled) {
                    checkbox.checked = this.checked;
                }
            });
            updateSelectedCount();
        });
    }

    questionCheckboxes.forEach(checkbox => {
        checkbox.addEventListener('change', updateSelectedCount);
    });

    // Search and filter functionality
    const questionSearch = document.getElementById('questionSearch');
    const exerciseFilter = document.getElementById('exerciseFilter');
    const questionList = document.getElementById('questionList');

    if (questionSearch && exerciseFilter && questionList) {
        const rows = questionList.getElementsByTagName('tr');

        questionSearch.addEventListener('input', function () {
            filterQuestions();
        });

        exerciseFilter.addEventListener('change', function () {
            filterQuestions();
        });

        function filterQuestions() {
            const searchText = questionSearch.value.toLowerCase();
            const exerciseId = exerciseFilter.value;
            let visibleCount = 0;

            for (let row of rows) {
                if (row.cells.length < 2) continue;

                const questionText = row.cells[1].textContent.toLowerCase();
                const exerciseText = row.cells[3].textContent;
                const matchSearch = questionText.includes(searchText);
                const matchExercise = exerciseId === '' || exerciseText.includes(exerciseId);
                const shouldShow = matchSearch && matchExercise;

                row.style.display = shouldShow ? '' : 'none';
                if (shouldShow) visibleCount++;
            }

            const visibleCheckboxes = Array.from(questionCheckboxes).filter(cb =>
                !cb.disabled && cb.closest('tr').style.display !== 'none'
            );
            const checkedVisible = visibleCheckboxes.filter(cb => cb.checked).length;

            if (selectAllCheckbox) {
                selectAllCheckbox.checked = visibleCheckboxes.length > 0 && checkedVisible === visibleCheckboxes.length;
                selectAllCheckbox.indeterminate = checkedVisible > 0 && checkedVisible < visibleCheckboxes.length;
            }
        }
    }

    // Handle add selected questions - FIXED THIS PART
    if (addSelectedBtn) {
        addSelectedBtn.addEventListener('click', function () {
            const selectedQuestions = Array.from(document.querySelectorAll('.question-check:checked:not(:disabled)'))
                .map(checkbox => checkbox.value);

            if (selectedQuestions.length === 0) {
                Swal.fire('Warning', 'Please select at least one question', 'warning');
                return;
            }

            const courseId = document.getElementById('courseId').value;
            const testId = document.getElementById('testId').value;

            if (!testId || !courseId) {
                Swal.fire('Error', 'Missing test or course information', 'error');
                return;
            }

            // Show loading
            Swal.fire({
                title: 'Adding questions...',
                html: 'Please wait while we add the selected questions.',
                allowOutsideClick: false,
                allowEscapeKey: false,
                showConfirmButton: false,
                didOpen: () => {
                    Swal.showLoading();
                }
            });

            fetch(`/Ecourse/course/${courseId}/tests/test/${testId}/questions/add-multiple`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({questionIds: selectedQuestions})
            })
                .then(response => {
                    console.log('Response status:', response.status);

                    if (!response.ok) {
                        throw new Error(`Server error: ${response.status}`);
                    }

                    return response.json();
                })
                .then(data => {
                    console.log('Response data:', data);

                    if (data.success) {
                        Swal.fire({
                            title: 'Success!',
                            text: `${selectedQuestions.length} question(s) added successfully`,
                            icon: 'success',
                            confirmButtonText: 'OK'
                        }).then(() => {
                            // Close modal
                            const modalElement = document.getElementById('addQuestionModal');
                            if (addQuestionModal && typeof addQuestionModal.hide === 'function') {
                                addQuestionModal.hide();
                            } else if (typeof $ !== 'undefined' && $.fn.modal) {
                                $(modalElement).modal('hide');
                            } else {
                                closeModalManually(modalElement);
                            }
                            window.location.reload();
                        });
                    } else {
                        throw new Error(data.message || 'Unknown error occurred');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    console.log('Course ID:', courseId, 'Test ID:', testId);
                    Swal.fire({
                        title: 'Error',
                        text: 'Failed to add questions. Please try again. ' + error.message,
                        icon: 'error',
                        confirmButtonText: 'OK'
                    });
                });
        });
    }

    // Initialize counts
    updateSelectedCount();
});