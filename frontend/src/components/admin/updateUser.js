import React, {useState, useEffect} from 'react';
import {GetAllUsers, UpdateUserFetch} from "../fetches/adminFetch";
import './updateUser.css';
import Navbar from "../navbar/NavBar";

const UpdateUser = () => {
    const [data, setData] = useState([]);
    const [isEditing, setIsEditing] = useState(false);
    const [editedData, setEditedData] = useState(null);
    const handleEdit = (index) => {
        setIsEditing(true);
        setEditedData({...data[index]});
    };

    const handleSave =(index) => {
        // Update the data array with the edited data
        const newData = [...data];
        newData[index] = editedData;
        setData(newData);
        UpdateUserFetch(editedData);
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
        GetAllUsers().then(data => data.json())
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


    return  (
        <div className="page-container">
            <Navbar buttons={tabs}/>
            <div className="cover-update">
                <table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Email</th>
                        <th>Username</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    {data.map((user, index) => (
                        <tr key={user.id}>
                            <td>{user.id}</td>
                            <td>{isEditing && editedData.id === user.id ? (
                                <input
                                    type="text"
                                    value={editedData.email}
                                    onChange={(e) => setEditedData({...editedData, email: e.target.value})}
                                />
                            ) : user.email}</td>
                            <td>{isEditing && editedData.id === user.id ? (
                                <input
                                    type="text"
                                    value={editedData.username}
                                    onChange={(e) => setEditedData({...editedData, username: e.target.value})}
                                />
                            ) : user.username}</td>
                            <td>{isEditing && editedData.id === user.id ? (
                                <input
                                    type="text"
                                    value={editedData.firstName}
                                    onChange={(e) => setEditedData({...editedData, firstName: e.target.value})}
                                />
                            ) : user.firstName}</td>
                            <td>{isEditing && editedData.id === user.id ? (
                                <input
                                    type="text"
                                    value={editedData.lastName}
                                    onChange={(e) => setEditedData({...editedData, lastName: e.target.value})}
                                />
                            ) : user.lastName}</td>
                            <td>
                                {isEditing && editedData.id === user.id ? (
                                    <>
                                        <button onClick={() => handleSave(index)} className="button-update">Save</button>
                                        <button onClick={handleCancel} className="button-update">Cancel</button>
                                    </>
                                ) : (
                                    <button onClick={() => handleEdit(index)} className="button-update">Edit</button>
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

export default UpdateUser;