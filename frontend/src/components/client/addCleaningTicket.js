import React, {useState, useEffect} from "react";
import {CreateTicket} from "../fetches/clientFetches";
import "./addCleaningTicket.css";
import Navbar from "../navbar/NavBar";
import {tabs} from "./tabs";
import Footer from "../footer/footer";

export const AddCleaningTicket = () => {
    const [details,setDetails] = useState("");
    const [roomNumber,setRoomNumber] = useState("");
    const [popupStyle, showPopup] = useState("hide");
    const [popupStyle2, showPopup2] = useState("hide");
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

    const onDetailsChange = (e) => {
        setDetails(e.target.value);
    }
    const onRoomNumberChange = (e) => {
        setRoomNumber(e.target.value);
    }

    const sendTicket = () => {
        CreateTicket(details, roomNumber).then((response) => {
            setContent(response.json());
            setResponseStatus(response.status);
        })

    }

    useEffect(() => {
        if ((responseStatus === 404 && content) || (responseStatus === 400 && content) || (responseStatus === 500 && content))
        {
            popup();
        }
        else if (responseStatus === 200 && content)
        {
            popup2();
        }

    }, [content, responseStatus]);

    return (
        <div className="page-container-cleaning">
            <Navbar buttons={tabs}/>
            <div className="page-container">
                <div className="cover-cleaning">
                    <h1>Add Cleaning Ticket</h1>
                    <input
                        value={roomNumber}
                        onChange={onRoomNumberChange}
                        type="text"
                        placeholder="room number"
                    />
                    <input
                        value={details}
                        onChange={onDetailsChange}
                        type="text"
                        placeholder="details"
                    />
                    <button onClick={sendTicket} className="button-cleaning-ticket">Send Ticket</button>
                    <div className={popupStyle}>
                        <h3>Ticket NOT sent</h3>
                        <p>Please call the admin to help you</p>
                    </div>
                    <div className={popupStyle2}>
                        <h3>Cleaning ticket sent</h3>
                        <p>Someone will come and help with your problem</p>
                    </div>
                </div>
            </div>
            <Footer />
        </div>
    )
}