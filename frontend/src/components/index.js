import {React} from 'react';
import './index.css';
import NavBar from './navbar/NavBar';
import Content from './content/content';
import Footer from "./footer/footer";
const Index = () => {
    const tabs = [
        {ref: "#home", text: "Home"},
        {ref: "#about", text: "About"},
        {ref: "#room", text: "Room"},
        {ref: "#restaurant", text: "Restaurant"},
        {ref: "#gallery", text: "Gallery"},
        {ref: "/login", text: "Login"},
    ]
    return (
        <div>
            <NavBar buttons={tabs}/>
            <Content />
            <Footer/>
        </div>
    );
}


export default Index;
