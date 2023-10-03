import React from 'react';
import CardItem from "./cardItem";
import './cards.css';
import Client1 from "../../photos/client_1.jpg";
import A2 from "../../photos/a2.jpg";
function Cards() {

    return(
        <div className="cards">
            <h1>
                Lorem ipsum dolor sit amet
            </h1>
            <div className="cards-container">
                <div className="cards-wrapper">
                    <ul className="cards-items">
                        <CardItem
                            src={A2}
                            text="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                            label="make a reservation"
                            path="/client/addReservation"
                        />
                        <CardItem
                        src={Client1}
                        text="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                        label="restaurant"
                        path="/client/menuPage"
                        />
                    </ul>
                </div>
            </div>
        </div>
    );
}

export default Cards;