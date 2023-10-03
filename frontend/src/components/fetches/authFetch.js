export const LoginFetch = (user) => {
        return fetch("http://localhost:8080/api/auth/signin", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(user),
        })
}
export const RegisterFetch = (user) => {
        return fetch("http://localhost:8080/api/auth/signup", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(user),
    })
}

export const GetAllUsers = () => {
    return fetch("http://localhost:8080/api/auth/findAll", {
        method: "GET",
        headers: {"Content-Type": "application/json"},
    });
}

