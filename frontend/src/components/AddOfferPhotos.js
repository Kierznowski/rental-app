import React from "react";
import '../styles/components/add-offer-photos.css';

export default function AddOfferPhotos( {onImagesChange}) {

    const [files, setFiles] = React.useState([]);
    const [photoIndex, setPhotoIndex] = React.useState(0);
    const [numOfPhotos, setNumOfPhotos] = React.useState(0);

    function handleUploadingPhoto(e) {
        const newFiles = Array.from(e.target.files);
        setFiles(prevFiles => [...prevFiles, ...newFiles]);
        setNumOfPhotos(prev => prev + 1);
        setPhotoIndex(0);
        onImagesChange([...files, ...newFiles]);
    }

    function previousPhoto() {
        photoIndex === 0 ? setPhotoIndex(numOfPhotos - 1) : setPhotoIndex(photoIndex - 1);
    }

    function nextPhoto() {
        photoIndex === numOfPhotos - 1 ? setPhotoIndex(0) : setPhotoIndex(photoIndex + 1); 
    }

    function deletePhoto() {
        setFiles(prevFiles => {
            const newFiles = prevFiles.filter((_, index) => index !== photoIndex);
            
            if(photoIndex >= newFiles.length) {
                setPhotoIndex(Math.max(newFiles.length - 1, 0));
            }
            onImagesChange(newFiles);
            setNumOfPhotos(prev => Math.max(prev - 1, 0));
            return newFiles;
        });
    }
    
    return (
            <div className='offer-photos-form'>
                <label>Dodaj zdjęcia:</label>
                <input className='add-photo-button' type='file' onChange={handleUploadingPhoto} multiple />
                <div className='photo-display'>
                    <button onClick={previousPhoto} className="prev-button">⇐</button>
                    {numOfPhotos > 0 ? <>
                        <img 
                            key={photoIndex} 
                            src={URL.createObjectURL(files[photoIndex])} 
                            alt={`appartment-photo-${photoIndex}`}   
                        />
                        <button className='delete-photo' onClick={deletePhoto}>&#x2715;</button>
                        </> 
                        : <p>Brak zdjęć</p>}
                    <button onClick={nextPhoto} className="next-button">⇒</button>
                </div>

            </div>
        )
}