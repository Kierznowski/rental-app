import './../styles/routes/login-style.css'

export default function LoginPage() {

    async function login() {
       window.location.href = `http://localhost:9090/oauth2/code/authServer?redirect_uri=${encodeURIComponent(window.location.href)}`;
    }

    return (
        login()
    )   
}