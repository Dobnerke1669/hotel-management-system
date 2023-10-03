import React, {useEffect, useState} from "react";
import {DeleteProductFetch, GetProducts} from "../fetches/adminFetch";
import Navbar from "../navbar/NavBar";

export const DeleteProduct = () => {
    const [data, setData] = useState([]);

    useEffect(() => {
        getProductsFunction();
    }, []);
    const getProductsFunction = () => {
        GetProducts().then(data => data.json())
            .then(data => {setData(data)});
    }

    const changeStatus = (id) => {
        DeleteProductFetch(id).then(() => getProductsFunction());
    }
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
        <div className="page-container">
            <Navbar buttons={tabs}/>
        <div className="tabel-container">
            <div className="table-wrapper">
                <table id="table1">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Email</th>
                        <th>Username</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Enabled</th>
                        <th>Select</th>
                    </tr>
                    </thead>
                    <tbody>
                    {data.map(product => (
                        <tr key={product.id}>
                            <td>{product.id}</td>
                            <td>{product.name}</td>
                            <td>{product.category}</td>
                            <td>{product.price}</td>
                            <td>{product.description}</td>
                            <td>
                                <button onClick={() => changeStatus(product.id)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    );
}