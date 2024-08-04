import React, { useState, useEffect, ChangeEvent } from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import InputAdornment from '@mui/material/InputAdornment';
import Grid from '@mui/material/Grid';

interface BreadInputProps {
  onChange: (values: (number | null)[]) => void;
  reset: boolean;
  resetComplete: () => void;
}

const BreadInput: React.FC<BreadInputProps> = ({ onChange, reset, resetComplete }) => {
    const [localBreadCount, setLocalBreadCount] = useState<(number | null)[]>(Array(7).fill(null));
    
    const handleChange = (index: number) => (event: ChangeEvent<HTMLInputElement>) => {
        const inputValue = event.target.value;
        let numericValue = inputValue === '' ? null : parseInt(inputValue, 10);
        if (numericValue !== null && numericValue < 0) {
            numericValue = 0;
        }
        const newValues = [...localBreadCount];
        newValues[index] = numericValue;
        setLocalBreadCount(newValues);
        onChange(newValues);        
    };

    useEffect(() => {
        if (reset) {
        const resetValues = Array(7).fill(null);
        setLocalBreadCount(resetValues);
        onChange(resetValues);
        resetComplete();
        }
    }, [reset, onChange, resetComplete]);

    const handleLabel = (index: number) => {
        if (index === 1) {
            return "Bread: 1 day old"
        }
        else {
            return `Bread: ${index} days old`;
        }
    }

  return (
    <Box component="form" noValidate autoComplete="off">
        <Grid container spacing={2} className="centered-inputs">
            {localBreadCount.map((value, index) => (
            <Grid item xs={12} key={index}>
                <TextField
                id={`bread-day-number-${index}`}
                label={handleLabel(index)}
                type="number"
                value={value ?? ''}
                onChange={handleChange(index)}
                InputProps={{
                    endAdornment: <InputAdornment position="end">pieces</InputAdornment>,
                }}
                />
            </Grid>
            ))}
        </Grid>
    </Box>
  );
};

export default BreadInput;
