document.addEventListener("DOMContentLoaded", function () {
    fetch('FetchTask', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        const todoList = document.getElementById('todoList');
        let tasksHTML = '';
        if(data.status == "todoIsEmpty"){
                tasksHTML = "<li class='list-group-item d-flex align-items-center border-start-0 border-top-0 border-end-0 border-bottom rounded-0 mb-2' id='emptylist'><p style='font-size: 1rem; '>Your List Is Empty </p></li>";
                todoList.innerHTML = tasksHTML;
        }else{
            data.forEach(todoString => {
            const todo = JSON.parse(todoString);
                tasksHTML += `
                    <li class="list-group-item d-flex justify-content-between align-items-center border-start-0 border-top-0 border-end-0 border-bottom rounded-0 mb-2">
                        <div class="d-flex align-items-center">
                            <input class="form-check-input me-2 task-checkbox" type="checkbox"
                            data-task-id="${todo.taskID}"
                            ${todo.taskStatus ? 'checked' : ''}
                            aria-label="..." />
                            ${todo.description}
                        </div>
                                <img src="images/x-octagon.svg" alt="" width="20" height="20"
                                class="d-inline-block align-text-top remove-task" data-task-id="${todo.taskID}"
                                style="color: #172F5F; border: #172F5F;">

                    </li>
                `;
            });
        }
        todoList.innerHTML = tasksHTML;
    })
    .catch(error => {
        console.error('Error fetching todos:', error);
    });
});
