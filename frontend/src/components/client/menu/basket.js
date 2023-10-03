import {useLocation} from "react-router";
import React, {useEffect, useState} from "react";
import {addOrder} from "../../fetches/clientFetches";
import Navbar from "../../navbar/NavBar";
import {tabs} from "../tabs";
import './basket.css'

export const Basket = (order) => {
    const location = useLocation();
    const queryParams = new URLSearchParams(location.search);
    const orderedItemsParam = queryParams.get("orderedItems");
    const roomNumberParam = queryParams.get("roomNumber");
    const orderedItems = orderedItemsParam ? JSON.parse(orderedItemsParam) : [];
    const roomNumberChange = roomNumberParam ? parseInt(roomNumberParam) : 0;
    const [roomNumber, setRoomNumberChange] = useState(roomNumberChange);
    const [totalPrice, setTotalPrice] = useState(0);
    const [popupStyle, showPopup] = useState("hide");

    useEffect(() => {
        // Calculate the total price when orderedItems or their quantities change
        const calculatedTotalPrice = orderedItems.reduce((total, item) => {
            return total + item.price * item.quantity;
        }, 0);
        setTotalPrice(calculatedTotalPrice);
    }, [orderedItems]);
    const popup = () => {
        showPopup("add-popup");
        setTimeout(() => showPopup("hide"), 3000);
    };
    const sendOrder = async () => {
        popup();
        let productList = [];
        for (let i = 0; i < orderedItems.length; i++) {
            const id = orderedItems[i].id;
            const quantity = orderedItems[i].quantity;
            const itemToSend = {id, quantity};
            productList = [...productList, itemToSend];
        }
        const orderToSend = {roomNumber, productList};
        addOrder(orderToSend);
    }
    const onRoomNumberChange = (event) => {
        setRoomNumberChange(event.target.value);
    }

    return (
        <div className="page-container">
            <Navbar buttons={tabs}/>
            {orderedItems.map((item) => {
                return (
                    <div key={item.id} className="basket-item">
                        <article className="main-div">
                            <div className="main-img">
                                <img src={item.image} alt={item.title}/>
                            </div>
                            <div className="content-menu">
                                <h4>Name:{item.name}</h4>
                                <h6>Quantity:{item.quantity}</h6>
                                <h6>Price:{item.price}</h6>
                            </div>
                        </article>
                    </div>
                )
            })}
            <h2>Room number</h2><input className={"room-number"}
                                       value={roomNumberChange}
                                       onChange={onRoomNumberChange}
                                       type="text"
                                       placeholder="Room number"
        />
            <h2>Total price: {totalPrice}</h2>
            <button onClick={sendOrder}>Send Order</button>
            <div className={popupStyle}>
                <h3>Order placed</h3>
                <p>The ordered items will get to you soon!</p>
            </div>
        </div>
    )
}