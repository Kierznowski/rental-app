import './../styles/components/offerShort-style.css';
import appartment from './../assets/images/appartment1.jpg';
import { Link } from 'react-router-dom';


export default function OfferShort(props) {
    return (
        <div className='content'>
        <div className='offer-container'>
            <Link to={`./../offers/${props.id}`}><img src={appartment} alt='appartment' className='offerImage'/></Link>
            <div className='description'>
                <div className='title-price'>
                    <Link to={`./../offers/${props.id}`} ><div className="title">{props.title}</div></Link>
                    <div className="price">{props.price} pln / msc</div>
                </div>
                <div className="info">Powierzchnia: {props.area} m2 | Miasto: {props.city} | Ulica: {props.street}</div>
            </div>
        </div>
        </div>
    )
}