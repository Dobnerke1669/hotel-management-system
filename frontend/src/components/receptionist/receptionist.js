import React from 'react';
import "./receptionist.css";
import Navbar from "../navbar/NavBar";


export const Receptionist = () => {
    const tabs = [
        {ref: "/", text: "Home"},
        {ref: "/addRoom", text: "Add room"},
        {ref: "/updateRoom", text: "Update room"},
        {ref: "/deleteRoom", text: "Delete room"},
        {ref: "/addReservation", text: "Make reservation"},
        {ref: "/updateReservation", text: "Update reservation"},
        {ref: "/deleteReservation", text: "Delete reservation"},

    ]
    return (
        <Navbar buttons={tabs}/>
    );

}
