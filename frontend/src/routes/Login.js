import './../styles/routes/login-style.css'

export default function Login() {

    function login() {
        window.location.href = "http://localhost:9090/oauth2/code/authServer"
    }
    return (
        login()
    )   
}