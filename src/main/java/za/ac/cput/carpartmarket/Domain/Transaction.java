package za.ac.cput.carpartmarket.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
@Entity

public class Transaction {
    @Id
    private Long id;
    private Long transactionId;
    private LocalDateTime transactionDate;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private double amount;

    protected Transaction(){

    }

    public Transaction(Builder builder) {
        this.transactionId = builder.transactionId;
        this.transactionDate = builder.transactionDate;
        this.order= builder.order;
        this.amount = builder.amount;

    }

    public Long getTransactionId() {
        return transactionId;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public Order getOrder() {
        return order;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", transactionDate=" + transactionDate +
                ", order=" + order +
                ", amount=" + amount +
                '}';
    }

    public static class Builder {
        private Long transactionId;
        private LocalDateTime transactionDate;
        private Order order;
        private double amount;

        public Builder setTransactionId(Long transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public Builder setTransactionDate(LocalDateTime transactionDate) {
            this.transactionDate = transactionDate;
            return this;
        }

        public Builder setOrder(Order order) {
            this.order = order;
            return this;
        }

        public Builder setAmount(double amount) {
            this.amount = amount;
            return this;
        }

        public Builder copy(Transaction transaction){
            this.transactionId = transaction.transactionId;
            this.transactionDate = transaction.transactionDate;
            this.order = transaction.order;
            this.amount = transaction.amount;
            return this;
        }

        public Transaction build(){
            return new Transaction(this);
        }
    }
    }
