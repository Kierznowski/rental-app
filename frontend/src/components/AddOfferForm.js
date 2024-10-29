export default function AddOfferForm( {formData, onFormChange, onFormSubmit} ) {
    return (
            <form onSubmit={onFormSubmit}>
                Wprowadź tytuł ogłoszenia:
                <input 
                    type="text" 
                    placeholder="Tytuł ogłoszenia" 
                    onChange={onFormChange}
                    value={formData.offerName}
                    name='offerName'
                />
                Podaj adres mieszkania:
                <input 
                    type="text" 
                    placeholder="Miasto" 
                    onChange={onFormChange}
                    value={formData.city}
                    name='city'
                />
                <input 
                    type="text"
                    placeholder="Ulica" 
                    onChange={onFormChange}
                    value={formData.street}
                    name='street'
                />
                <input 
                    type="text" 
                    placeholder="Dzielnica" 
                    onChange={onFormChange}
                    value={formData.district}
                    name='district'
                />
                <input 
                    type="text"
                    placeholder="Kod pocztowy" 
                    onChange={onFormChange}
                    value={formData.zip}
                    name='zip'
                />
                Podaj dodatkowe informacje o mieszkaniu:
                <input 
                    type="number" 
                    placeholder="Powierzchnia" 
                    onChange={onFormChange}
                    value={formData.area}
                    name='area'
                />
                <input 
                    type="number" 
                    placeholder="Ilość pokoi" 
                    onChange={onFormChange}
                    value={formData.roomsNumber}
                    name='roomsNumber'
                />
                <input 
                    type="number" 
                    placeholder="Piętro" 
                    onChange={onFormChange}
                    value={formData.estateLevel}
                    name='estateLevel' 
                />
                <input 
                    type="number" 
                    placeholder="Rok budowy" 
                    onChange={onFormChange}
                    value={formData.buildingYear}
                    name='buildingYear'
                />
                <label htmlFor="garage">Miejsce postojowe / garaż: </label>
                <input 
                    type='checkbox'
                    id='garage'
                    checked={formData.garage}
                    name="garage"
                    onChange={onFormChange}
                />

                <label htmlFor="annexKitchen">Aneks kuchenny: </label>
                <input 
                    type='checkbox'
                    id='annexKitchen'
                    checked={formData.annexKitchen}
                    name="annexKitchen"
                    onChange={onFormChange}
                />

                <label htmlFor="elevator">Winda: </label>
                <input 
                    type='checkbox'
                    id='elevator'
                    checked={formData.elevator}
                    name="elevator"
                    onChange={onFormChange}
                />
                
                <label htmlFor="animals">Zwierzęta dozwolone: </label>
                <input 
                    type='checkbox'
                    id='animals'
                    checked={formData.animals}
                    name="animals"
                    onChange={onFormChange}
                />
                <button>Dodaj ogłoszenie</button>
            </form>
    )
}