import { useContext, useEffect, useState } from 'react';
import '../styles/components/side-panel.css';
import hidePanelIcon from '../assets/images/icons/hidepanel-icon.png';
import registerIcon from '../assets/images/icons/register-icon.png';
import loginIcon from '../assets/images/icons/login-icon.png';
import { Link } from 'react-router-dom';
import { AuthContext } from '../context/AuthContext';

import mockPhoto from '../assets/images/icons/profile-icon.png';
export default function SidePanel({ isOpen, togglePanel }) {
    
    
    const { authState } = useContext(AuthContext);
    const [userDetails, setUserDetails] = useState({firstName: "", lastName: ""});
    // const authState  = true; 

    useEffect(() => {
        if(authState) {
            fetch('http://127.0.0.1:9090/account/user-basic-info', {
                method: 'GET',
                credentials: 'include',
            })
            .then((response) => {
                if(!response.ok) {
                    throw new Error("Error during fetching user details.");
                }
                return response.json();
            })
            .then((data) => setUserDetails(data))
            .catch((err) => console.error(`Error during fetching user informations ${err}`));
        }
    }, [authState]);

    const loggedInContent = (
        <>
            <div className='sidePanel-profile-photo'>
                <img src={mockPhoto} alt='profile-photo' /> 
            </div>
            <div className='user-name'>{`${userDetails.firstName} ${userDetails.lastName}`}</div>
            <div className='sidePanel-menu'>
                <Link to={'/addOffer'}>
                    <div className='sidePanel-menu-option'>
                        Dodaj ofertę
                    </div>
                </Link>
                <Link to={'/'}>
                    <div className='sidePanel-menu-option'>
                        Twoje oferty
                    </div>
                </Link>
                <Link to={'/'}>
                    <div className='sidePanel-menu-option'>
                        Zapisane oferty
                    </div>
                </Link>
                <Link to={'/'}>
                    <div className='sidePanel-menu-option'>
                        Edytuj profil
                    </div>
                </Link>
                <Link to={'/'}>
                    <div className='sidePanel-menu-option'>
                        Ustawienia konta
                    </div>
                </Link>
                <div className='sidePanel-menu-option'>
                    Wyloguj
                </div>
            </div>
        </>
    );
    
    return (

        <div className={`side-panel ${isOpen ? "open" : ""}`}>

            <button className="close-btn" onClick={togglePanel}><img src={hidePanelIcon} alt='hide-panel-icon' /></button>
            <div className='sidepanel-content'>
                {authState ?
                <div className='logged-sidepanel'>
                    {loggedInContent}
                </div>
                :<div className='notlogged-sidepanel'>
                    <div className='sidepanel-login'>
                    Nie jesteś zalogowany.<br/>
                    Zaloguj się, aby wyświetlić szczegóły swojego konta:
                    <Link to='/login'><button className='sidepanel-login-btn'><img src={loginIcon} alt='login-icon' className='login-icon'/>Zaloguj się</button></Link>
                    </div>
                    <div className='sidepanel-register'>
                    Jeśli nie posiadasz jeszcze konta w naszym serwisie, <br/> założysz je tutaj:
                    <Link to={'/register'}><button className='sidepanel-register-btn'><img src={registerIcon} alt='register-icon' className='register-icon'/>Utwórz konto</button></Link>
                    </div>

                </div>}
            </div>
        </div>
    );
}