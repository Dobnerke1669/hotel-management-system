import React, {useEffect, useState} from "react";
import {DeleteReservation, GetAllReservations} from "../../fetches/receptionistFetches";
import Navbar from "../../navbar/NavBar";

const DeleteReservations = () => {
    const [data, setData] = useState([]);

    const deleteReservation =(id) => {
        DeleteReservation(id).then(() => fetchFunction());
    };

    useEffect(() => {
        fetchFunction();
    }, []);
    const fetchFunction =  () => {
        GetAllReservations().then(data => data.json())
            .then(data => setData(data));
    }

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
        <div className="page-container">
            <Navbar buttons={tabs}/>
            <table id="table">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Room Type</th>
                    <th>Total Price</th>
                </tr>
                </thead>
                <tbody>
                {data.map(reservation => (
                    <tr key={reservation.id}>
                        <td>{reservation.id}</td>
                        <td>{reservation.startDate}</td>
                        <td>{reservation.endDate}</td>
                        <td>{reservation.roomType}</td>
                        <td>{reservation.totalPrice}</td>
                        <td>
                            <button onClick={() => deleteReservation(reservation.id)}>Delete Reservation</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}

export default DeleteReservations;