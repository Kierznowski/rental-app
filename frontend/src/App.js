import Navbar from './components/Navbar';
import Footer from './components/Footer';
import SidePanel from './components/SidePanel';
import { Outlet } from 'react-router-dom';
import './styles/base.css';
import { useState } from 'react';

function App() {

    const[isPanelOpen, setIsPanelOpen] = useState(false);
    
    const togglePanel = () => {
      setIsPanelOpen(!isPanelOpen);
    }

    return (


      <div className="main-container">
        
        <Navbar togglePanel={togglePanel}/>
        <SidePanel isOpen={isPanelOpen} togglePanel={togglePanel}/>
        <main>
          <Outlet />
        </main>
        <Footer />
      </div>
    );
}

export default App;
