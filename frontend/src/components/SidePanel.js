import { useState } from 'react';
import '../styles/components/side-panel.css';
import hidePanelIcon from '../assets/images/icons/hidepanel-icon.png';
import registerIcon from '../assets/images/icons/register-icon.png';
import loginIcon from '../assets/images/icons/login-icon.png';
import { Link } from 'react-router-dom';


export default function SidePanel({ isOpen, togglePanel }) {
    
    const [isLogged, setIsLogged] = useState(false);
    
    
    return (

        <div className={`side-panel ${isOpen ? "open" : ""}`}>

            <button className="close-btn" onClick={togglePanel}><img src={hidePanelIcon} alt='hide-panel-icon' /></button>
            <div className='sidepanel-content'>
                {isLogged ?
                <div className='logged-sidepanel'>




                </div>
                :<div className='notlogged-sidepanel'>
                    <div className='sidepanel-login'>
                    Nie jesteś zalogowany.<br/>
                    Zaloguj się, aby wyświetlić szczegóły swojego konta:
                    <button className='sidepanel-login-btn'><img src={loginIcon} alt='login-icon' className='login-icon'/>Zaloguj się</button>
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