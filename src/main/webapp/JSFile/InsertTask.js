const todoForm = document.getElementById("todoForm");
todoForm.addEventListener("submit",(e)=>{
    e.preventDefault();

    const task = document.getElementById("taskInput").value;
    const taskList = document.getElementById("todoList");


    fetch('insertTodo', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
            body: JSON.stringify({ task: task })
        })
        .then(response => response.json())
        .then(data => {
            if (data.status == "success") {
               //console.log(task.taskID);
                const taskElement = `
                    <li class="list-group-item d-flex justify-content-between align-items-center border-start-0 border-top-0 border-end-0 border-bottom rounded-0 mb-2">
                        <div class="d-flex align-items-center">
                            <input class="form-check-input me-2 task-checkbox" type="checkbox"
                                data-task-id="${data.taskID}"
                                ${data.taskStatus ? 'checked' : ''}
                                aria-label="..." name />
                            ${task}
                        </div>
                        <img src="images/x-octagon.svg" alt="" width="20" height="20" data-task-id="${data.taskID}" class="remove-task d-inline-block align-text-top">
                    </li>
                `;
                taskList.insertAdjacentHTML('beforeend', taskElement);
                task.value = '';
                let emptylist =document.getElementById('emptylist');
                if(emptylist){
                emptylist.remove();
                }
            } else {

              showAlert("Failed to add task. Please try again.");

            }
        }).catch(error => console.error('Error:', error));
        document.getElementById('taskInput').value = '';
})