import React from "react";
import '../styles/components/add-offer-photos.css';

export default function AddOfferPhotos( {onImagesChange}) {

    const [files, setFiles] = React.useState([]);
    const [photoIndex, setPhotoIndex] = React.useState(0);

    function handleUploadingPhoto(e) {
        const newFiles = Array.from(e.target.files);
        
        setFiles((prevFiles) => {
            const updatedFiles = [...prevFiles, ...newFiles];
            setPhotoIndex(updatedFiles.length - 1);
            onImagesChange(updatedFiles);
            return updatedFiles;
        });
    }

    function previousPhoto() {
        setPhotoIndex((prevIndex) => prevIndex === 0 ? files.length - 1 : prevIndex - 1);
    }

    function nextPhoto() {
        setPhotoIndex((prevIndex) => prevIndex === files.length - 1  ? 0 : prevIndex + 1);
    }

    function deletePhoto() {
        setFiles((prevFiles) => {
            const updatedFiles = prevFiles.filter((_, index) => index !== photoIndex);
            
            setPhotoIndex((prevIndex) => Math.max(updatedFiles.length - 1, 0));
            onImagesChange(updatedFiles);
            
            return updatedFiles;
        });
    }
    
    return (
            <div className='addofferphotos-form'>
                <label>Dodaj zdjęcia:</label>
                <input 
                    className='add-photo-btn' 
                    type='file' 
                    onChange={handleUploadingPhoto} 
                    multiple     
                />
                <div className='photo-display'>
                    <button onClick={previousPhoto} className="prev-photo-btn">⇐</button>
                    {files.length > 0 ? 
                        <>
                            <img 
                                key={photoIndex} 
                                src={URL.createObjectURL(files[photoIndex])} 
                                alt={`appartment-photo-${photoIndex}`} />
                            <button className='delete-photo-btn' onClick={deletePhoto}>&#x2715;</button>
                        </> 
                        : <p>Brak zdjęć</p>}
                    <button onClick={nextPhoto} className="next-photo-btn">⇒</button>
                    <div className="circle-indicator">
                        {files.map((_, index) => (
                        <span
                            key={index}
                            className={`circle ${index === photoIndex ? 'active' : ''}`}
                            onClick={() => setPhotoIndex(index)}
                        />
                        ))}
                    </div>
                </div>
            </div>
        )
}