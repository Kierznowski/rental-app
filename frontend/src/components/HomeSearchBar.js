import { useState } from 'react';
import '../styles/components/home-search-bar.css';
import searchIcon from '../assets/images/icons/search-icon.png';
import locationIcon from '../assets/images/icons/location-icon.png';
import priceIcon from '../assets/images/icons/price-icon.png';
import areaIcon from '../assets/images/icons/area-icon.png';

export default function HomeSearchBar() {

    const [searchData, setSearchData] = useState({
        city: "",
        minPrice: "",
        maxPrice: "",
        minArea: "",
        maxArea: "",

    });

    function handleSubmit(event) {
        event.preventDefault();
        
        const searchParams = new URLSearchParams();
        Object.entries(searchData).forEach(([key, value]) => {
            if(value) searchParams.append(key, value);
        });
    }

    function handleChange(event) {
        const {name, value, type, checked} = event.target;
        setSearchData(prev => ({
                ...prev,
                [name]: type === "checkbox" ? checked : value
        }));
    }


    return (
        <div className="searchbar-container">
            <div className='searchbar'>
            <form className='search-form' onSubmit={handleSubmit}>
                <div className='inputs'>
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
                    <div className='category'>
                        <img className='area-icon' src={areaIcon} alt='area-icon' />
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
                    
                </div>
                
                <button className='submit-searchbar'><img src={searchIcon} alt='search-icon'></img>Szukaj</button>
            </form>
            </div>
        </div>
    )
}