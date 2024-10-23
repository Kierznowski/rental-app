import React from "react";
import './../styles/routes/offers-style.css';
import OfferShort from "../components/OfferShort";

export default function OfferSearch() {

    const [offers, setOffers] = React.useState([]);

    React.useEffect(() => {
        fetch('http://127.0.0.1:9090/bff/offers',
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
    
    const offersList = offers.map(offer => (
        <OfferShort
            key={offer.id}
            id={offer.id}
            title={offer.offerName}
            price={offer.fullPrice}
            area={offer.area}
            city={offer.city}
            street={offer.street}
        />
    ));

    return (
        <div className="offer-page">
            <div className="Form"></div>
            {offers.length > 0 ? offersList : <p>No offers available :(</p>}
        </div>
    )
}