import React, { useState, useEffect } from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

interface TableProps {
  orderData: any[];
}

const ReceiptTable: React.FC<TableProps> = ({ orderData }) => {
  const [rows, setRows] = useState<any[]>([]);

  useEffect(() => {
    setRows(orderData ?? []);
  }, [orderData]);

  return (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>Item Name</TableCell>
            <TableCell align="right">Quantity</TableCell>
            <TableCell align="right">Unit Price</TableCell>
            <TableCell align="right">Discount Rule</TableCell>
            <TableCell align="right">Item Total Price</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {rows.map((row) => (
            <TableRow
              key={row.itemName}
              sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
            >
              <TableCell component="th" scope="row">
                {row.itemName}
              </TableCell>
              <TableCell align="right">{row.quantity}</TableCell>
              <TableCell align="right">{row.unitPrice.toFixed(2)}</TableCell>
              <TableCell align="right">{row.discountRule}</TableCell>
              <TableCell align="right">{row.totalItemPrice.toFixed(2)}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}

export default ReceiptTable;
