import React from "react";
import "./admin.css";
import Navbar from "../navbar/NavBar";

export const Admin = () => {
    const tabs = [
        {ref: "/", text: "Home"},
        {ref: "/addUser", text: "Add User"},
        {ref: "/disableUser", text: "Delete User"},
        {ref: "/updateUser", text: "Update User"},
        {ref: "/addProduct", text: "Add Product"},
        {ref: "/deleteProduct", text: "Delete Product"},
        {ref: "/updateProduct", text: "Update Product"}
    ]
    return (
        <Navbar buttons={tabs}/>
    );
}
