import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import ErrorPage from './routes/ErrorPage';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';  

import OfferSearchPage from './routes/OfferSearchPage';
import HomePage from './routes/HomePage';
import AddOfferPage from './routes/AddOfferPage';
import AccountPage from './routes/AccountPage';
import LoginPage from './routes/LoginPage';
import ContactPage from './routes/ContactPage';
import OfferPage from './routes/OfferPage';
import RegisterPage from './routes/RegisterPage';
import ProtectedRoute from './context/ProtectedRoute';
import { AuthProvider } from './context/AuthContext';

// import reportWebVitals from './reportWebVitals';

const router = createBrowserRouter([
  {
    path: '/',
    element: <App />,
    errorElement: <ErrorPage />,
    children: [
      {
        path: '/',
        element: <HomePage />,
      },
      {
        path: 'offers',
        element: <OfferSearchPage />
      },
      {
        path: 'addOffer',
        element: 
          <ProtectedRoute>
            <AddOfferPage />
          </ProtectedRoute>
      },
      {
        path: 'account',
        element: (
          <ProtectedRoute>
            <AccountPage />
          </ProtectedRoute>
        )
      },
      {
        path: 'login',
        element: <LoginPage />
      },
      {
        path: 'contact',
        element: <ContactPage />
      },
      {
        path: 'offers/:id',
        element: <OfferPage />
      },
      {
        path: 'register',
        element: <RegisterPage />
      }
    ]
  },
])

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <AuthProvider>
      <RouterProvider router={router} />
    </AuthProvider>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
// reportWebVitals();
