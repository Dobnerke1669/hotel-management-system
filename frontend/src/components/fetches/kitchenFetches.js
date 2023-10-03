export const GetAllOrders = () => {
    const token = localStorage.getItem("token");
    return fetch("http://localhost:8080/kitchen/getOrders", {
        method: "GET",
        headers: {"Content-Type": "application/json", Authorization: `Bearer ${token}`},
    })
}

export const MarkAsDone = (id) => {
    const token = localStorage.getItem("token");
    return fetch(`http://localhost:8080/kitchen/markOrderDone/${id}`, {
        method: "PUT",
        headers: {"Content-Type": "application/json", Authorization: `Bearer ${token}`},
    });
}
export const GetOrderedProducts = (id) => {
    const token = localStorage.getItem("token");
    return fetch(`http://localhost:8080/kitchen/getOrderedProducts/${id}`, {
        method: "GET",
        headers: {"Content-Type": "application/json", Authorization: `Bearer ${token}`},
    })
}