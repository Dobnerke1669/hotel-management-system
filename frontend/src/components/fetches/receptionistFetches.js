export const AddNewRoom = (room) => {
    const token = localStorage.getItem("token");
    return fetch ("http://localhost:8080/receptionist/addRoom", {
        method: "POST",
        headers: {"Content-Type": "application/json", Authorization: `Bearer ${token}`},
        body: JSON.stringify(room),
    })
}

export const UpdateRoom = (room) => {
    const token = localStorage.getItem("token");
    fetch ("http://localhost:8080/receptionist/updateRoom", {
        method: "PUT",
        headers: {"Content-Type": "application/json", Authorization: `Bearer ${token}`},
        body: JSON.stringify(room),
    })
}

export const GetAllRooms = () => {
    const token = localStorage.getItem("token");
    return fetch ("http://localhost:8080/receptionist/getRooms", {
        method: "GET",
        headers: {"Content-Type": "application/json", Authorization: `Bearer ${token}`},
    })
}

export const DeleteRoom = (id) => {
    const token = localStorage.getItem("token");
    return fetch (`http://localhost:8080/receptionist/deleteRoom/${id}`, {
        method: "DELETE",
        headers: {"Content-Type": "application/json", Authorization: `Bearer ${token}`},
    });
}

export const AddReservation = (reservation) => {
    const token = localStorage.getItem("token");
    return fetch ("http://localhost:8080/receptionist/addReservation", {
        method: "POST",
        headers: {"Content-Type": "application/json", Authorization: `Bearer ${token}`},
        body : JSON.stringify(reservation),
    });
}

export const UpdateReservation = (reservation) => {
    const token = localStorage.getItem("token");
    fetch ("http://localhost:8080/receptionist/updateReservation", {
        method: "PUT",
        headers: {"Content-Type": "application/json", Authorization: `Bearer ${token}`},
        body : JSON.stringify(reservation),
    });
}

export const GetAllReservations = () => {
    const token = localStorage.getItem("token");
    return fetch ("http://localhost:8080/receptionist/getReservations", {
        method: "GET",
        headers: {"Content-Type": "application/json", Authorization: `Bearer ${token}`},
    });
}

export const DeleteReservation = (id) => {
    const token = localStorage.getItem("token");
    return fetch (`http://localhost:8080/receptionist/deleteReservation/${id}`, {
        method: "DELETE",
        headers: {"Content-Type": "application/json", Authorization: `Bearer ${token}`},
    });
}

export const GetSpecificRoom = (id) => {
    const token = localStorage.getItem("token");
    return fetch (`http://localhost:8080/receptionist/getRoom/${id}`, {
        method: "GET",
        headers: {"Content-Type": "application/json", Authorization: `Bearer ${token}`},
    });
}
