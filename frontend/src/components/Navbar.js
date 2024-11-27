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
                {/* <Link to={'/'}><div className='menu-item'>Strona główna</div></Link> */}
                <Link to={'offers'}><div className='menu-item'>Przeglądaj oferty</div></Link>
                <Link to={'addOffer'}><div className='menu-item'>Dodaj ofertę</div></Link>
                <Link to={'account'}><div className='menu-item'>Konto</div></Link>
                {!authState ? <Link to={'login'}><div className='menu-item'>Zaloguj się</div></Link> : <Link><button onClick={logout}>Wyloguj się</button></Link> }
                {/* <Link to={'contact'}><div className='menu-item'>Kontakt</div></Link>  */}
            </div>
        </nav>
    )
}