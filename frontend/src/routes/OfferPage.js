import { useParams } from 'react-router-dom';
import React from 'react';
import './../styles/routes/offer-style.css';
import ImageSlider from '../components/ImageSlider';

export default function OfferPage() {
    const { id } = useParams();
    const [offerData, setOfferData] = React.useState({}); 
    const [images, setImages] = React.useState([]);

    React.useEffect(() => {
        fetch(`http://localhost:9090/bff/offers/${id}`)
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
        <div className="offerContainer">
            <ImageSlider images = {images}/>
            <div className='title'>{offerData.offerName}</div>
            <div className='price'>Cena całkowita: {offerData.fullPrice} pln / miesiąc</div>
            <div className='address'>{offerData.city}, {offerData.street}, {offerData.district}, {offerData.zip}</div>
            <div className='additionalInfo'>Rok budowy: {offerData.buildingYear}, Powierzchnia: {offerData.area}, Liczba pokoi: {offerData.roomsNumber},
            Piętro: {offerData.estateLevel}, Aneks kuchenny: {offerData.annexKitchen}, Winda: {offerData.elevator}, Zwierzęta mile widziane: {offerData.animals}</div>
        </div>
    )
}