import React, {useState, useEffect} from 'react';
import {DeleteRoom, GetAllRooms} from "../../fetches/receptionistFetches";
import Navbar from "../../navbar/NavBar";

const DeleteRooms = () => {
    const [data, setData] = useState([]);

    const deleteRoom =(id) => {
        DeleteRoom(id).then(() => fetchFunction());
    };

    useEffect(() => {
        fetchFunction();
    }, []);
    const fetchFunction =  () => {
        GetAllRooms().then(data => data.json())
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

    return(
        <div className="page-container">
            <Navbar buttons={tabs}/>
            <table id="table">
                <thead>
                <tr>
                    <th>Number</th>
                    <th>Room type</th>
                    <th>Price Per Night</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                {data.map(room => (
                    <tr key={room.number}>
                        <td>{room.number}</td>
                        <td>{room.roomType}</td>
                        <td>{room.pricePerNight}</td>
                        <td>
                            <button onClick={() => deleteRoom(room.number)}>Delete Room</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}

export default DeleteRooms;