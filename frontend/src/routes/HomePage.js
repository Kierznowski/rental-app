import './../styles/routes/home-style.css';

import appartment1 from './../assets/images/appartments/appartment1.jpg';
import appartment2 from './../assets/images/appartments/appartment3.jpg'
import HomeSearchBar from '../components/HomeSearchBar';

export default function HomePage() {
    

    return (
        <div className="home-page">
            <HomeSearchBar />
            <div className="row1">
                <div className='home-content'>
                    <div className="home-content1">
                        <h3>Przejrzyste i klarowne oferty</h3>
                        <p>Dzięki prostemu i ograniczonemu do niezbędnych informacji formularzowi, oferty zamieszczane na Clearent są przejrzyste i jednoznaczne.
                        Skracamy czas wystawiania ogłoszenia oraz czas poświęcony na poszukiwania odpowiedniego lokum na wynajem.
                        </p>
                    </div><div className='home-content2'>
                        <h3>Czynsz tylko 2000 zł ! ...plus media 500</h3>
                        <p>...plus śmieci 100 zł. Ah, no i jeszcze prąd oddzielnie! <br />Też to znamy i też tego nie lubimy. Dodatkowe opłaty ukrywane w opisach ogłoszeń skutecznie utrudniają szukanie mieszkania w odpowiedniej cenie.
                        To właśnie takie ogłoszenia zainspirowały nas do stworzenia Clearent.
                        </p>
                    </div>
                </div>
                <img src={appartment1} alt="appartment" className="appartment-img1" />
            </div>
            
            <div className="row2">
                <img src={appartment2} alt="appartment" className="appartment-img2" />
            </div>
        </div>
    )
}