import React, {useState, useEffect} from "react";
import "./loginform.css";
import {Link, useNavigate} from "react-router-dom";
import {LoginFetch} from "../fetches/authFetch";
import {GetAllUsers} from "../fetches/authFetch";

const LoginForm = ({onChangeLoggedInStatus}) => {

    const [popupStyle, showPopup] = useState("hide")
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [isLoginErrorVisible, setIsLoginErrorVisible] = useState(false);
    const navigate = useNavigate();
    const [responseStatus, setResponseStatus] = useState(0);
    const [content, setContent] = useState("");

    const handleClick = (e) => {
        try {
            e.preventDefault();
            const user = {username, password};
            LoginFetch(user).then((data) => {
                setResponseStatus(data.status);
                data.json().then((data) => {
                    setContent(data);
                });
            });
        } catch
            (e) {
            setIsLoginErrorVisible(true);
            onChangeLoggedInStatus(false);
            console.error(e);
        }
    }
    useEffect(() => {
        if (responseStatus === 401)
        {
            popup();
        }
        if (responseStatus === 200 && content) {
            const token = content.accessToken;
            const roles2 = content.roles;
            setIsLoginErrorVisible(false);
            localStorage.setItem("token", token);
            onChangeLoggedInStatus(true);
            const navigation = getNavigationLink(roles2[0]);
            navigate(navigation);
            GetAllUsers().then(data => data.json())
                .then(data => {
                    data.map(user => {
                        if (user.username === username) {
                            localStorage.setItem("id", user.id);
                        }
                    }, [])
                });
        } else {
            setIsLoginErrorVisible(true);
        }
    }, [content, responseStatus]);
    const getNavigationLink = (roles) => {
        switch (roles) {
            case "ROLE_ADMIN":
                return "/admin";
            case "ROLE_CLIENT":
                return "/client";
            case "ROLE_RECEPTIONIST":
                return "/receptionist";
            case "ROLE_KITCHEN":
                return "/kitchen";
            case "ROLE_CLEANING":
                return "/cleaning";
        }
    }

    const popup = () => {
        showPopup("login-popup")
        setTimeout(() => showPopup("hide"), 3000)
    }

    function onUsernameChange(event) {
        setUsername(event.target.value);
    }

    function onPasswordChange(event) {
        setPassword(event.target.value);
    }

    return (
        <div className="page-container-login">
            <div className="cover-login">
                <h1>Login</h1>
                <input value={username} onChange={onUsernameChange} type="text" placeholder="Username"/>
                <input value={password} onChange={onPasswordChange} type="password" placeholder="Password"/>
                <button className="login-button" onClick={handleClick}>Login</button>

                <p className="question">Don't have an account?</p>
                <Link to='/register'>
                    <button className="register-button">Register</button>
                </Link>

                <Link to='/'>
                    <button className="register-button">Return to HOME</button>
                </Link>

                <div className={popupStyle}>
                    <h3>Login Failed</h3>
                    <p>Username or password incorrect</p>
                </div>
            </div>
        </div>
    )
}

export default LoginForm;