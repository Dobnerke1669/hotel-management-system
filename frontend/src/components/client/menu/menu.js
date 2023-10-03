import React, {useState} from 'react';
import "./menu.css";
import {Link} from "react-router-dom";

export const Menu = ({items}) => {

    const [roomNumber, setRoomNumber] = useState(0);

    const [orderedItems, setOrder] = useState([]);
    const addToOrder = (item) => {
        const index = orderedItems.findIndex((orderedItem) => orderedItem.id === item.id);
        if (index > -1) {
            const newOrder = [...orderedItems];
            newOrder[index].quantity++;
            setOrder(newOrder);
            return;
        }
        item.quantity = 1;
        setOrder([...orderedItems, item]);
    }

    const onRoomNumberChange = (event) => {
        setRoomNumber(event.target.value);
    }
    return <main className="product-space">
        <h2>Room number</h2>
        <input
            value={roomNumber}
            onChange={onRoomNumberChange}
            type="text"
            placeholder="Room number"
        />
        <div className="menu-container">

            <div className="row-menu">
                {
                    items.map((item) => {
                        return (
                            <div key={item.id} className="col-6">
                                <article className="main-div">
                                    <div className="main-img">
                                        <img src={item.image} alt={item.title}/>
                                    </div>
                                    <div className="content-menu">
                                        <div className="title">
                                            <h2>{item.name}</h2>
                                            <h4>-price:</h4>
                                            <h4>{item.price}</h4>
                                        </div>
                                        <p>{item.description}</p>
                                        <button onClick={() => addToOrder(item)} className="button-add-to-order">Add to order</button>
                                    </div>
                                </article>
                            </div>
                        )
                    })
                }
        </div>
        </div>
        <button onClick={() => setOrder([])} className="button-clear-order">Clear order</button>
        <Link to={`/client/basket?orderedItems=${encodeURIComponent(JSON.stringify(orderedItems))}&roomNumber=${roomNumber}`}>
            <button className="button-show-order">Show order</button>
        </Link>
    </main>

}