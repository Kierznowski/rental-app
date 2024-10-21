import React from 'react';
import './../styles/routes/addOffer-style.css';
import { redirect, useNavigate } from 'react-router-dom';

export default function AddOffer() {

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

    const navigate = useNavigate();

    function handleChange(event) {
        const {name, value, type, checked} = event.target;
        setFormData(prev => {
            return {
                ...prev,
                [name]: type === "checkbox" ? checked : value
            }
        })
    }

    function handleSubmit(event) {
        event.preventDefault();
        fetch('http://localhost:9090/bff/offers', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json', 
            },
            body: JSON.stringify(formData),
            credentials: 'include'
        })
        .then(res => {
            if(res.ok) {
                navigate('/offers')
            } else {
                redirect('/login')
            }
        })
        .catch(error => console.log(`Error during adding offer: ${error}`));
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
        </div>
    )
}