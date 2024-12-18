document.addEventListener("DOMContentLoaded", function () {

    document.getElementById('todoList').addEventListener('change', function (event) {
        if (event.target.classList.contains('task-checkbox')) {
            const taskId = event.target.getAttribute('data-task-id');
            const status = event.target.checked;
            const requestData = {
                taskID: taskId,
                taskStatus: status
            };


            fetch("updateTask", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(requestData)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Network response was not ok ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
            console.log(data);
                if (data.success) {
                    showAlert("Task status updated successfully.");
                } else {
                    showAlert("Failed to update task.");
                }
            })
            .catch(error => {
                showAlert("Error updating task status.");
                console.error('Error updating task status:', error.message);
            });
        }
    });
});
