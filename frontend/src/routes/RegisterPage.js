import { useNavigate } from 'react-router-dom';
import '../styles/routes/register-page.css'

import photoFrame from '../assets/images/misc/register-photo-frame.png';
import addPhotoIcon from '../assets/images/icons/add-photo-black-icon.png';
import addToFavoriteIcon from '../assets/images/icons/favorite-add-icon.png';
import addOfferIcon from '../assets/images/icons/add-offer-icon.png';
import phoneIcon from '../assets/images/icons/phone-icon.png';
import searchOffersIcon from '../assets/images/icons/search-offers-icon.png';

import React from "react";


export default function RegisterPage() {

    const navigate = useNavigate();

    const [formData, setFormData] = React.useState({
        email: "",
        password: "",
        firstName: "",
        lastName: "",
        phone: "",
        userCity: "",
        userStreet: "",
        userZip: "",
    })

    function isValid() {
        return true;
    }


    async function handleSubmit(e) {
        e.preventDefault();
        
        if(!isValid()) {
            return;
        } 
        
        try {
            const res = await fetch("http://localhost:9090/bff/auth/register", {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData),
                credentials: 'include',
            })
            if(res.ok) {
                navigate('/account');
            }
        } catch (error) {
            console.error(`Error during registering new user: ${error}`);
        }
    }


    function handleChange(e) {
        const {name, value} = e.target;
        setFormData(prev => {
            return {
                ...prev,
                [name]: value
            }
        })
    }

    return (
        <div className='register-container'>
            <div className='register-form'>
                <pre><div className='registerpage-title'>       Utwórz nowe konto       </div></pre>
                <form onSubmit={handleSubmit}> 
                    
                    {/* Podaj swoje imię i nazwisko:                 */}
                    <input
                        type='text'
                        placeholder="imię"
                        onChange={handleChange}
                        value={formData.firstName}
                        name="firstName"
                        className='register-input'
                    />
                    
                    <input
                        type='text'
                        placeholder="nazwisko"
                        onChange={handleChange}
                        value={formData.lastName}
                        name="lastName"
                        className='register-input'
                    />
                  
                    <div className='register-data-type'>
                        Podaj swoje dane kontaktowe:
                    </div>  
                    
                    <input
                        type='text'
                        placeholder='adres e-mail'
                        onChange={handleChange}
                        value={formData.email}
                        name='email'
                        className='register-input'
                    />
                    <input
                        type='text'
                        placeholder="numer telefonu"
                        onChange={handleChange}
                        value={formData.phone}
                        name="phone"
                        className='register-input'
                    />
                    <input
                        type='text'
                        placeholder="miasto"
                        onChange={handleChange}
                        value={formData.userCity}
                        name="userCity"
                        className='register-input'
                    />
                    <input
                        type='text'
                        placeholder="adres zamieszkania"
                        onChange={handleChange}
                        value={formData.userStreet}
                        name="userStreet"
                        className='register-input'
                    />
                    
                    <input
                        type='text'
                        placeholder="kod pocztowy"
                        onChange={handleChange}
                        value={formData.userZip}
                        name="userZip"
                        className='register-input'
                    />
                    
                    <div className='register-data-type'>
                        Utwórz hasło:
                    </div>
                    
                    <input
                        type='password'
                        placeholder="hasło"
                        onChange={handleChange}
                        value={formData.password}
                        name="password"
                        className='register-input'
                    />

                    <input
                        type='password'
                        placeholder="powtórz hasło"
                        onChange={handleChange}
                        value={formData.confirm}
                        name="confirm"
                        className='register-input'
                    />

                    <button>Utwórz konto</button>
                </form>
            </div>
            <div className='register-sec-column'>
                <div className='photo-form'>
                    <img src={photoFrame} alt='photo-frame' className='profile-photo'/>
                    <img src={addPhotoIcon} alt='add-photo-icon' className='add-photo-icon'/>
                </div>
                <div className='register-info'>
                Nie masz jeszcze konta w naszym serwisie? <br/> Założysz je w mgnieniu oka, w kilku krokach!<br/>
                    Wystarczy, że wypełnisz formularz. <br/>Nie zapomnij wstawić zdjęcia w ramkę!
                    <br/>
                    <br/>
                    <div className='features-list'>
                        <img src={searchOffersIcon} alt='search-offers-icon' className='list-icon'/> wyszukuj atrakcyjne oferty <br />
                        <img src={addOfferIcon} alt='add-offer-icon' className='list-icon'/> zamieszczaj ogłoszenia <br />
                        <img src={addToFavoriteIcon} alt='add-to-favortie-icon' className='list-icon'/> dodawaj w ulubionych <br />
                        <img src={phoneIcon} alt='phone-icon' className='list-icon'/> kontaktuj się z zaintersowanymi <br />
                    </div>
                </div>
            </div>
        </div>
    )
}