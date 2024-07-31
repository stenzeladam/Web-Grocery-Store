import React, {useState} from 'react';
import Vegetables from './components/Vegetables'
import BelgianBeerInput from './components/BelgianBeer';
import DutchBeerInput from './components/DutchBeer';
import GermanBeerInput from './components/GermanBeer';
import './App.css';

const App: React.FC = () => {
  
  const [vegValue, setVegValue] = useState<number | null>();
  const [BelgianBeerValue, setBelgianBeerValue] = useState<number | null>();
  const [DutchBeerValue, setDutchBeerValue] = useState<number | null>();
  const [GermanBeerValue, setGermanBeerValue] = useState<number | null>();

  const handleVegetablesChange = (value: number | null) => {
    setVegValue(value);
  };

  const handleBelgianBeerChange = (value: number | null) => {
    setBelgianBeerValue(value);
  }

  const handleDutchBeerChange = (value: number | null) => {
    setDutchBeerValue(value);
  }

  const handleGermanBeerChange = (value: number | null) => {
    setGermanBeerValue(value);
  }

  return (
    <div className="App">
      <h1>Web Grocery Store</h1>
      <div className="input-container">
        <Vegetables onChange={handleVegetablesChange} />
      </div>
      <div className="input-container">
        <BelgianBeerInput onChange={handleBelgianBeerChange} />
      </div>
      <div className="input-container">
        <DutchBeerInput onChange={handleDutchBeerChange} />
      </div>
      <div className="input-container">
        <GermanBeerInput onChange={handleGermanBeerChange} />
      </div>
    </div>
  );
}

export default App;
