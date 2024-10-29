import React from 'react';
import './../styles/routes/addOffer-style.css';
import PhotoSlider from '../components/PhotoSlider';

export default function AddOfferPage() {

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

    function handleFilesChange(newFiles) {
        setFiles(newFiles);
    }


    function handleChange(event) {
        const {name, value, type, checked} = event.target;
        setFormData(prev => {
            return {
                ...prev,
                [name]: type === "checkbox" ? checked : value
            }
        })
    }

    async function handleSubmit(event) {
        event.preventDefault();
        try {   
            const res = await fetch('http://localhost:9090/bff/offers', {
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
                    const uploadSuccess = await uploadImages(data);
                    if(!uploadSuccess) {
                        console.log("BŁąd przesyłania obrazów");
                    }
                }
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

        const res = await fetch(`http://localhost:8080/file-system/image`, {
            method: 'POST',
            body: formData,
            credentials: 'include'
        });
        return res.ok;
    }


    return (
        <div className="addOffer-container">
            <form onSubmit={handleSubmit}>
                Wprowadź tytuł ogłoszenia:
                <input 
                    type="text" 
                    placeholder="Tytuł ogłoszenia" 
                    onChange={handleChange}
                    value={formData.offerName}
                    name='offerName'
                />
                Podaj adres mieszkania:
                <input 
                    type="text" 
                    placeholder="Miasto" 
                    onChange={handleChange}
                    value={formData.city}
                    name='city'
                />
                <input 
                    type="text"
                    placeholder="Ulica" 
                    onChange={handleChange}
                    value={formData.street}
                    name='street'
                />
                <input 
                    type="text" 
                    placeholder="Dzielnica" 
                    onChange={handleChange}
                    value={formData.district}
                    name='district'
                />
                <input 
                    type="text"
                    placeholder="Kod pocztowy" 
                    onChange={handleChange}
                    value={formData.zip}
                    name='zip'
                />
                Podaj dodatkowe informacje o mieszkaniu:
                <input 
                    type="number" 
                    placeholder="Powierzchnia" 
                    onChange={handleChange}
                    value={formData.area}
                    name='area'
                />
                <input 
                    type="number" 
                    placeholder="Ilość pokoi" 
                    onChange={handleChange}
                    value={formData.roomsNumber}
                    name='roomsNumber'
                />
                <input 
                    type="number" 
                    placeholder="Piętro" 
                    onChange={handleChange}
                    value={formData.estateLevel}
                    name='estateLevel' 
                />
                <input 
                    type="number" 
                    placeholder="Rok budowy" 
                    onChange={handleChange}
                    value={formData.buildingYear}
                    name='buildingYear'
                />
                <label htmlFor="garage">Miejsce postojowe / garaż: </label>
                <input 
                    type='checkbox'
                    id='garage'
                    checked={formData.garage}
                    name="garage"
                    onChange={handleChange}
                />

                <label htmlFor="annexKitchen">Aneks kuchenny: </label>
                <input 
                    type='checkbox'
                    id='annexKitchen'
                    checked={formData.annexKitchen}
                    name="annexKitchen"
                    onChange={handleChange}
                />

                <label htmlFor="elevator">Winda: </label>
                <input 
                    type='checkbox'
                    id='elevator'
                    checked={formData.elevator}
                    name="elevator"
                    onChange={handleChange}
                />
                
                <label htmlFor="animals">Zwierzęta dozwolone: </label>
                <input 
                    type='checkbox'
                    id='animals'
                    checked={formData.animals}
                    name="animals"
                    onChange={handleChange}
                />
                <button>Dodaj ogłoszenie</button>
            </form>
            <PhotoSlider onFilesChange={handleFilesChange} />
        </div>
    )
}