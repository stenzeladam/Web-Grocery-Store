import React, { useEffect, useState } from 'react';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';
import axios from 'axios';

interface SubmitButtonProps {
    veg: number | null,
    belgianBeer: number | null,
    dutchBeer: number | null,
    germanBeer: number | null,
    onOrderResponse: any
}

const SubmitButton: React.FC<SubmitButtonProps> = ({ veg, belgianBeer, dutchBeer, germanBeer, onOrderResponse }) => {

    const [isDisabled, setDisabled] = useState<boolean>(true);
    useEffect(() => {
        if (!veg && !belgianBeer && !dutchBeer && !germanBeer) {
            setDisabled(true);
        }
        else {
            setDisabled(false);
        }
    }, [veg, belgianBeer, dutchBeer, germanBeer]);

    const handleSubmit = async () => {
        const priceDataSuccess = await setPriceData();

        if (!priceDataSuccess) {
            return;
        }

        const orderData = {
            bread: [1, 0, 0, 0, 0, 0, 0],
            vegetables: veg ?? 0,
            belgianBeers: belgianBeer ?? 0,
            dutchBeers: dutchBeer ?? 0,
            germanBeers: germanBeer ?? 0
        };

        try {
            const orderResponse = await axios.post('http://localhost:8080/send_order', orderData);
            onOrderResponse(orderResponse.data);
        } catch (error) {
            console.error('Error submitting order:', error);
        }
        setDisabled(true);
    };

    const setPriceData = async () => {
        const priceData = {
            BREAD_PRICE: 1.00,
            VEGETABLES_PRICE: 1.00,
            BELGIAN_BEERS_PRICE: 0.75,
            DUTCH_BEERS_PRICE: 0.50,
            GERMAN_BEERS_PRICE: 1.00,
        };

        try {
            // eslint-disable-next-line @typescript-eslint/no-unused-vars
            const response = await axios.post('http://localhost:8080/set_Prices', priceData);
            //console.log('Price data success:', response.data);
            return true;
        } catch (error) {
            console.error('Error setting price data:', error);
            return false;
        }
    };

  return (
    <Stack>
        <Button 
            onClick={handleSubmit}
            color='success'
            variant="outlined"
            disabled={isDisabled}>
            Submit Order
        </Button>
    </Stack>
  );
}

export default SubmitButton;
