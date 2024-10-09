import React from "react";
import './../styles/routes/login-style.css'

export default function Login() {

    const [email, setEmail] = React.useState();
    const [password, setPassword] = React.useState();

    function handleSubmit(event) {
        event.preventDefault();
        loginUser(event.target.email.value, event.target.password.value);
    }

    function loginUser(email, password) {
        fetch('http://localhost:8080/login', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json', 
            },
            body: JSON.stringify({
                email: email,
                password: password,
            })
        })
        .then(res => {
            if(res.status === 200) {
                console.log("User logged in");
            } else {
                console.log("User not logged in");
            }
        })
        .catch(err => {
            console.error(err);
        })
    }

    return (
        <div className="login-page">
            <div className="login">
            <h3>Zaloguj się: </h3>
                <form onSubmit={handleSubmit}>
                    <input 
                        type="text" 
                        placeholder="Adres e-mail"
                        name="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)} 
                    />
                    <input 
                        type="password" 
                        placeholder="Hasło"
                        name="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}    
                    />
                    <button>Zaloguj</button>
                </form>
            </div>
            <div className="register">

            </div>REJESTRACJA
        </div>
    )
}