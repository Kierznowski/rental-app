import { useContext } from "react"
import { AuthContext } from "./AuthContext"
import { Navigate } from "react-router-dom";

export default function ProtectedRoute({children}) {
    
    const { authState } = useContext(AuthContext);

    if(!authState) {
        return (
            <Navigate to="/login" />
        )
    }

    return children;
}

