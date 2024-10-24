import React from "react";
import SearchForm from "../components/SearchForm";
import SearchedOffers from "../components/SearchedOffers";

import '../styles/routes/offer-search.css';

export default function OfferSearchPage() {

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

    const handleSearchResult = (searchResult) => {
        setOffers(searchResult);
    };

    return (
        <div className="offer-page">
            <SearchForm onSearch={handleSearchResult} />
            <SearchedOffers offers={offers} />
        </div>
    )
}