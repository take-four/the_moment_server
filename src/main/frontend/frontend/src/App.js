import React, { Component } from 'react';
import ReactDOM from 'react-dom';
//import './style/style.css'
//import logo from './logo.svg';
//import './App.css';

/*ReactDOM.render(
    <h1 className="testblue">App working</h1>,
    document.querySelector('.container'));*/

class App extends React.Component {
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Welcome to React</h1>
        </header>
        <p className="App-intro">
          To get started, edit <code>src/App.js</code> and save to reload.
        </p>
      </div>
    );
  }
}

export default App;
