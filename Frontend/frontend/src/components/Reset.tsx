import React, { useEffect, useState } from 'react';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';

interface ResetButtonProps {
    onReset: () => void;
    bread: (number | null)[],
    veg: number | null;
    belgianBeer: number | null;
    dutchBeer: number | null;
    germanBeer: number | null;
}

const ResetButton: React.FC<ResetButtonProps> = ({ onReset, bread, veg, belgianBeer, dutchBeer, germanBeer }) => {

    const [isDisabled, setDisabled] = useState<boolean>(true);

    useEffect(() => {
        if (!bread.every(value => value === null) || veg || belgianBeer || dutchBeer || germanBeer) {
            setDisabled(false);
        }
    }, [bread, veg, belgianBeer, dutchBeer, germanBeer]);

    const handleClick = () => {
        onReset();
        setDisabled(true);
    }

  return (
    <Stack>
        <Button 
            onClick={handleClick}
            variant="outlined"
            color="warning"
            disabled={isDisabled}>
            Reset Order
        </Button>
    </Stack>
  );
}

export default ResetButton;
