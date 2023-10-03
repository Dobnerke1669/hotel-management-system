import React, {useState} from "react";
import "./NavBar.css";
import myPhoto from "../photos/LOGOT3.png";
import {Link} from "react-router-dom";


function Navbar({ buttons = [] }) {
    const [tabs, setButtons] = useState(buttons);
    return (
        <header className="header" id="navigation-menu">
            <div className="container">
                <nav>
                    <Link to="/"><img src={myPhoto} alt="imagine"/></Link>
                    <ul className="nav-menu">
                        { tabs.map((tab) => <li><a href={tab.ref} className="nav-link">{tab.text}</a></li>)}
                    </ul>
                </nav>
            </div>
        </header>
    );
}

export default Navbar;