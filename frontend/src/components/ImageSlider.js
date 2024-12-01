import React from "react";
import '../styles/components/image-slider.css';

export default function ImageSlider( {images}) {

    const [photoIndex, setPhotoIndex] = React.useState(0);
 
    function previousPhoto() {
        setPhotoIndex((prevIndex) => prevIndex === 0 ? images.length - 1 : prevIndex - 1);
    }

    function nextPhoto() {
        setPhotoIndex((prevIndex) => prevIndex === images.length - 1  ? 0 : prevIndex + 1);
    }

    return (
        <div className="slider-container">
            <button onClick={previousPhoto} className="prev-photo-btn">⇐</button>
            {images.length > 0 ?
            <img 
                key={photoIndex}
                src={images[photoIndex]} 
                alt={`appartment-photo-${photoIndex}`}   
                /> 
                : <p>Brak zdjęć</p>}
            <button onClick={nextPhoto} className="next-photo-btn">⇒</button>
            <div className="circle-indicator">
                {images.map((_, index) => (
                    <span
                        key={index}
                        className={`circle ${index === photoIndex ? 'active' : ''}`}
                        onClick={() => setPhotoIndex(index)}
                    />
                ))}
            </div>
        </div>
    )
}