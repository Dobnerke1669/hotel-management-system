import React, {useEffect, useState} from 'react';
import "./addUser.css";
import Select from 'react-select';
import {AddNewUser} from "../fetches/adminFetch";
import Navbar from "../navbar/NavBar";

const AddUser = () => {
    const [popupStyle, showPopup] = useState("hide");
    const [popupStyle2, showPopup2] = useState("hide");
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [roles2, setSelected] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [responseStatus, setResponseStatus] = useState(0);
    const [content, setContent] = useState("");

    const handleClick = (e) => {
        e.preventDefault();
        const role = [roles2.value];
        const user = {email, password, username, firstName, lastName, role};
        AddNewUser(user).then((response) => {
            setContent(response.json());
            setResponseStatus(response.status);
        })
    };

    useEffect(() => {
        if ((responseStatus === 404 && content) || (responseStatus === 400 && content) || (responseStatus === 469 && content) || (responseStatus === 500 && content))
        {
            popup();
        }
        else if (responseStatus === 200 && content)
        {
            popup2();
        }
    }, [content, responseStatus]);

    const popup = () => {
        showPopup("add-popup");
        setTimeout(() => showPopup("hide"), 3000);
    };

    const popup2 = () => {
        showPopup2("add-popup2");
        setTimeout(() => showPopup2("hide"), 3000);
    };

    const rolesOptions = [
        {value: "user"},
        {value: "client", label: 'Client'},
        {value: "admin", label: 'Admin'},
        {value: "receptionist", label: 'Receptionist'},
        {value: "kitchen", label: 'Kitchen'},
        {value: "cleaning", label: 'Cleaning'},
    ];

    function checkPassword() {
        let password = document.getElementById("password").value;
        let confirmPassword = document.getElementById("confirm-password").value;
        let message = document.getElementById("message");

        if (password.length !== 0) {
            if (password === confirmPassword) {
                message.textContent = "Passwords match";
            } else {
                message.textContent = "Passwords don't match";
            }
        }
    }

    function onFirstNameChange(event) {
        setFirstName(event.target.value);
    }

    function onLastNameChange(event) {
        setLastName(event.target.value);
    }

    function onNumberChange(event) {
        setUsername(event.target.value);
    }

    function onEmailChange(event) {
        setEmail(event.target.value);
    }


    function handleChange(selectedOption) {
        setSelected(selectedOption);
    }

    function onPasswordChange(event) {
        setPassword(event.target.value);
    }

    function onConfirmPasswordChange(event) {
        setConfirmPassword(event.target.value);
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
            <div className="cover">
                <h1>Add User</h1>
                <input
                    value={firstName}
                    onChange={onFirstNameChange}
                    type="text"
                    placeholder="first name"
                />
                <input
                    value={lastName}
                    onChange={onLastNameChange}
                    type="text"
                    placeholder="last name"
                />
                <input
                    value={username}
                    onChange={onNumberChange}
                    type="text"
                    placeholder="Username"
                />
                <input
                    value={email}
                    onChange={onEmailChange}
                    type="email"
                    placeholder="email"
                />
                <input
                    value={password}
                    onChange={onPasswordChange}
                    type="password"
                    placeholder="password"
                    id="password"
                />
                <input
                    value={confirmPassword}
                    onChange={onConfirmPasswordChange}
                    type="password"
                    placeholder="confirm password"
                    id="confirm-password"
                    onBlur={checkPassword}
                />
                <p id="message"></p>

                <Select
                    options={rolesOptions}
                    onChange={handleChange}
                    placeholder="Select a role"
                />
                <button className="add-button" onClick={handleClick}>Add User</button>
                <div className={popupStyle}>
                    <h3>Faild</h3>
                    <p>Something is wrong</p>
                </div>
                <div className={popupStyle2}>
                    <h3>Account created</h3>
                    <p>Account is successfully created!</p>
                </div>
            </div>
        </div>

    );
};
export default AddUser;