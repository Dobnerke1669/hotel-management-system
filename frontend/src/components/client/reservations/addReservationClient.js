import React, {useEffect, useState} from "react";
import Select from "react-select";
import {AddReservation, ViewPrice} from "../../fetches/clientFetches";
import "../addReservationClient.css";
import Navbar from "../../navbar/NavBar";
import {tabs} from "../tabs";
import Footer from "../../footer/footer";

const AddReservationClient = () => {

    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');
    const [rooms2, setSelected] = useState('');
    const [finalPrice, showFinalPrice] = useState('');
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

    useEffect(() => {
        showFinalPrice(null);
        if (startDate && endDate && rooms2)
        {
            const roomType=rooms2.value;
            const reservation = {startDate, endDate, roomType};
            ViewPrice(reservation).then(data => data.json()).then(data => showFinalPrice(data))
        }

    }, [startDate,endDate,rooms2]);

    const handleClick = (e) => {
        e.preventDefault()
        const roomType = rooms2.value;
        const clientId = localStorage.getItem("id");
        const finalPrice = 0;
        const reservation = {clientId, startDate, endDate, roomType, finalPrice};
        AddReservation(reservation).then((response) => {
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

    return(
        <div className="page-container-reservation">
            <Navbar buttons={tabs}/>
            <div className="mini-container">
                <div className="cover-reservation">
                    <h1>Make Reservation</h1>
                    <h3>Start date</h3>
                    <input value={startDate} onChange={onStartDate} type="date" placeholder="Start Date"/>
                    <h3>End date</h3>
                    <input value={endDate} onChange={onEndDate} type="date" placeholder="End Date"/>
                    <Select
                        options={roomsOptions}
                        placeholder="Select room"
                        onChange={setSelected}
                    />
                    <div className="final-price">
                        <h2>{finalPrice}</h2>
                    </div>
                    <button onClick={handleClick} className="button-make-reservation">Submit</button>
                    <div className={popupStyle}>
                        <h3>Reservation didn't succed</h3>
                        <p>Please call the admin to help you</p>
                    </div>
                    <div className={popupStyle2}>
                        <h3>Reservation done</h3>
                        <p>We will wait for you to come!</p>
                    </div>
                </div>
            </div>
            <Footer/>
        </div>
    )



}
export default AddReservationClient;