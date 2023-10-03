import React, {useState, useEffect} from 'react';
import {GetAllRooms, UpdateRoom} from "../../fetches/receptionistFetches";
import Navbar from "../../navbar/NavBar";

const UpdateRooms = () => {
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
        UpdateRoom(editedData);
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
            <table>
                <thead>
                <tr>
                    <th>Number</th>
                    <th>Room Type</th>
                    <th>Price Per Night</th>
                    <th>Select</th>
                </tr>
                </thead>
                <tbody>
                {data.map((room, index) => (
                    <tr key={room.number}>
                        <td>{room.number}</td>
                        <td>{isEditing && editedData.number === room.number ? (
                            <input
                                type="text"
                                value={editedData.roomType}
                                onChange={(e) => setEditedData({...editedData, roomType: e.target.value})}
                            />
                        ) : room.roomType}</td>
                        <td>{isEditing && editedData.number === room.number ? (
                            <input
                                type="text"
                                value={editedData.pricePerNight}
                                onChange={(e) => setEditedData({...editedData, pricePerNight: e.target.value})}
                            />
                        ) : room.pricePerNight}</td>
                        <td>
                            {isEditing && editedData.number === room.number ? (
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

export default UpdateRooms;