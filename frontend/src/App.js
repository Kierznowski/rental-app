import Navbar from './components/Navbar';
import Footer from './components/Footer';
import { Outlet } from 'react-router-dom';
import './styles/style.css';

function App() {
    return (
      <div className="main-container">
        <Navbar />
        <main>
          <Outlet />
        </main>
        <Footer />
      </div>
    );
}

export default App;
