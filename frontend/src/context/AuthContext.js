import React, { createContext, useState, useEffect } from 'react';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [authState, setAuthState] = useState(false);

  const checkAuthStatus = async () => {
    try {
      const response = await fetch('http://localhost:9090/bff/auth/is-auth', {
        method: 'GET',
        credentials: 'include',
      })

      if(response.ok) {
        setAuthState(true);
      } else {
        setAuthState(false);
      }
    } catch(error) {
      console.error('Error when checking authentication status: ', error);
      setAuthState(false);
    }
  };


  useEffect(() => {
    checkAuthStatus();
  }, []);

  return (
    <AuthContext.Provider value={{ authState, setAuthState, checkAuthStatus }}>
      {children}
    </AuthContext.Provider>
  );
};