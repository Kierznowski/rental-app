import OfferShort from "./OfferShort";
import '../styles/components/searched-offers.css';

export default function SearchedOffers({ offers }) {
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
            <div className="offers-list">
                {offers.length > 0 ? offersList : <p>No offers available with this parameters</p>}
            </div>
        </div>
    )

}