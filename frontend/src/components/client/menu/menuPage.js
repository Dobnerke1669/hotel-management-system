import React, { useState, useEffect } from 'react';
import { Categories } from "./categories";
import { Menu } from "./menu";
import { fetchItems } from "./dataFetch";
import {GetImage} from "../../fetches/clientFetches";
import Navbar from "../../navbar/NavBar";
import {tabs} from "../tabs";
function MenuPage() {
    const [menuItems, setMenuItems] = useState([]);
    const [filteredItems, setFilteredItems] = useState([]);
    const [categories, setCategories] = useState([]);



    useEffect(() => {
        async function fetchData() {
            try {
                const response = await fetchItems();
                const allCategories = ['all', ...new Set(response.map((item) => item.category))];
                let newMenuItems = [];
                for (let i = 0; i < response.length; i++) {
                    newMenuItems.push({
                        id: response[i].id,
                        name: response[i].name,
                        price: response[i].price,
                        description: response[i].description,
                        image: await GetImage(response[i].imageId),
                        category: response[i].category,
                        available : response[i].available
                    });
                }
                setMenuItems(newMenuItems);
                setCategories(allCategories);
            } catch (error) {
                console.error("Error fetching data:", error);
            }
        }
        fetchData();
    }, []);

    const filterItems = (category) => {
        if (category === 'all') {
            setFilteredItems(menuItems);
        } else {
            const newItems = menuItems.filter((item) => item.category === category);
            setFilteredItems(newItems);
        }
    };

    return (
        <div className="page-container">
            <Navbar buttons={tabs}/>
            <Categories filterItems={filterItems} categories={categories}/>
            <Menu items={filteredItems}/>
        </div>
    );
}

export default MenuPage;
