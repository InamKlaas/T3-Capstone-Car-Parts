package za.ac.cput.carpartmarket.factory;

import za.ac.cput.carpartmarket.Domain.Order;
import za.ac.cput.carpartmarket.Domain.Transaction;
import za.ac.cput.carpartmarket.util.Helper;

import java.time.LocalDateTime;

public class TransactionFactory {

    public static Transaction createTransaction(Long transactionId, LocalDateTime transactionDate, Order order,double amount){
        if(Helper.isEmptyOrNull(transactionId)){
            return null;
        }
        if (transactionDate == null) {
            return null;
        }
        if (order == null) {
            return null;
        }
        if (amount <= 0) {
            return null;
        }

        return new Transaction.Builder()
                .setTransactionId(transactionId)
                .setTransactionDate(transactionDate)
                .setOrder(order)
                .setAmount(amount)
                .build();
    }
}
