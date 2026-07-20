package za.ac.cput.carpartmarket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.carpartmarket.Domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
