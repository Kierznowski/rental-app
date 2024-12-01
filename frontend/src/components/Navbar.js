import { useContext } from 'react';
import { Link } from 'react-router-dom';
import { AuthContext } from '../context/AuthContext';

import profileIcon from '../assets/images/icons/profile-icon.png';

export default function Navbar({ togglePanel }) {

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
            <div className="navbar">
                <div className="logo">
                    <Link to={'/'}>
                        CLEARENT <pre><span className="sublogo">        i wszystko jasne !                     </span></pre>
                    </Link>
                </div>
                
                <div className='menu'>
                    <div className='menu-item'><Link to={'offers'}>Przeglądaj oferty</Link></div>
                    <div className='account-button' onClick={togglePanel}>
                        Twoje konto
                        <img src={profileIcon} alt='profile-icon' className='profile-icon'/>
                    </div>
                </div>
            </div>
        </nav>
    )
}