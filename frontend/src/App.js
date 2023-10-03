import "./App.css";
import RegisterForm from "./components/register/registerform.js";
import LoginForm from "./components/login/loginform.js";
import {Routes, Route} from "react-router-dom";
import {Admin} from "./components/admin/admin.js";
import { PrivateRoute } from "./PrivateRoute";
import { useState } from "react";
import AddUser from "./components/admin/addUser.js";
import DisableUser from "./components/admin/disableUser";
import UpdateUser from "./components/admin/updateUser";
import Index from "./components";
import {Receptionist} from "./components/receptionist/receptionist";
import AddReservation from "./components/receptionist/reservation/addReservation";
import AddRoom from "./components/receptionist/rooms/addRoom";
import UpdateRooms from "./components/receptionist/rooms/updateRoom";
import DeleteRoom from "./components/receptionist/rooms/deleteRoom";
import UpdateReservation from "./components/receptionist/reservation/updateReservation";
import DeleteReservations from "./components/receptionist/reservation/deleteReservation";
import AddReservationClient from "./components/client/reservations/addReservationClient";
import {Client} from "./components/client/client";
import DeleteReservationClient from "./components/client/reservations/deleteReservationClient";
import {Cleaning} from "./components/cleaning/cleaning";
import {AddCleaningTicket} from "./components/client/addCleaningTicket";
import MenuPage from "./components/client/menu/menuPage";
import {Basket} from "./components/client/menu/basket";
import {AddProduct} from "./components/admin/addProduct";
import {UpdateProduct} from "./components/admin/updateProduct";
import {DeleteProduct} from "./components/admin/deleteProduct";
import {KitchenPage} from "./components/kitchen/kitchen";
import CareHelp from "./components/footer/careHelp";
import Terms from "./components/footer/terms";

function App() {
    const user = JSON.parse(localStorage.getItem("user"));
    const [isLoggedIn, setIsLoggedIn] = useState(!!user);

    const handleChangeLoggedInStatus = (isLoggedInValue) => {
        setIsLoggedIn(isLoggedInValue);
    };

    return (
        <div className="page">
                <Routes>
                    <Route path="/" exact element={<Index/>} />
                    <Route path="/careHelp" element={<CareHelp />}/>
                    <Route path="/terms" element={<Terms />}/>
                    <Route
                        path="/login"
                        exact
                        element={
                            <LoginForm onChangeLoggedInStatus={handleChangeLoggedInStatus} />
                        }
                    />
                    <Route path="/register" element={<RegisterForm />} />

                    <Route element={<PrivateRoute isAuthenticated={isLoggedIn} />}>
                        <Route path="/admin" element={<Admin />} />
                        <Route path="/disableUser" element={<DisableUser />} />
                        <Route path="/updateUser" element={<UpdateUser />} />
                        <Route path="/addUser" element={<AddUser />} />

                        <Route path="/addProduct" element={<AddProduct />} />
                        <Route path="/updateProduct" element={<UpdateProduct />} />
                        <Route path="/deleteProduct" element={<DeleteProduct />} />
                    </Route>

                    <Route element={<PrivateRoute isAuthenticated={isLoggedIn} />}>
                        <Route path="/receptionist" element={<Receptionist />} />

                        <Route path="/addRoom" element={<AddRoom />} />
                        <Route path="/updateRoom" element={<UpdateRooms />} />
                        <Route path="/deleteRoom" element={<DeleteRoom />} />

                        <Route path="/addReservation" element={<AddReservation />} />
                        <Route path="/updateReservation" element={<UpdateReservation />} />
                        <Route path="/deleteReservation" element={<DeleteReservations />} />
                    </Route>

                    <Route element={<PrivateRoute isAuthenticated={isLoggedIn} />}>
                        <Route path="/client" element={<Client />} />
                        <Route path="/client/addReservation" element={<AddReservationClient />} />
                        <Route path="/client/deleteReservation" element={<DeleteReservationClient />} />

                        <Route path="/client/addCleaningTicket" element={<AddCleaningTicket />} />

                        <Route path="/client/menuPage" element={<MenuPage/>} />
                        <Route path="/client/basket" element={<Basket/>} />
                    </Route>
                    <Route element={<PrivateRoute isAuthenticated={isLoggedIn} />}>
                        <Route path="/cleaning" element={<Cleaning/>} />
                    </Route>
                    <Route element={<PrivateRoute isAuthenticated={isLoggedIn} />}>
                        <Route path="/kitchen" element={<KitchenPage/>} />
                    </Route>
                </Routes>
        </div>
    );
}

export default App;
