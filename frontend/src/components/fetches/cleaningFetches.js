export const AssignTask = (id, assignedTo) => {
    const userWithTaskId= {id, assignedTo};
    const token = localStorage.getItem("token");
    return fetch (`http://localhost:8080/cleaning/assign`, {
        method: "POST",
        headers: {"Content-Type": "application/json", Authorization: `Bearer ${token}`},
        body : JSON.stringify(userWithTaskId),
    });
}

export const MarkAsDone = (id) => {
    const token = localStorage.getItem("token");
    return fetch (`http://localhost:8080/cleaning/markAsDone/${id}`, {
        method: "PUT",
        headers: {"Content-Type": "application/json", Authorization: `Bearer ${token}`},
    });
}

export const GetAllTasks = () => {
    const token = localStorage.getItem("token");
    return fetch ("http://localhost:8080/cleaning/getTasks", {
        method: "GET",
        headers: {"Content-Type": "application/json", Authorization: `Bearer ${token}`},
    });
}