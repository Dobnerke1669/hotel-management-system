import React, {useEffect, useState} from 'react';
import Select from "react-select";
import {AddNewRoom} from "../../fetches/receptionistFetches";
import "./addRoom.css";
import Navbar from "../../navbar/NavBar";

const AddRooms = () =>{
    const [number, setNumber]= useState("");
    const [pricePerNight, setPrice]= useState("");
    const [type, setType]= useState("");
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
        const roomType = type.value;
        const room = {number, roomType, pricePerNight};
        AddNewRoom(room).then((response) => {
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

    function onNumber(event){
        setNumber(event.target.value);
    }

    function onPrice(event){
        setPrice(event.target.value);
    }

    function onTypeChange(SelectedOptions){
        setType(SelectedOptions);
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
            <div className="cover-addRoom">
            <h1>Add Room</h1>
            <input onChange={onNumber} type="number" placeholder="Number room"/>

            <Select
                onChange={onTypeChange}
                options={roomsOptions}
                placeholder="Type rooms"
            />
            <input onChange={onPrice} type="number" placeholder="Price room"/>

            <button onClick={handleClick} className="button-add-room">Add Room</button>
            </div>
            <div className={popupStyle}>
                <h3>Error</h3>
                <p>This is an error</p>
            </div>
            <div className={popupStyle2}>
                <h3>Room Added</h3>
                <p>Room added successfully!</p>
            </div>
        </div>
    )


}
export default AddRooms