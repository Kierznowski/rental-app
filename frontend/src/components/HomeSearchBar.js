import { useState } from 'react';
import '../styles/components/search-form.css';
import searchIcon from '../assets/images/icons/search-icon.png';
import locationIcon from '../assets/images/icons/location-icon.png';
import priceIcon from '../assets/images/icons/price-icon.png';
import areaIcon from '../assets/images/icons/area-icon.png';
import { useNavigate } from 'react-router-dom';

export default function HomeSearchBar() {

    const [searchData, setSearchData] = useState({
        city: "",
        minPrice: "",
        maxPrice: "",
        minArea: "",
        maxArea: "",
    });

    const navigate = useNavigate();

    function handleSubmit(event) {
        event.preventDefault();

        const searchParams = new URLSearchParams();
        Object.entries(searchData).forEach(([key, value]) => {
            if(value) searchParams.append(key, value);
        });

        navigate(`/offers?${searchParams.toString()}`);
    }

    function handleChange(event) {
        const {name, value} = event.target;
        setSearchData(prev => ({
            ...prev,
            [name]: value,
        }));
    }


    return (
        <div className="search-container">
            <div className='searchbar'>
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
                </div>
                <div className='column3'>
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
                </div>
                
                <button className='submit-search'><img src={searchIcon} alt='search-icon' />Szukaj</button>
            </form>
            </div>
        </div>
    )
}