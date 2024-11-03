import './../styles/components/search-form.css';

import React from 'react';

export default function SearchForm({ onSearch }) {

    const [searchData, setSearchData] = React.useState({
        city: "",
        minPrice: "",
        maxPrice: "",
        minArea: "",
        maxArea: "",
        maxRoomsNumber: "",
        minRoomsNumber: "",
        district: "",
        minEstateLevel: "",
        maxEstateLevel: "",
        garage: "",
        animals: "",
        annexKitchen: "",
        elevator: "",
    });

    const [more, setMore] = React.useState(false);

    function handleChange(event) {
        const {name, value, type, checked} = event.target;
        setSearchData(prev => {
            return {
                ...prev,
                [name]: type === "checkbox" ? checked : value
            }
        })
    }

    function handleSubmit(event) {
        if(validate) {
            event.preventDefault();
            fetch('http://localhost:9090/bff/offers/search', {
                method: 'POST',
                headers: {
                    'Accept' : 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(searchData),
                credentials: 'include'
            })
            .then(res => res.json())
            .then(data => {
                onSearch(data);
            })
            .catch(err => console.log(`Error during searching offer: ${err}`));
        }
    }

    function validate() {
        return true;
    }

    return (
        <div className='search-container'>
        <form className='search-form' onSubmit={handleSubmit}>
            <div className='inputs'>
                <div className='category'>
                    <div className='category-name'>Lokalizacja:</div>
                    <input 
                        type='text' 
                        placeholder='miasto'
                        onChange={handleChange}    
                        value={searchData.city}
                        name='city'
                    />
                    {more ? 
                    <input
                        type='text'
                        placeholder='dzielnica'
                        onChange={handleChange}
                        value={searchData.district}
                        name='district'
                    /> : null}
                </div>
                <div className='category'>
                    <div className='category-name'>Cena za miesiąc:</div>                
                    <input  
                        type='number'
                        placeholder='cena od:'
                        onChange={handleChange}
                        value={searchData.minPrice}
                        name='minPrice'
                    />
                    <input 
                        type='number'
                        placeholder='cena do:'
                        onChange={handleChange}
                        value={searchData.maxPrice}
                        name='maxPrice'
                    />
                </div>
                <div className='category'>
                    <div className='category-name'>Powierzchnia:</div>
                    <input
                        type='number'
                        placeholder='powierzchnia od:'
                        onChange={handleChange}
                        value={searchData.minArea} 
                        name='minArea'
                    />
                    <input
                        type='number'
                        placeholder='powierzchnia do:'
                        onChange={handleChange}
                        value={searchData.maxArea} 
                        name='maxArea'
                    />
                </div>
                
            </div>
            
            <div className='submit-search'>
                <button>Szukaj</button>
            </div>
        </form>
        <div className={`additional-options ${more ? 'visible' : ''}`}>
            <form className='search-form'>
                <div className='inputs'>
                    <div className='category'>
                        <div className='category-name'>Ilość pokoi:</div>
                            <input
                                type='number'
                                placeholder='min. pokoi:'
                                onChange={handleChange}
                                value={searchData.minRoomsNumber} 
                                name='minRoomsNumber'
                            />
                            <input
                                type='number'
                                placeholder='max. pokoi:'
                                onChange={handleChange}
                                value={searchData.maxRoomsNumber} 
                                name='maxRoomsNumber'
                            />
                    </div>    
                    <div className='category'>
                        <div className='category-name'>Piętro:</div>
                            <input
                                type='number'
                                placeholder='min. piętro:'
                                onChange={handleChange}
                                value={searchData.minEstateLevel} 
                                name='minEstateLevel'
                            />
                            <input
                                type='number'
                                placeholder='max. piętro:'
                                onChange={handleChange}
                                value={searchData.maxEstateLevel} 
                                name='maxEstateLevel'
                            />
                    </div> 
                    <div className='options-to-check'>
                        <div className='firstColumn'>
                            <div className='check-input'>
                                <label htmlFor="garage">Miejsce postojowe / garaż:</label>
                                <input 
                                    type='checkbox'
                                    id='garage'
                                    checked={searchData.garage}
                                    name="garage"
                                    onChange={handleChange}
                                />
                            </div>
                            <div className='check-input'>
                                <label htmlFor="animals">Zwierzęta dozwolone: </label>
                                <input 
                                    type='checkbox'
                                    id='animals'
                                    checked={searchData.animals}
                                    name="animals"
                                    onChange={handleChange}
                                />   
                            </div>
                        </div>

                        <div className='secondColumn'>
                            <div className='check-input'>
                                <label htmlFor="annexKitchen">Aneks kuchenny: </label>    
                                <input 
                                    type='checkbox'
                                    id='annexKitchen'
                                    checked={searchData.annexKitchen}
                                    name="annexKitchen"
                                    onChange={handleChange}
                                />
                            </div>
                            <div className='check-input'>
                                <label htmlFor="elevator">Winda: </label>
                                <input 
                                    type='checkbox'
                                    id='elevator'
                                    checked={searchData.elevator}
                                    name="elevator"
                                    onChange={handleChange}
                                />
                            </div>
                        </div>
                    </div>
                </div>     
            </form>
        </div>
        <div className='more' onClick={() => {setMore(prev => !prev)}}>
            {more ? 
                <div>zwiń<br/>⇧</div>:
                <div>rozwiń<br/>⇩</div>}
        </div>
        
        
    </div>    
    )
}