import './../styles/components/search-form.css';
import locationIcon from '../assets/images/icons/location-icon.png';
import priceIcon from '../assets/images/icons/price-icon.png';
import areaIcon from '../assets/images/icons/area-icon.png';
import searchIcon from '../assets/images/icons/search-icon.png';
import districtIcon from '../assets/images/icons/district-icon.png';
import roomsIcon from '../assets/images/icons/rooms-icon.png';
import floorIcon from '../assets/images/icons/floor-icon.png';

import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

export default function SearchForm({ onSearch, initialCriteria }) {

    const [searchData, setSearchData] = useState(initialCriteria);
    const [more, setMore] = useState(false);
    const navigate = useNavigate();

    useEffect(() => {
        setSearchData(initialCriteria);
    }, [initialCriteria]);

    function handleChange(event) {
        const {name, value, type, checked} = event.target;
        setSearchData(prev => ({
                ...prev,
                [name]: type === "checkbox" ? checked : value
        }));
    }

    function handleSubmit(event) {
        event.preventDefault();

        const searchParams = new URLSearchParams();
        Object.entries(searchData).forEach(([key, value]) => {
            if(value) searchParams.append(key, value);
        });

        navigate(`?${searchParams.toString()}`);
        onSearch(searchData);
    }


    return (
        <div className='search-container'>
            <div className='adv-searchbar'>
                <form className='search-form' onSubmit={handleSubmit}>
                    <div className='inputs'>

                        <div className='column1'>
                            <div className='category'>
                                <img className='location-icon' src={locationIcon} alt='location-icon'/>
                                <input 
                                    type='text' 
                                    placeholder='miasto'
                                    onChange={handleChange}    
                                    value={searchData.city}
                                    name='city'
                                    className='city-input'
                                />    
                            </div>
                            <div className={`additional-options ${more ? 'visible' : ''}`}>
                                <div className='category'>
                                    <img src={districtIcon} alt='district-icon' className='district-icon'/>
                                    <input
                                        type='text'
                                        placeholder='dzielnica'
                                        onChange={handleChange}
                                        value={searchData.district}
                                        name='district'
                                        className='district-input'
                                    />
                                </div>
                            </div>


                        </div>

                        <div className='column2'>
                            <div className='category'>
                                <img className='price-icon' src={priceIcon} alt='price-icon'/>
                                <input  
                                    type='number'
                                    placeholder='cena od'
                                    onChange={handleChange}
                                    value={searchData.minPrice}
                                    name='minPrice'
                                    className='price-input1'
                                />
                                <input 
                                    type='number'
                                    placeholder='cena do'
                                    onChange={handleChange}
                                    value={searchData.maxPrice}
                                    name='maxPrice'
                                    className='price-input2'
                                />
                            </div>
                            
                            <div className={`additional-options ${more ? 'visible' : ''}`}>
                                <div className='category'>
                                    <img src={roomsIcon} alt='rooms-icon' className='rooms-icon' />
                                    <input
                                        type='number'
                                        placeholder='min. pokoi'
                                        onChange={handleChange}
                                        value={searchData.minRoomsNumber} 
                                        name='minRoomsNumber'
                                        className='rooms-input1'
                                    />
                                    <input
                                        type='number'
                                        placeholder='max. pokoi'
                                        onChange={handleChange}
                                        value={searchData.maxRoomsNumber} 
                                        name='maxRoomsNumber'
                                        className='rooms-input2'
                                    />
                                </div>
                            </div>
                        </div>
                        <div className='column3'>
                            <div className='category'>
                                <img className='area-icon' src={areaIcon} alt='area-icon'/>
                                <input
                                    type='number'
                                    placeholder='powierzchnia od'
                                    onChange={handleChange}
                                    value={searchData.minArea} 
                                    name='minArea'
                                    className='area-input1'
                                />
                                <input
                                    type='number'
                                    placeholder='powierzchnia do'
                                    onChange={handleChange}
                                    value={searchData.maxArea} 
                                    name='maxArea'
                                    className='area-input2'
                                />
                            </div>
                            
                            <div className={`additional-options ${more ? 'visible' : ''}`}>
                                <div className='category'>
                                    <img src={floorIcon} alt='floor-icon' className='floor-icon' />
                                    <input
                                        type='number'
                                        placeholder='min. piętro'
                                        onChange={handleChange}
                                        value={searchData.minEstateLevel} 
                                        name='minEstateLevel'
                                        className='level-input1'
                                    />
                                    <input
                                        type='number'
                                        placeholder='max. piętro'
                                        onChange={handleChange}
                                        value={searchData.maxEstateLevel} 
                                        name='maxEstateLevel'
                                        className='level-input2'
                                    />
                                </div>
                            </div>
                        </div>
                        
                    </div>
                    
                    <button className='submit-search'><img src={searchIcon} alt='search-icon'/>Szukaj</button>
                </form>
                <div className={`additional-options ${more ? 'visible' : ''}`}>
                <form className='options-to-check'>
                    <div className='check'>
                        <label htmlFor="garage">Miejsce postojowe / garaż:</label>
                        <input 
                            type='checkbox'
                            id='garage'
                            checked={searchData.garage}
                            name="garage"
                            onChange={handleChange}
                        />
                    </div>
                    <div className='check'>
                        {/* TO DO backend */}    
                        <label htmlFor="annexKitchen">Balkon / loggia / ogródek</label>    
                        <input 
                            type='checkbox'
                            id='annexKitchen'
                            checked={searchData.annexKitchen}
                            name="annexKitchen"
                            onChange={handleChange}
                        />
                    </div>
                    <div className='check'>
                        <label htmlFor="elevator">Winda: </label>
                        <input 
                            type='checkbox'
                            id='elevator'
                            checked={searchData.elevator}
                            name="elevator"
                            onChange={handleChange}
                        />
                    </div>
                    <div className='check'>
                        <label htmlFor="animals">Zwierzęta dozwolone: </label>
                        <input 
                            type='checkbox'
                            id='animals'
                            checked={searchData.animals}
                            name="animals"
                            onChange={handleChange}
                        />   
                    </div>
                </form>
                </div>      
            </div>
            <div className='more' onClick={() => {setMore(prev => !prev)}}>
                {more ? 
                    <div>zwiń<br/>⇧</div>:
                    <div>więcej kryteriów<br/>⇩</div>}
            </div>
        </div>    
    )
}