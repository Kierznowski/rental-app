import EditAccountProfile from "../components/EditAccountProfile";
import AccountOffers from "../components/AccountOffers";
import AccountSecurity from "../components/AccountSecurity";

import React from "react";

import '../styles/routes/account-page.css';

export default function AccountPage() {
    
    const [option, setOption] = React.useState(0);


    return (    
        <div className='account-container'>
            <div className="account-menu">
                <button className="menu-item" onClick={() => (setOption(0))}>
                    Edytuj profil    
                </button>
                <button className="menu-item" onClick={() => (setOption(1))}>
                    Twoje oferty
                </button>
                <button className="menu-item" onClick={() => (setOption(2))}>
                    Ustawienia bezpieczeństwa
                </button>
            </div>

            <div className="account-page-content">
                {option === 0 ? <EditAccountProfile /> :
                option === 1 ? <AccountOffers /> :
                <AccountSecurity />}
            </div>
        </div>
    );
}
