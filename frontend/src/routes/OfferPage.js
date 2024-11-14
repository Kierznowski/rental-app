import { useParams } from 'react-router-dom';
import React from 'react';
import './../styles/routes/offer-style.css';
import ImageSlider from '../components/ImageSlider';

export default function OfferPage() {
    const { id } = useParams();
    const [offerData, setOfferData] = React.useState({}); 
    const [images, setImages] = React.useState([]);

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
        <div className="offerContainer">
            <div className='title'>{offerData.offerName}</div>
            <ImageSlider images = {images}/>
            <div className='price'>Cena całkowita: {offerData.fullPrice} pln / miesiąc</div>
            <div className='address'>Lokalizacja: {offerData.city}, {offerData.street}, {offerData.district}, {offerData.zip}</div>
            <div className='row1'>
                <div className='offer-details'>
                    <h3>Szczegółowe informacje:</h3>
                    <div className='info'>Cena podstawowa: {offerData.basePrice}</div>
                    <div className='info'>Opłaty dodatkowe (media, czynsz etc.): {offerData.additionalPrice}</div>
                    <div className='info'>Rok budowy: {offerData.buildingYear}</div> 
                    <div className='info'>Powierzchnia: {offerData.area} m<sup>2</sup></div>
                    <div className='info'>Liczba pokoi: {offerData.roomsNumber}</div>
                    <div className='info'>Piętro: {offerData.estateLevel}</div>
                    <div className='info'>Aneks kuchenny: {offerData.annexKitchen ? "Tak" : "Nie"}</div> 
                    <div className='info'>Winda: {offerData.elevator ? "Tak" : "Nie"}</div> 
                    <div className='info'>Zwierzęta mile widziane: {offerData.animals ? "Tak" : "Nie"}</div>
                </div>
                <div className='rentierDetails'>
                    <div>Imię wynajmującego: {offerData.user === null ? "Trzeba sprawdzić z logowaniem" : "Coś tam siedzi"}</div>
                    <div>Telefon wynajmującego: {offerData.user === null ? "Trzeba sprawdzić z logowaniem" : "Coś tam siedzi"}</div>
                </div>
            </div>
        </div>
    )
}