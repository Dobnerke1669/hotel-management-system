import React, {useState, useEffect} from 'react';
import {DisableUserFetch, GetAllUsers} from "../fetches/adminFetch";
import './disableUser.css';
import Navbar from "../navbar/NavBar";


const DisableUser = () => {
    const [dataTableDisabled, setDataTableDisabled] = useState([]);
    const [dataTableEnabled, setDataTableEnabled] = useState([]);

    useEffect(() => {
        getUsersFunction();
    }, []);
    const getUsersFunction = () => {
        GetAllUsers().then(data => data.json())
            .then(data => {createEnabledUsers(data); createDisabledUsers(data)});
    }

    const createDisabledUsers = (content) => {
        const dataTable1 = [];
        content.map(user => {
            if (user.enabled === false) {
                dataTable1.push(user);
            }
        });
        setDataTableDisabled(dataTable1);
    }

    const createEnabledUsers = (content) => {
        const dataTable2 = [];
        content.map(user => {
            if (user.enabled === true) {
                dataTable2.push(user);
            }
        });
        setDataTableEnabled(dataTable2);
    }

    const changeStatus = (id) => {
        DisableUserFetch(id).then(() => getUsersFunction());
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
            <div className="cover-disable-enable">
                <div className="table-wrapper">
                    <h2>Active Users</h2>
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
                        {dataTableEnabled.map(user => (
                            <tr key={user.id}>
                                <td>{user.id}</td>
                                <td>{user.email}</td>
                                <td>{user.username}</td>
                                <td>{user.firstName}</td>
                                <td>{user.lastName}</td>
                                <td>{user.enabled ? 'Yes' : 'No'}</td>
                                <td>
                                    <button onClick={() => changeStatus(user.id)} className="button-disable-enable">Disable</button>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                    <h2 className="h2-disable-user">Disabled Users</h2>
                    <table id="table2">
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
                        {dataTableDisabled.map(user => (
                            <tr key={user.id}>
                                <td>{user.id}</td>
                                <td>{user.email}</td>
                                <td>{user.username}</td>
                                <td>{user.firstName}</td>
                                <td>{user.lastName}</td>
                                <td>{user.enabled ? 'Yes' : 'No'}</td>
                                <td>
                                    <button onClick={() => changeStatus(user.id)} className="button-disable-enable">Enable</button>
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

export default DisableUser;