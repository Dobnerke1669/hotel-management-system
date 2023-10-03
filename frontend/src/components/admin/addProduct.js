import React, {useEffect, useState} from "react";
import {AddNewProduct} from "../fetches/adminFetch";
import Navbar from "../navbar/NavBar";


export const AddProduct = () => {
    const [name, setName] = useState("");
    const [category, setCategory] = useState("");
    const [price, setPrice] = useState("");
    const [description, setDescription] = useState("");
    const [image, setSelectedFile] = useState(null);
    const [popupStyle2, showPopup2] = useState("hide");
    const [popupStyle, showPopup] = useState("hide");
    const [responseStatus, setResponseStatus] = useState(0);
    const [content, setContent] = useState("");


    const popup = () => {
        showPopup("add-popup");
        setTimeout(() => showPopup("hide"), 3000);
    };

    const popup2 = () => {
        showPopup2("add-popup2");
        setTimeout(() => showPopup2("hide"), 3000);
    };

    const handleFileChange = (event) => {
        const file = event.target.files[0];

        if (file) {
            const reader = new FileReader();

            reader.onload = (e) => {
                const imageData = Array.from(new Uint8Array(e.target.result));
                const name = file.name;
                const type = file.type;
                const imageToUpload = {name, type, imageData};
                setSelectedFile(imageToUpload);
            };

            reader.readAsArrayBuffer(file);
        }
    };

    const onNameChange = (event) => {
        setName(event.target.value);
    }

    const onCategoryChange = (event) => {
        setCategory(event.target.value);
    }

    const onPriceChange = (event) => {
        setPrice(event.target.value);
    }

    const onDescriptionChange = (event) => {
        setDescription(event.target.value);
    }

    const handleClick = (e) => {
        e.preventDefault();
        const product = {name, category, price, description, image};
        AddNewProduct(product).then((response) => {
            setContent(response.json());
            setResponseStatus(response.status);
        });
    }

    useEffect(() => {
        if ((responseStatus === 404 && content) || (responseStatus === 400 && content) || (responseStatus === 469 && content) || (responseStatus === 500 && content))
        {
            popup();
        }

        else if (responseStatus === 200 && content)
        {
            popup2();
        }

    }, [content, responseStatus]);

    const tabs = [
        {ref: "/", text: "Home"},
        {ref: "/addUser", text: "Add User"},
        {ref: "/disableUser", text: "Delete User"},
        {ref: "/updateUser", text: "Update User"},
        {ref: "/addProduct", text: "Add Product"},
        {ref: "/deleteProduct", text: "Delete Product"},
        {ref: "/updateProduct", text: "Update Product"}
    ]

    return (
        <div className="page-container">
            <Navbar buttons={tabs}/>
        <div className="cover">
            <h1>Add Product</h1>
            <input
                value={name}
                onChange={onNameChange}
                type="text"
                placeholder="name"
            />
            <input
                value={category}
                onChange={onCategoryChange}
                type="text"
                placeholder="category"
            />
            <input
                value={price}
                onChange={onPriceChange}
                type="text"
                placeholder="price"
            />
            <input
                value={description}
                onChange={onDescriptionChange}
                type="text"
                placeholder="description"
            />
            <input type="file" onChange={handleFileChange} />
            {image && (
                <div>
                    <h3>Selected File:</h3>
                    <p>Name: {image.name}</p>
                    <p>Type: {image.type}</p>
                </div>
            )}
            <button className="add-button" onClick={handleClick}>Add Product</button>
            <div className={popupStyle}>
                <h3>Error</h3>
                <p>This is an error</p>
            </div>
            <div className={popupStyle2}>
                <h3>Product Added</h3>
                <p>Product successfully added!</p>
            </div>
        </div>
        </div>
    )
}