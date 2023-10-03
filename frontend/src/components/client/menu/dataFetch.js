
export const items = [
    {
        id: 1,
        title: "Butter Chicken",
        category: "Indian",
        price: 15.99,
        image: "./images/item-1.jpeg",
        description: `o, butter masala, which uses a tomato paste.`,
    }
]
export const getHardcodedItems = () => {
    return [
        {
            id: 1,
            title: "Butter2 Chicken2",
            category: "Indian2",
            price: 152.99,
            image: "./images/item-1.jpeg",
            description: `o, butter232323232 masala, which uses a tomato paste.`,
        }
    ];
}
export const fetchItems = () => {
    const token = localStorage.getItem("token");
    return fetch (`http://localhost:8080/client/getProducts`, {
        method: "GET",
        headers: {"Content-Type": "application/json", Authorization: `Bearer ${token}`},
    }).then((response) => response.json());
}
export const categories2 = [
    "indian",
    "italian",
    "chinese"
]


