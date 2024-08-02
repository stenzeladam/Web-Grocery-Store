import React, { useState, useEffect } from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import '../App.css';

interface TableProps {
  orderData: any[];
}

const ReceiptTable: React.FC<TableProps> = ({ orderData }) => {
  const [rows, setRows] = useState<any[]>([]);

  useEffect(() => {
    setRows(orderData ?? []);
  }, [orderData]);

  const formatQuantity = (quantity: number, itemName: string) => {
    if (itemName === "Vegetables") {
      if (quantity < 1000) {
        return `${quantity.toFixed(2)} grams`;
      } else {
        return `${(quantity / 1000).toFixed(3)} kg`;
      }
    } else if (itemName === "Belgian Beer Bottle" || itemName === "Dutch Beer Bottle" || itemName === "German Beer Bottle") {
      if (quantity === 1) {
        return `${quantity} Bottle`;
      }
      return `${quantity} Bottles`;
    } else if (itemName === "6-Pack of Belgian Beer" || itemName === "6-Pack of Dutch Beer" || itemName === "6-Pack of German Beer") {
      if (quantity === 1) {
        return `${quantity} Pack`;
      }
      return `${quantity} Packs`;
    }
    return "";
  };

  const formatUnitPrice = (price: number, itemName: string) => {
    let priceStr = price.toFixed(2).toString();
    priceStr = priceStr.replace('.', ',');
    if (itemName === "Vegetables") {
      return `\u20AC ${priceStr} per 100.0 grams`;
    } else if (itemName === "Belgian Beer Bottle" || itemName === "Dutch Beer Bottle" || itemName === "German Beer Bottle") {
      return `\u20AC ${priceStr} per Bottle`;
    } else if (itemName === "6-Pack of Belgian Beer" || itemName === "6-Pack of Dutch Beer" || itemName === "6-Pack of German Beer") {
      return `\u20AC ${priceStr} per Pack`;
    }
    return "";
  };

  const formatDiscountRule = (discountRule: string, itemName: string) => {
    if (itemName === "Subtotal") {
      return "";
    }
    if (discountRule === "") {
      return "---";
    }
    return discountRule;
  };

  const formatItemTotalPrice = (totalItemPrice: number) => {
    let priceStr = totalItemPrice.toFixed(2).toString();
    priceStr = priceStr.replace('.', ',');
    return `\u20AC ${priceStr}`;
  };

  return (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead className="table-head-dark">
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
            <TableRow key={row.itemName}>
              <TableCell 
                component="th" 
                scope="row" 
                style={row.itemName === "Subtotal" ? { fontWeight: 'bold' } : {}}
              >
                {row.itemName}
              </TableCell>
              <TableCell align="right">
                {formatQuantity(row.quantity, row.itemName)}
              </TableCell>
              <TableCell align="right">
                {formatUnitPrice(row.unitPrice, row.itemName)}
              </TableCell>
              <TableCell align="right">
                {formatDiscountRule(row.discountRule, row.itemName)}
              </TableCell>
              <TableCell 
                align="right" 
                style={row.itemName === "Subtotal" ? { fontWeight: 'bold' } : {}}
              >
                {formatItemTotalPrice(row.totalItemPrice)}
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}

export default ReceiptTable;
