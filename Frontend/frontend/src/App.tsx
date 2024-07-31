import React, {useState} from 'react';
import Vegetables from './components/Vegetables'

const App: React.FC = () => {
  
  const [vegValue, setVegValue] = useState<number | null>();

  const handleVegetablesChange = (value: number | null) => {
    setVegValue(value);
  };

  return (
    <div className="App">
      <h1>Web Grocery Store</h1>
      <Vegetables onChange={handleVegetablesChange}/>
    </div>
  );
}

export default App;
