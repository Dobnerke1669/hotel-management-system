import React, { useEffect, useState } from "react";
import { GetAllOrders, GetOrderedProducts, MarkAsDone } from "../fetches/kitchenFetches";
import './kitchen.css'
import Navbar from "../navbar/NavBar";

export const KitchenPage = () => {
    const [data, setData] = useState([]);

    const markAsDone = (id) => {
        MarkAsDone(id).then(() => fetchFunction());
    }

    useEffect(() => {
        fetchFunction();
    }, []);

    const fetchFunction = async () => {
        const response = await GetAllOrders();
        const orders = await response.json();

        const orderedData = await Promise.all(
            orders.map(async (order) => {
                const productResponse = await GetOrderedProducts(order.id);
                const products = await productResponse.json();
                return { order, products };
            })
        );

        setData(orderedData);
    }

    const tabs = [
        { ref: "/", text: "Home" },
    ];

    return (
        <div className="page-container">
            <Navbar buttons={tabs}/>
            {data.map((orderData) => (
                <div key={orderData.order.id}>
                    {!orderData.order.done && (
                        <>
                            <h3>Order ID: {orderData.order.id} To Room: {orderData.order.roomNumber}</h3>
                            <table id="table">
                                <thead>
                                <tr>
                                    <th>Product name</th>
                                    <th>Quantity</th>
                                </tr>
                                </thead>
                                <tbody>
                                {orderData.products.map((product) => (
                                    <tr key={product.id}>
                                        <td>{product.name}</td>
                                        <td>{product.quantity}</td>
                                    </tr>
                                ))}
                                </tbody>
                            </table>
                            <button onClick={() => markAsDone(orderData.order.id)}>Mark as Done</button>
                        </>
                    )}
                </div>
            ))}
        </div>
    );
}
