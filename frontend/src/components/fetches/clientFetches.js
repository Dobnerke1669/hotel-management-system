export const AddReservation = (reservation) => {
    const token = localStorage.getItem("token");
    return fetch ("http://localhost:8080/client/addReservation", {
        method: "POST",
        headers: {"Content-Type": "application/json", Authorization: `Bearer ${token}`},
        body: JSON.stringify(reservation),
    });
}

export const DeleteReservation = (id) => {
    const token = localStorage.getItem("token");
    return fetch (`http://localhost:8080/client/deleteReservation/${id}`, {
        method: "DELETE",
        headers: {"Content-Type": "application/json", Authorization: `Bearer ${token}`},
    });
}

export const GetReservations = (id) => {
    const token = localStorage.getItem("token");
    return fetch (`http://localhost:8080/client/getReservations/${id}`, {
        method: "GET",
        headers: {"Content-Type": "application/json", Authorization: `Bearer ${token}`},
    });
}

export const ViewPrice = (reservation) => {
    const token = localStorage.getItem("token");
    return fetch ("http://localhost:8080/client/viewPrice", {
        method: "POST",
        headers: {"Content-Type": "application/json", Authorization: `Bearer ${token}`},
        body: JSON.stringify(reservation),
    });

}

export const CreateTicket = (details, roomNumber) => {
    const ticket = {details, roomNumber};
    const token = localStorage.getItem("token");
    return fetch (`http://localhost:8080/client/sendNotification`, {
        method: "POST",
        headers: {"Content-Type": "application/json", Authorization: `Bearer ${token}`},
        body: JSON.stringify(ticket),
    });
}
export const GetUser = (username) => {
    const token = localStorage.getItem("token");
    const link = `http://localhost:8080/client/getUser/${username}`;
    return fetch (`http://localhost:8080/client/getUser/${username}`, {
        method: "GET",
        headers: {"Content-Type": "application/json", Authorization: `Bearer ${token}`},
    });
}

export const addOrder = (order) => {
    const token = localStorage.getItem("token");
    fetch ("http://localhost:8080/client/addOrder", {
        method: "POST",
        headers: {"Content-Type": "application/json", Authorization: `Bearer ${token}`},
        body: JSON.stringify(order),
    });
}

export const GetImage = async (id) => {
    const token = localStorage.getItem("token");
    const response = await fetch(`http://localhost:8080/client/image/${id}`, {
        method: "GET",
        headers: {"Content-Type": "application/json", Authorization: `Bearer ${token}`},
    });
    const blob = await response.blob();
    const imageUrl = URL.createObjectURL(blob);
    return imageUrl;
}