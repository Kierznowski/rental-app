import './../styles/routes/home-style.css';

import { Link } from 'react-router-dom';

import appartment1 from './../assets/images/appartment1.jpg';
import appartment2 from './../assets/images/appartment2.jpg'

export default function Home() {
    return (
        <div className="home-page">
            <div className="row1">
                <div className="home-content1">
                    <h3>Przejrzyste i klarowne oferty</h3>
                    <p>Dzięki prostemu i ograniczonemu do niezbędnych informacji formularzowi, oferty zamieszczane na Clearent są przejrzyste i jednoznaczne.
                    Skracamy czas wystawiania ogłoszenia oraz czas poświęcony na poszukiwania odpowiedniego lokum na wynajem.
                    </p>
                </div>
                <img src={appartment1} alt="appartment" className="appartment-img" />
            </div>
            
            <div className="row2">
                <img src={appartment2} alt="appartment" className="appartment-img" />
                <div className="home-content2">
                    <h3>Czynsz tylko 2000 zł ! <br/> ...plus media 500</h3>
                    <p>...plus śmieci 100 zł. Ah, no i jeszcze prąd oddzielnie! <br />Też to znamy i też tego nie lubimy. Dodatkowe opłaty ukrywane w opisach ogłoszeń skutecznie utrudniają szukanie mieszkania w odpowiedniej cenie.
                    To właśnie takie ogłoszenia zainspirowały nas do stworzenia Clearent.
                    </p>
                </div>
            </div>
            <div className="action-bar">
                <Link to={'/register'}>
                    <button className="action-button">Załóż konto</button>
                </Link>
                <Link to={'/offers'}>
                    <button className="action-button">Szukaj ofert</button>
                </Link>
            </div>
        </div>
    )
}