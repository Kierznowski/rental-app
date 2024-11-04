import React from "react";

import OfferShort from '../components/OfferShort';

export default function AccountOffers() {

    const [offers, setOffers] = React.useState([]);
    const id = 1;   //to do

    React.useEffect(() => {
        fetch(`http://127.0.0.1:9090/bff/account/${id}/offers`,
            {
                method: 'GET',
                credentials: 'include',
            }
        )
            .then(res => res.json())
            .then(data => {
                setOffers(data)
            })
            .catch(error => console.error('Error during fetching offers:', error));
        }, []);

    return (
        <div className="account-subpage-container">
            <h3>Twoje oferty</h3>
            <div className="offer-short">
                <OfferShort />
                <button className="edit-offer-button">edytuj</button>
            </div>
        </div>
    )
}