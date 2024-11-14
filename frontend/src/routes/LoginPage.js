import { useState } from 'react'
import './../styles/routes/login-style.css'

export default function LoginPage() {

    const [loginData, setLoginData] = useState( {
        email: "",
        password: "",
    })


    async function login() {
       window.location.href = `http://localhost:9090/oauth2/code/authServer?redirect_uri=${encodeURIComponent(window.location.href)}`;
        // try {
        //     const res = await fetch('http://localhost:9090/bff/auth/login', {
        //         method: 'POST',
        //         body: loginData,
        //         credentials: 'include'
        //     });
        //     return res.ok;
        // } catch (error) {
        //     console.log('Error during login ' + error);
        // }
    }

    // function handleChange(event) {
    //     const {name, value} = event.target;
    //     setLoginData(prev => {
    //         return {
    //             ...prev,
    //             [name]: value,
    //         }
    //     })

    // }

    return (
        login()
        // <div className='login-container'>
        //     <form onSubmit={login}>
        //         <input
        //             type='text' 
        //             placeholder='Adres e-mail'
        //             value={loginData.email}
        //             name='email'
        //             onChange={handleChange}
        //         />
        //         <input
        //             type='password' 
        //             placeholder='Hasło'
        //             value={loginData.password}
        //             name='password'
        //             onChange={handleChange}
        //         />
        //         <button>Zaloguj</button>
        //     </form>
        // </div>
    )   
}