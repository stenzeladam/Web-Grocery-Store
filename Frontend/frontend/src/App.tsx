import React, {useState} from 'react';
import Vegetables from './components/Vegetables'
import BelgianBeerInput from './components/BelgianBeer';
import DutchBeerInput from './components/DutchBeer';
import GermanBeerInput from './components/GermanBeer';
import SubmitButton from './components/SubmitOrderButton';
import ReceiptTable from './components/ReceiptTable';
import ResetButton from './components/Reset';
import './App.css';

const App: React.FC = () => {
  
  const [vegValue, setVegValue] = useState<number | null>(null);
  const [BelgianBeerValue, setBelgianBeerValue] = useState<number | null>(null);
  const [DutchBeerValue, setDutchBeerValue] = useState<number | null>(null);
  const [GermanBeerValue, setGermanBeerValue] = useState<number | null>(null);
  const [orderResponseData, setOrderResponseData] = useState<any>(null);
  const [reset, setReset] = useState<boolean>(false);

  const handleVegetablesChange = (value: number | null) => {
    setVegValue(value);
  };

  const handleBelgianBeerChange = (value: number | null) => {
    setBelgianBeerValue(value);
  };

  const handleDutchBeerChange = (value: number | null) => {
    setDutchBeerValue(value);
  };

  const handleGermanBeerChange = (value: number | null) => {
    setGermanBeerValue(value);
  };

  const handleOrderResponse = (data: any) => {
    setOrderResponseData(data);
  };

  const clearValues = () => {
    setVegValue(null);
    setBelgianBeerValue(null);
    setDutchBeerValue(null);
    setGermanBeerValue(null);
    setOrderResponseData(null);
    setReset(true);
  };

  const resetComplete = () => {
    setReset(false);
  };

  return (
    <div className="App">
      <h1>Web Grocery Store</h1>
      <div className="input-container">
        <Vegetables 
          onChange={handleVegetablesChange}
          reset={reset}
          resetComplete={resetComplete} 
        />
      </div>
      <div className="input-container">
        <BelgianBeerInput 
          onChange={handleBelgianBeerChange}
          reset={reset}
          resetComplete={resetComplete}
        />
      </div>
      <div className="input-container">
        <DutchBeerInput 
          onChange={handleDutchBeerChange}
          reset={reset}
          resetComplete={resetComplete}
        />
      </div>
      <div className="input-container">
        <GermanBeerInput 
          onChange={handleGermanBeerChange}
          reset={reset}
          resetComplete={resetComplete}
        />
      </div>
      <div className="submit-button">
        <SubmitButton 
          veg={vegValue} 
          belgianBeer={BelgianBeerValue} 
          dutchBeer={DutchBeerValue} 
          germanBeer={GermanBeerValue}
          onOrderResponse={handleOrderResponse}/>
      </div>
      <div className="submit-button">
        <ResetButton
          onReset={clearValues}
          veg={vegValue}
          belgianBeer={BelgianBeerValue}
          dutchBeer={DutchBeerValue}
          germanBeer={GermanBeerValue}
        />
      </div>
      <div>
        <ReceiptTable
          orderData={orderResponseData}
        />
      </div>
    </div>
  );
}

export default App;
