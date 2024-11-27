import { useParams, Link } from 'react-router-dom';
import React, { useContext } from 'react';
import './../styles/routes/offer-style.css';
import ImageSlider from '../components/ImageSlider';
import { AuthContext } from '../context/AuthContext';
import defaultProfilePic from '../assets/images/icons/default-profile-pic.png';

export default function OfferPage() {
    const { id } = useParams();
    const [offerData, setOfferData] = React.useState({}); 
    const [images, setImages] = React.useState([]);

    const { authState } = useContext(AuthContext); 

    React.useEffect(() => {
        fetch(`http://localhost:9090/offers/${id}`)
            .then(res => res.json())
            .then(data => setOfferData(data))
            .catch(error => console.log('Error during fetching offer data:', error));
    }, [id])

    React.useEffect(() => {
        if(offerData.imageIds && offerData.imageIds.length > 0) {
            const fetchImages = async () => {
                const imageUrls = await Promise.all(
                    offerData.imageIds.map(async (imageId) => {
                        const res = await fetch(`http://localhost:9090/image/${imageId}`,
                            {
                                method: 'GET',
                                credentials: 'include',
                            });
                            if(res.ok) {
                                const imageBlob = await res.blob();
                                const imageUrl = URL.createObjectURL(imageBlob);
                                return imageUrl;
                            }
                    })
                );
                setImages(imageUrls);
            };
            fetchImages();
        }
    }, [offerData]);


    return(
        <div className='main-offer-container'>
            <div className='title'>{offerData.offerName}</div>
            <div className="offerContainer">
                <ImageSlider images = {images}/>
                <div className='price-info'><h3>Cena całkowita:</h3> 
                    <div className='offer-info'>
                        {offerData.fullPrice} pln / miesiąc
                    </div>
                </div>
                <div className='address-info'>
                    <h3>Lokalizacja:</h3> 
                    <div className='offer-info'>
                        {offerData.city}, {offerData.street}, {offerData.district}, {offerData.zip}
                    </div>
                </div>
                <div className='row1'>
                    <div className='offer-details'>
                        <h3>Szczegółowe informacje:</h3>
                        <div className='info'>Cena podstawowa: {offerData.basePrice} pln</div>
                        <div className='info'>Opłaty dodatkowe (media, czynsz etc.): {offerData.additionalPrice} pln</div>
                        <div className='info'>Rok budowy: {offerData.buildingYear}</div> 
                        <div className='info'>Powierzchnia: {offerData.area} m<sup>2</sup></div>
                        <div className='info'>Liczba pokoi: {offerData.roomsNumber}</div>
                        <div className='info'>Piętro: {offerData.estateLevel}</div>
                        <div className='info'>Aneks kuchenny: {offerData.annexKitchen ? "Tak" : "Nie"}</div> 
                        <div className='info'>Winda: {offerData.elevator ? "Tak" : "Nie"}</div> 
                        <div className='info'>Zwierzęta mile widziane: {offerData.animals ? "Tak" : "Nie"}</div>
                    </div>
                    <div className='rentier-details'>
                        <h3>Wynajmujący:</h3>
                            <div className='rentier-row1'>
                                <div className='profile-pic'>
                                    <img src={defaultProfilePic} alt='profile-picture'/>
                                </div>
                                <div className='rentier-info'>
                                    {offerData.user === null ? 'Stefan' : null}
                                </div>
                            </div>
                            <div className='rentier-info'>  
                                { authState === true ? 
                                'Telefon: 123 456 789' : 
                                <Link to={'/login'}><button onClick>Zaloguj się aby zobaczyć numer</button></Link>}
                            </div>
                    </div>
                </div>
            </div>
        </div>
    )
}