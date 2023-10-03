import React, {useEffect, useState} from "react";
import Select from "react-select";
import {AddReservation} from "../../fetches/receptionistFetches";
import "./addReservation.css";
import Navbar from "../../navbar/NavBar";


const AddReservations = () => {

    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');
    const [rooms2, setSelected] = useState('');
    const [popupStyle2, showPopup2] = useState("hide");
    const [popupStyle, showPopup] = useState("hide");
    const [responseStatus, setResponseStatus] = useState(0);
    const [content, setContent] = useState("");

    const popup = () => {
        showPopup("add-popup");
        setTimeout(() => showPopup("hide"), 3000);
    };

    const popup2 = () => {
        showPopup2("add-popup2");
        setTimeout(() => showPopup2("hide"), 3000);
    };

    const handleClick = (e) => {
        e.preventDefault()
        const roomType = rooms2.value;
        const client = {firstName, lastName, email, startDate, endDate, roomType};
        AddReservation(client).then((response) => {
            setContent(response.json());
            setResponseStatus(response.status);
        });
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

    function onFirstNameChange(event) {
        setFirstName(event.target.value);
    }

    function onLastNameChange(event) {
        setLastName(event.target.value);
    }

    function onEmailChange(event) {
        setEmail(event.target.value);
    }

    function onStartDate(event) {
        setStartDate(event.target.value);
    }

    function onEndDate(event) {
        setEndDate(event.target.value);
    }

    const roomsOptions = [
        {value:"single", label:'Single-room'},
        {value:"double", label:'Double-room'},
        {value:"triple", label:'Triple-room'},
        {value:"family",label:'Family-room'},
    ];

    const tabs = [
        {ref: "/", text: "Home"},
        {ref: "/addRoom", text: "Add room"},
        {ref: "/updateRoom", text: "Update room"},
        {ref: "/deleteRoom", text: "Delete room"},
        {ref: "/addReservation", text: "Make reservation"},
        {ref: "/updateReservation", text: "Update reservation"},
        {ref: "/deleteReservation", text: "Delete reservation"},

    ]

    return(

        <div className="page-container">
            <Navbar buttons={tabs}/>
            <div className="cover-res">
            <h1>Make Reservation</h1>
            <input value={firstName} onChange={onFirstNameChange} type="text" placeholder="First Name"/>
            <input value={lastName} onChange={onLastNameChange} type="text" placeholder="Last Name"/>
            <input value={email} onChange={onEmailChange} type="email" placeholder="Email"/>

            <h3>Start date</h3>
            <input value={startDate} onChange={onStartDate} type="date" placeholder="Start Date"/>
            <h3>End date</h3>
            <input value={endDate} onChange={onEndDate} type="date" placeholder="End Date"/>
            <Select
                options={roomsOptions}
                placeholder="Select room"
                onChange={setSelected}
            />
            <button onClick={handleClick} className="button-add-reservation">Submit</button>
            </div>
            <div className={popupStyle}>
                <h3>Error</h3>
                <p>This is an error</p>
            </div>
            <div className={popupStyle2}>
                <h3>Reservation Added</h3>
                <p>Reservation successfully added!</p>
            </div>
        </div>

    )

}
export default AddReservations;