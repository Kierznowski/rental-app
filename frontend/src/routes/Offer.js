import { useParams } from 'react-router-dom';
import React from 'react';
import './../styles/routes/offer-style.css';
import Appartment from './../assets/images/appartment1.jpg';

export default function Offer() {
    const { id } = useParams();
    const [offerData, setOfferData] = React.useState({}); 

    React.useEffect(() => {
        fetch(`http://localhost:9090/bff/offers/${id}`)
            .then(res => res.json())
            .then(data => setOfferData(data))
            .catch(error => console.log('Error during fetching offer data:', error));
    }, [id]);

    return(
        <div className="offerContainer">
            <img src={Appartment} alt='appartment' className='appartmentImage'/> 
            <div className='title'>{offerData.offerName}</div>
            <div className='price'>Cena całkowita: {offerData.fullPrice} pln / miesiąc</div>
            <div className='address'>{offerData.city}, {offerData.street}, {offerData.district}, {offerData.zip}</div>
            <div className='additionalInfo'>Rok budowy: {offerData.buildingYear}, Powierzchnia: {offerData.area}, Liczba pokoi: {offerData.roomsNumber},
            Piętro: {offerData.estateLevel}, Aneks kuchenny: {offerData.annexKitchen}, Winda: {offerData.elevator}, Zwierzęta mile widziane: {offerData.animals}</div>
        </div>
    )
}