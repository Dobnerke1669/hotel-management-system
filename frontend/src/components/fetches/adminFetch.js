export const AddNewUser = (user) => {
    const token = localStorage.getItem("token");
    return fetch("http://localhost:8080/admin/addUser", {
        method: "POST",
        headers: {"Content-Type": "application/json", Authorization: `Bearer ${token}`},
        body: JSON.stringify(user),
    })
}

export const GetAllUsers = () => {
    const token = localStorage.getItem("token");
    return fetch("http://localhost:8080/admin/findAll", {
        method: "GET",
        headers: {"Content-Type": "application/json", Authorization: `Bearer ${token}`},
    })
}
export const DisableUserFetch = (id) => {
    const token = localStorage.getItem("token");
    return fetch(`http://localhost:8080/admin/disableUser/${id}`, {
        method: "PUT",
        headers: {"Content-Type": "application/json", Authorization: `Bearer ${token}`},
    });
}

export const UpdateUserFetch = (user) => {
    const token = localStorage.getItem("token");
    fetch('http://localhost:8080/admin/updateUser', {
        method: 'PUT',
        body: JSON.stringify(user),
        headers: {"Content-Type": "application/json", Authorization: `Bearer ${token}`},
    })
}

export const AddNewProduct = (product) => {
    const token = localStorage.getItem("token");
    return fetch('http://localhost:8080/admin/addProduct', {
        method: 'POST',
        body: JSON.stringify(product),
        headers: {"Content-Type": "application/json", Authorization: `Bearer ${token}`},
    })
}

export const GetProducts = () => {
    const token = localStorage.getItem("token");
    return fetch("http://localhost:8080/admin/getProducts", {
        method: "GET",
        headers: {"Content-Type": "application/json", Authorization: `Bearer ${token}`},
    })
}

export const UpdateProductFetch = (product) => {
    const token = localStorage.getItem("token");
    fetch('http://localhost:8080/admin/updateProduct', {
        method: 'PUT',
        body: JSON.stringify(product),
        headers: {"Content-Type": "application/json", Authorization: `Bearer ${token}`},
    })
}

export const DeleteProductFetch = (id) => {
    const token = localStorage.getItem("token");
    return fetch(`http://localhost:8080/admin/deleteProduct/${id}`, {
        method: "DELETE",
        headers: {"Content-Type": "application/json", Authorization: `Bearer ${token}`},
    });
}