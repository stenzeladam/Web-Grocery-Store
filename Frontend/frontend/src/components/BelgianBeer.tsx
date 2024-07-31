import React, { useState, ChangeEvent } from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import InputAdornment from '@mui/material/InputAdornment';

interface BelgianBeerProps {
  onChange: (value: number | null) => void;
}

const BelgianBeerInput: React.FC<BelgianBeerProps> = ({ onChange }) => {
  const [value, setValue] = useState<number | null>(null);

  const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
    const inputValue = event.target.value;
    let numericValue = inputValue === '' ? null : parseInt(inputValue, 10);
    if (numericValue !== null && numericValue < 0) {
      numericValue = 0;
    }
    setValue(numericValue);
    onChange(numericValue);
  };

  return (
    <Box component="form" noValidate autoComplete="off">
      <div>
        <TextField
          id="belgian-beer-number"
          label="Belgian Beer Quantity"
          type="number"
          value={value ?? ''}
          onChange={handleChange}
          InputProps={{
            endAdornment: <InputAdornment position="end">bottles</InputAdornment>,
          }}
        />
      </div>
    </Box>
  );
};

export default BelgianBeerInput;
