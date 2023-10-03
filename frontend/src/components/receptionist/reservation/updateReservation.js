import React, {useState, useEffect} from 'react';
import {GetAllReservations, UpdateReservation} from "../../fetches/receptionistFetches";
import Navbar from "../../navbar/NavBar";

const UpdateReservations = () => {
    const [data, setData] = useState([]);
    const [isEditing, setIsEditing] = useState(false);
    const [editedData, setEditedData] = useState(null);
    const handleEdit = (index) => {
        setIsEditing(true);
        setEditedData({...data[index]});
    };

    const handleSave =(index) => {
        // Update the data array with the edited data
        const newData = [...data];
        newData[index] = editedData;
        setData(newData);
        UpdateReservation(editedData);
        setIsEditing(false);
        setEditedData(null);
    };

    const handleCancel = () => {
        setIsEditing(false);
        setEditedData(null);
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
            <table>
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
                {data.map((reservation, index) => (
                    <tr key={reservation.id}>
                        <td>{reservation.id}</td>
                        <td>{isEditing && editedData.id === reservation.id ? (
                            <input
                                type="text"
                                value={editedData.startDate}
                                onChange={(e) => setEditedData({...editedData, startDate: e.target.value})}
                            />
                        ) : reservation.startDate}</td>
                        <td>{isEditing && editedData.id === reservation.id ? (
                            <input
                                type="text"
                                value={editedData.endDate}
                                onChange={(e) => setEditedData({...editedData, endDate: e.target.value})}
                            />
                        ) : reservation.endDate}</td>
                        <td>{isEditing && editedData.id === reservation.id ? (
                            <input
                                type="text"
                                value={editedData.roomType}
                                onChange={(e) => setEditedData({...editedData, roomType: e.target.value})}
                            />
                        ) : reservation.roomType}</td>
                        <td>{isEditing && editedData.id === reservation.id ? (
                            <input
                                type="text"
                                value={editedData.totalPrice}
                                onChange={(e) => setEditedData({...editedData, totalPrice: e.target.value})}
                            />
                        ) : reservation.totalPrice}</td>
                        <td>
                            {isEditing && editedData.id === reservation.id ? (
                                <>
                                    <button onClick={() => handleSave(index)}>Save</button>
                                    <button onClick={handleCancel}>Cancel</button>
                                </>
                            ) : (
                                <button onClick={() => handleEdit(index)}>Edit</button>
                            )}
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}

export default UpdateReservations;