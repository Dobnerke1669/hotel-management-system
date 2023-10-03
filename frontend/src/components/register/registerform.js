import React, {useState, useEffect} from "react";
import "./registerform.css";
import { Link } from 'react-router-dom';
import {RegisterFetch} from "../fetches/authFetch";
const Registerform = () => {

    const[firstName, setFirstName] = useState('');
    const[lastName, setLastName] = useState('');
    const[username, setUsername] = useState('');
    const[email, setEmail] = useState('');
    const[password, setPassword] = useState('');
    const[confirmPassword, setConfirmPassword] = useState('');
    const [popupStyle, showPopup] = useState("hide");
    const [responseStatus, setResponseStatus] = useState(0);
    const [content, setContent] = useState("");

    const handleClick=(e)=>{
        e.preventDefault()
        const user = {email, password, username, firstName, lastName}
        RegisterFetch(user).then((response) => {
            setContent(response.json());
            setResponseStatus(response.status);
        })
    };

    useEffect(() => {
        if (responseStatus === 400 && content)
        {
            popup();
        }

    }, [content, responseStatus]);

    const popup = () => {
        showPopup("register-popup")
        setTimeout(() => showPopup("hide"), 3000)
    }

    function checkPassword(){
        let password = document.getElementById("password").value;
        let confirmPassword = document.getElementById("confirm-password").value;
        let message = document.getElementById("message");

        if(password.length !== 0)
        {
            if(password === confirmPassword)
            {
                message.textContent = "Passwords match";
            }
            else
            {
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

    function onUsernameChange(event) {
        setUsername(event.target.value);
    }

    function onEmailChange(event) {
        setEmail(event.target.value);
    }

    function onPasswordChange(event) {
        setPassword(event.target.value);
    }

    function onConfirmPasswordChange(event) {
        setConfirmPassword(event.target.value);
    }


    return(
        <div className="page-container-register">
            <div className="cover">
                <h1>Register Now</h1>
                <input value={firstName} onChange={onFirstNameChange} type="text" placeholder="First Name"/>
                <input value={lastName} onChange={onLastNameChange} type="text" placeholder="Last Name"/>
                <input value={username} onChange={onUsernameChange} type="text" placeholder="Username"/>
                <input value={email} onChange={onEmailChange} type="email" placeholder="Email"/>
                <input value={password} onChange={onPasswordChange} type="password" placeholder="Password" id="password"/>
                <input value={confirmPassword} onChange={onConfirmPasswordChange} type="password" placeholder="Confirm Password" id="confirm-password" onBlur={checkPassword}/>
                <p id="message"></p>

                <button className="register-button" disabled={password !== confirmPassword} onClick={handleClick}>Register</button>
                <div className={popupStyle}>
                    <h3>Contactati adminul</h3>
                    <p>Contul NU a fost creat. Contactati adminul pentru ajutor.</p>
                </div>
                <Link to ='/login'  >
                    <button className="register-button">Login</button>
                </Link>
            </div>
        </div>
    );
}

export default Registerform