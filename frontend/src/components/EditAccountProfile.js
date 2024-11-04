import React from "react"

export default function EditAccountProfile() {

    const [formData, setFormData] = React.useState({
        email: "",
        firstName: "",
        lastName: "",
        phone: "",
        userCity: "",
        userStreet: "",
        userZip: ""
    });

    function handleChange(e) {
        e.preventDefault();

        const {name, value} = e.target;

        setFormData(prev => {
            return {
                ...prev,
                [name]: value,
            }
        })
    }

    function handleSubmit(e) {

    }

    return (
        <div className="account-subpage-container">
            <div className="edit-profile-image">
                <h3>Edytuj zdjęcie profilowe</h3>
                <div className="profile-img">
                    
                </div>
            </div>
            <div className="edit-contact-data">
                <h3>Edytuj dane kontaktowe</h3>
                <form onSubmit={handleSubmit}>
                Edytuj swoje imię i nazwisko:                
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
                Edytuj numer telefonu:
                <input
                    type='text'
                    placeholder="numer telefonu"
                    onChange={handleChange}
                    value={formData.phone}
                    name="phone"
                />
            </form>
        </div>
        <div className="edit-address-data">
            <h3>Edytuj dane adresowe</h3>
            <form onSubmit={handleSubmit}>
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
            </form>

            <button>Zapisz zmiany</button>
            </div>
        </div>
    )
}