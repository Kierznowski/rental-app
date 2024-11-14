import React from 'react';
import './../styles/routes/addOffer-style.css';
import AddOfferForm from '../components/AddOfferForm';
import AddOfferPhotos from '../components/AddOfferPhotos';
import { useNavigate } from 'react-router-dom';

export default function AddOfferPage() {

    const navigate = useNavigate();

    const [ formData, setFormData ] = React.useState({
        offerName: "",
        city: "",
        street: "",
        district: "",
        zip: "",
        area: "",
        roomsNumber: "",
        estateLevel: "",
        buildingYear: "",
        garage: false,
        annexKitchen: false,
        elevator: false,
        animals: false 
    });
    const [files, setFiles] = React.useState([]);

    function handleFormChange(event) {
        const {name, value, type, checked} = event.target;
        setFormData(prev => {
            return {
                ...prev,
                [name]: type === "checkbox" ? checked : value
            }
        })
    }

    function handleImagesChange(newFiles) {
        setFiles(newFiles);
    }

    async function handleSubmit(event) {
        event.preventDefault();
        try {   
            const res = await fetch('http://localhost:9090/offers', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json', 
                },
                body: JSON.stringify(formData),
                credentials: 'include'
            })
            if(res.ok) {
                const data = await res.json();
                if(files.length > 0) {
                    await uploadImages(data);
                }
                navigate('/offers');
            }
        } catch (error) {
            console.error(`Error during adding offer: ${error}`);
        }
    }

    async function uploadImages(offerId) {
        const formData = new FormData();

        files.forEach((file) => {
            formData.append(`multipartImage`, file);
        });

        formData.append('offerId', offerId);
        console.log(formData);
        const res = await fetch(`http://localhost:9090/image`, {
            method: 'POST',
            body: formData,
            credentials: 'include'
        });

        return res.ok;
    }


    return (
        <div className="addOffer-container">
            <AddOfferForm 
                formData={formData}
                onFormChange={handleFormChange} 
                onFormSubmit={handleSubmit}    
            />
            <AddOfferPhotos onImagesChange={handleImagesChange} />
        </div>
    )
}