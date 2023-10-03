import React, {useEffect, useState} from "react";
import {GetProducts, UpdateProductFetch} from "../fetches/adminFetch";
import Navbar from "../navbar/NavBar";

export const UpdateProduct = () => {
    const [data, setData] = useState([]);
    const [isEditing, setIsEditing] = useState(false);
    const [editedData, setEditedData] = useState(null);
    const handleEdit = (index) => {
        setIsEditing(true);
        setEditedData({...data[index]});
    };

    const handleSave =(index) => {
        const newData = [...data];
        newData[index] = editedData;
        setData(newData);
        UpdateProductFetch(editedData);
        setIsEditing(false);
        setEditedData(null);
    };

    const handleCancel = () => {
        setIsEditing(false);
        setEditedData(null);
    };

    useEffect(() => {
        fetchFunction();
    }, []);

    const fetchFunction =  () => {
        GetProducts().then(data => data.json())
            .then(data => setData(data));
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
        <div>
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Category</th>
                    <th>Price</th>
                    <th>Description</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                {data.map((product, index) => (
                    <tr key={product.id}>
                        <td>{product.id}</td>
                        <td>{isEditing && editedData.id === product.id ? (
                            <input
                                type="text"
                                value={editedData.name}
                                onChange={(e) => setEditedData({...editedData, name: e.target.value})}
                            />
                        ) : product.name}</td>
                        <td>{isEditing && editedData.id === product.id ? (
                            <input
                                type="text"
                                value={editedData.category}
                                onChange={(e) => setEditedData({...editedData, category: e.target.value})}
                            />
                        ) : product.category}</td>
                        <td>{isEditing && editedData.id === product.id ? (
                            <input
                                type="text"
                                value={editedData.price}
                                onChange={(e) => setEditedData({...editedData, price: e.target.value})}
                            />
                        ) : product.price}</td>
                        <td>{isEditing && editedData.id === product.id ? (
                            <input
                                type="text"
                                value={editedData.description}
                                onChange={(e) => setEditedData({...editedData, description: e.target.value})}
                            />
                        ) : product.description}</td>
                        <td>
                            {isEditing && editedData.id === product.id ? (
                                <>
                                    <button onClick={() => handleSave(index)}>Save</button>
                                    <button onClick={handleCancel}>Cancel</button>
                                </>
                            ) : (
                                <button onClick={() => handleEdit(index)}>Edit</button>
                            )}
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
        </div>
    );
}