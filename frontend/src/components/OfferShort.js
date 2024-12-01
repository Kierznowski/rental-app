import { Link } from 'react-router-dom';
import {useState, useEffect } from 'react';

import './../styles/components/offer-short.css';

export default function OfferShort(props) {

    const [imageSrc, setImageSrc] = useState("");

    useEffect(() => {
        const fetchImage = async () => {
            try {
                const res = await fetch(`http://localhost:9090/image/${props.imageId}`, {
                    method: 'GET',
                    credentials: 'include'
                });

                if(res.ok) {
                    const imageBlob = await res.blob();
                    const imageUrl = URL.createObjectURL(imageBlob);
                    setImageSrc(imageUrl);
                } else {
                    console.error(`Error: ${res.status}`);
                }
            } catch (error) {
                console.error(`Error during fetching offer image: ${error}`);
            }
        };
        fetchImage();
    }, [props.imageId]);
    
    return (
        <div className='offer-container'>
            <Link to={`./../offers/${props.id}`} target='_blank'>
                <img src={imageSrc} alt='appartment' className='offerImage'/>
            </Link>
            <div className='description'>
                <div className='title-price'>
                    <Link to={`./../offers/${props.id}`}>
                        <div className="offer-title">
                            {props.title}
                        </div>
                    </Link>
                    <div className="price">
                        {props.price} pln / msc
                    </div>
                </div>
                <div className="info">
                    Powierzchnia: {props.area} m2 | Miasto: {props.city} | Ulica: {props.street}
                </div>
            </div>
        </div>
    )
}