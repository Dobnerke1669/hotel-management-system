import React from 'react';
import "./client.css";
import Navbar from "../navbar/NavBar";
import {tabs} from "./tabs";
import Cards from "./homepageClient/cards";
import Footer from "../footer/footer";

export const Client = () => {

    return (
        <div className="page-container">
            <Navbar buttons={tabs}/>
            <Cards />
            <Footer />
        </div>
    )
}
