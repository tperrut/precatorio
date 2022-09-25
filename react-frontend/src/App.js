import React from 'react';
import logo from './logo.svg';
import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import ListClienteComponent from './components/ListClienteComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import CreateClienteComponent from './components/CreateClienteComponent';
import UpdateClienteComponent from './components/UpdateClienteComponent';
import ViewClienteComponent from './components/ViewClienteComponent';

function App() {
  return (
    <div>
        <Router>
              <HeaderComponent />
                <div className="container">
                    <Switch> 
                          <Route path = "/" exact component = {ListClienteComponent}></Route>
                          <Route path = "/clientes" component = {ListClienteComponent}></Route>
                          <Route path = "/add-cliente/:id" component = {CreateClienteComponent}></Route>
                          <Route path = "/view-cliente/:id" component = {ViewClienteComponent}></Route>
                          {/* <Route path = "/update-cliente/:id" component = {UpdateClienteComponent}></Route> */}
                    </Switch>
                </div>
              <FooterComponent />
        </Router>
    </div>
    
  );
}

export default App;
