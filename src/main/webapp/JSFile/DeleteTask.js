document.addEventListener("DOMContentLoaded", function () {

    document.getElementById('todoList').addEventListener('click', function (event) {
        if (event.target.closest('.remove-task')) {
            const taskElement = event.target.closest('.remove-task');
            const taskId = taskElement.getAttribute('data-task-id');

            if (!taskId) {
                showAlert("Task ID not found for the clicked element.");
                return;
            }


            const confirmDelete = confirm("Are you sure you want to remove this task?");
            if (!confirmDelete) {
                return;
            }


            fetch('deleteTask', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ taskID: taskId })
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                if (data.success) {
                    const listItem = taskElement.closest('li');
                    if (listItem) {
                        listItem.remove();
                    }
                    showAlert("Task removed successfully.");

                } else {
                    showAlert("Failed to remove task");
                    console.error(`Failed to remove task ${taskId}:`, data.message);
                }
            })
            .catch(error => {
                showAlert("Error removing task");
                console.error('Error removing task:', error);
            });
        }
    });
});
