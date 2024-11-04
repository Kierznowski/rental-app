import React from "react";

export default function AccountSecurity() {

    const [formData, setFormData] = React.useState({
        password: "",
        newPassword: "", 
    });

    const [warning, setWarning] = React.useState("");

    function handleChange(e) {
        const {name, value} = e.target;

        setFormData(prev => {
            return {
                prev,
                [name]: value,
            }
        })
    }

    async function handleSubmit(e) {
        e.preventDefault();

        if(!isValid()) {
            return;
        } 
        
        try {
            const res = await fetch("http://localhost:9090/user/{userId}", {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData),
                credentials: 'include',
            })
            if(res.ok) {
                console.log("Password updated");
            }
        } catch (error) {
            console.error(`Error during changing password: ${error}`);
        }
    }

    function isValid() {
        if(formData.newPassword !== formData.confirmPassword) {
            return false;
        }
        return true;
    }

    return (
        <div className="account-subpage-container">
            <h3>Zmień hasło</h3>
            <form onSubmit={handleSubmit}>
                <input
                    type="password"
                    placeholder="obecne hasło"
                    name="password"
                    value={formData.password}
                    onChange={handleChange}
                />
                <input
                    type="password"
                    placeholder="nowe hasło"
                    name="newPassword"
                    value={formData.newPassword}
                    onChange={handleChange}
                />
                <input
                    type="password"
                    placeholder="powtórz nowe hasło"
                    name="confirmPassword"
                    value={formData.confirmPassword}
                    onChange={handleChange}
                />
            </form>
            <button>Wprowadź zmiany</button>
        </div>
    )
}