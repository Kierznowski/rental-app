import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import ErrorPage from './routes/ErrorPage';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';  

import OfferSearch from './routes/OfferSearch';
import AddOffer from './routes/AddOffer';
import Account from './routes/Account';
import Login from './routes/Login';
import Contact from './routes/Contact'; 
import Home from './routes/Home';
import Offer from './routes/Offer';

// import reportWebVitals from './reportWebVitals';

const router = createBrowserRouter([
  {
    path: '/',
    element: <App />,
    errorElement: <ErrorPage />,
    children: [
      {
        path: '/',
        element: <Home />
      },
      {
        path: 'offers',
        element: <OfferSearch />
      },
      {
        path: 'addOffer',
        element: <AddOffer />
      },
      {
        path: 'account',
        element: <Account />
      },
      {
        path: 'login',
        element: <Login />
      },
      {
        path: 'contact',
        element: <Contact />
      },
      {
        path: 'offers/:id',
        element: <Offer/>
      }
    ]
  },
])

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
    {/* <App /> */}
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
// reportWebVitals();
