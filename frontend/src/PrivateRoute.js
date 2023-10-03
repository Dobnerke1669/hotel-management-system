import { Navigate, Outlet } from "react-router-dom";

export const PrivateRoute = ({
                                 isAuthenticated,
                                 redirectPath = "/login",
                                 children,
                             }) => {
    console.log(isAuthenticated);
    if (!isAuthenticated) {
        return <Navigate to={redirectPath} replace />;
    }

    return children ? children : <Outlet />;
};
