import React, {useEffect, useState} from "react";
import {AssignTask, GetAllTasks, MarkAsDone} from "../fetches/cleaningFetches";
import Navbar from "../navbar/NavBar";
import './cleaning.css';

export const Cleaning = () => {

    const [data, setData] = useState([]);

    const assignTask =(id) => {
        AssignTask(id,localStorage.getItem("id")).then(() => fetchFunction());
    };

    const markAsDone =(id) => {
        MarkAsDone(id).then(() => fetchFunction());
    }

    useEffect(() => {
        fetchFunction();
    }, []);
    const fetchFunction =  () => {
        GetAllTasks().then(data => data.json())
            .then(data =>{setData(data); });

    }
    const tabs = [
        {ref: "/", text: "Home"},

    ]

    return(
        <div className="page-container">
            <Navbar buttons={tabs}/>
            <table id="table">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Room Number</th>
                    <th>Task</th>
                    <th>Assigned To</th>
                    <th>Status</th>
                </tr>
                </thead>
                <tbody>
                {data.map(notification => (
                    !notification.done &&
                    <tr key={notification.id}>
                        <td>{notification.id}</td>
                        <td>{notification.roomNumber}</td>
                        <td>{notification.details}</td>
                        <td>{notification.cleanerId}</td>
                        <td>{notification.done ? 'Done' : 'Not done'}</td>
                        <td>
                            {notification.cleanerId === "Not assigned" ? (
                                <button onClick={() => assignTask(notification.id)}>
                                    Assign to yourself
                                </button>
                            ) : (
                                <button onClick={() => markAsDone(notification.id)}>
                                    Mark as Done
                                </button>
                            )}
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    )
}