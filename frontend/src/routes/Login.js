import React from "react";
import './../styles/routes/login-style.css'

export default function Login() {

    const [formData, setFormData] = React.useState([]);

    function handleSubmit(event) {
        event.preventDefault();
        fetch('http://localhost:9090/bff/login', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json', 
            },
            body: JSON.stringify(formData),
            credentials: "include"
        })
        .then(res => {
            if(res.ok) {
                navigator('/offers');
            } else {
                throw new Error('Failed to submit offer');
            }
        })
        .catch(error => console.log(`Error during logging: ${error}`));
    }

    function handleChange(event) {
        const {name, value} = event.target;
        setFormData(prev => {
            return {
                ...prev,
                [name]: value
            }
        })
    }
        function login() {
            window.location.href = "http://localhost:9090/oauth2/code/authServer"
        }
    return (
        login()
        // <div className="login-page">
        //     <form onSubmit={handleSubmit}>
        //         Zaloguj się
        //         <input 
        //             type="text" 
        //             placeholder="email" 
        //             onChange={handleChange}
        //             value={formData.email}
        //             name='email'
        //         />
        //         <input 
        //             type="text" 
        //             placeholder="password" 
        //             onChange={handleChange}
        //             value={formData.password}
        //             name='password'
        //         />
        //         <button>Zaloguj</button>
        //     </form>
        // </div>
    )   
}