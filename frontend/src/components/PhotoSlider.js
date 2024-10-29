import React from "react";
import '../styles/components/photo-slider.css';

export default function PhotoSlider() {

    const [files, setFiles] = React.useState([]);
    const [photoIndex, setPhotoIndex] = React.useState(0);
    const [numOfPhotos, setNumOfPhotos] = React.useState(0);

    function handleUploadingPhoto(e) {
        const newFile = URL.createObjectURL(e.target.files[0]);
        setFiles(prevFiles => [...prevFiles, newFile]);
        setNumOfPhotos(prev => prev + 1);
        setPhotoIndex(0);
    }

    function previousPhoto() {
        if(photoIndex === 0) {
            setPhotoIndex(numOfPhotos - 1);
        } else {
            setPhotoIndex(photoIndex - 1);
        }
    }

    function nextPhoto() {
        if(photoIndex === numOfPhotos - 1) {
            setPhotoIndex(0);
        } else {
            setPhotoIndex(photoIndex + 1);
        }
    }

    function deletePhoto() {
        setFiles(prevFiles => {
            const newFiles = prevFiles.filter((_, index) => index !== photoIndex);
            
            if(photoIndex >= newFiles.length) {
                setPhotoIndex(Math.max(newFiles.length - 1, 0));
            }
            return newFiles;
        });
            setNumOfPhotos(prev => Math.max(prev - 1, 0));
    }
    
    return (
        <div className='offer-photos-form'>
                    <label>Dodaj zdjęcia:</label>
                    <input className='add-photo-button' type='file' onChange={handleUploadingPhoto} multiple />
                    <div className='photo-display'>
                        <button onClick={previousPhoto} className="prev-button">⇐</button>
                        {numOfPhotos > 0 ? <>
                        <img key={photoIndex} src={files[photoIndex]} alt={`appartment-photo-${photoIndex}`} />
                        <button className='delete-photo' onClick={deletePhoto}>&#x2715;</button>
                        </> : <p>Brak zdjęć</p>}
                        <button onClick={nextPhoto} className="next-button">⇒</button>
                    </div>

                </div>
        )
}