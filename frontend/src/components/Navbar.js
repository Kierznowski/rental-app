import { useContext } from 'react';
import { Link } from 'react-router-dom';
import { AuthContext } from '../context/AuthContext';

export default function Navbar() {

    const { authState, checkAuthStatus } = useContext(AuthContext); 


    const logout = async () => {
        console.log('logout clicked');
        try {
            const response = await fetch('http://localhost:9090/logout', {
                method: 'POST',
                credentials: "include"
            });

            if(response.ok) {
                await checkAuthStatus();
                window.location.href = '/';
            } else {
                console.error('Failed to logout');
            }
        } catch (error) {
            console.error('Error during logout:', error);
        }
    };

    return (
        <nav>
            <Link to={'/'}><div className="logo">CLEARENT<span className="sublogo"> i wszystko jasne</span></div></Link>
            <div className="menu">
                <Link to={'/'}><button>Strona główna</button></Link>
                <Link to={'offers'}><button>Przeglądaj oferty</button></Link>
                <Link to={'addOffer'}><button>Dodaj ofertę</button></Link>
                <Link to={'account'}><button>Konto</button></Link>
                {!authState ? <Link to={'login'}><button>Zaloguj się</button></Link> : <Link><button onClick={logout}>Wyloguj się</button></Link> }
                <Link to={'contact'}><button>Kontakt</button></Link> 
            </div>
        </nav>
    )
}