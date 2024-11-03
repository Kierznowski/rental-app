import { useNavigate } from 'react-router-dom';
import '../styles/routes/register-page.css'

import React from "react";
import { useFormik } from 'formik';
import * as Yup from 'yup';

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
    //
    }


    async function handleSubmit(e) {
        e.preventDefault();
        
        if(!isValid()) {
            return;
        } 
        
        try {
            const res = await fetch("http://localhost:9090/register", {
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
        <h3>Utwórz konto</h3>
            <form onSubmit={handleSubmit}>
                Podaj e-mail i utwórz hasło:
                <input
                    type='text'
                    placeholder='adres e-mail'
                    onChange={handleChange}
                    value={formData.email}
                    name='email'
                />
                
                <input
                    type='password'
                    placeholder="hasło"
                    onChange={handleChange}
                    value={formData.password}
                    name="password"
                />

                <input
                    type='password'
                    placeholder="powtórz hasło"
                    onChange={handleChange}
                    value={formData.confirm}
                    name="confirm"
                />
                Podaj swoje imię i nazwisko:                
                <input
                    type='text'
                    placeholder="imię"
                    onChange={handleChange}
                    value={formData.firstName}
                    name="firstName"
                />
                
                <input
                    type='text'
                    placeholder="nazwisko"
                    onChange={handleChange}
                    value={formData.lastName}
                    name="lastName"
                />
                Podaj swoje dane kontaktowe:
                <input
                    type='text'
                    placeholder="numer telefonu"
                    onChange={handleChange}
                    value={formData.phone}
                    name="phone"
                />
                
                <input
                    type='text'
                    placeholder="miasto"
                    onChange={handleChange}
                    value={formData.userCity}
                    name="userCity"
                />
                
                <input
                    type='text'
                    placeholder="ulica"
                    onChange={handleChange}
                    value={formData.userStreet}
                    name="userStreet"
                />

                <input
                    type='text'
                    placeholder="kod pocztowy"
                    onChange={handleChange}
                    value={formData.userZip}
                    name="userZip"
                />

            <button>Utwórz konto</button>
            </form>
        </div>
    )
}