export const Categories = ({filterItems,categories}) => {
    return (
        <div className="container">
            <div className="row">
                <div className="col-8 mx-auto">
                    <div className="category-tabs">
                        {
                            categories.map((category) => {
                                return <button onClick={() => filterItems(category)}>{category}</button>

                            })
                        }
                    </div>
                </div>
            </div>
        </div>
    )
}

