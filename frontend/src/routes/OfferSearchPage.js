import React, { useEffect, useState} from "react";
import { useLocation } from "react-router-dom";
import SearchForm from "../components/SearchForm";
import SearchedOffers from "../components/SearchedOffers";

import '../styles/routes/offer-search-page.css';

export default function OfferSearchPage() {

    const [offers, setOffers] = useState([]);
    const location = useLocation();

    const getInitialCriteria = () => {
        const searchParams = new URLSearchParams(location.search);
        return {
            city: searchParams.get("city") || "",
            minPrice: searchParams.get("minPrice") || "",
            maxPrice: searchParams.get("maxPrice") || "",
            minArea: searchParams.get("minArea") || "",
            maxArea: searchParams.get("maxArea") || "",
            maxRoomsNumber: searchParams.get("maxRoomsNumber") || "",
            minRoomsNumber: searchParams.get("minRoomsNumber") || "",
            district: searchParams.get("district") || "",
            minEstateLevel: searchParams.get("minEstateLevel") || "",
            maxEstateLevel: searchParams.get("maxEstateLevel") || "",
            garage: searchParams.get("garage") === "true",
            animals: searchParams.get("animals") === "true",
            annexKitchen: searchParams.get("annexKitchen") === "true",
            elevator: searchParams.get("elevator") === "true",
        };
    };

    const [searchCriteria, setSearchCriteria] = useState(getInitialCriteria);

    useEffect(() => {
        const isCriteriaEmpty = Object.values(searchCriteria).every(
            (value) => value === "" || value === false
        );
        if(isCriteriaEmpty) {
            fetch(`http://127.0.0.1:9090/offers`, {
                method: "GET",
                credentials: "include",
            })
                .then((res) => res.json())
                .then((data) => setOffers(data))
                .catch((error) =>
                    console.error("Error during fetching recent offers:", error)
                );
        } else {
            fetch(`http://127.0.0.1:9090/offers/searched-offers`, {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(searchCriteria),
                credentials: "include"
            })
                .then(res => res.json())
                .then(data => setOffers(data))
                .catch(error => console.error("Error during fetching offers:", error));
        }
    }, [searchCriteria]);
            
    const handleSearchResult = (criteria) => {
        setSearchCriteria(criteria);
    };

    return (
        <div className="offersearch-main">
            <div className="offer-page">
                <SearchForm onSearch={handleSearchResult} initialCriteria={searchCriteria} />
                <SearchedOffers offers={offers} />
            </div>
        </div>
    )
}