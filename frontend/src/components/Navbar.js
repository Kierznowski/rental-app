import { Link } from 'react-router-dom';

export default function Navbar() {

    return (
        <nav>
            <Link to={'/'}><div className="logo">CLEARENT<span className="sublogo"> i wszystko jasne</span></div></Link>
            <div className="menu">
                <Link to={'/'}><button>Strona główna</button></Link>
                <Link to={'offers'}><button>Przeglądaj oferty</button></Link>
                <Link to={'addOffer'}><button>Dodaj ofertę</button></Link>
                <Link to={'account'}><button>Konto</button></Link>
                <Link to={'login'}><button>Zaloguj się</button></Link>
                <Link to={'contact'}><button>Kontakt</button></Link> 
            </div>
        </nav>
    )
}