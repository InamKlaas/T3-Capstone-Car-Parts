package za.ac.cput.carpartmarket.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.carpartmarket.Domain.Transaction;
import za.ac.cput.carpartmarket.Repository.TransactionRepository;

@Service
public class TransactionService implements ITransactionService{

    @Autowired
    private TransactionRepository repository;

    @Override
    public Transaction create(Transaction transaction) {
        return repository.save(transaction);
    }

    @Override
    public Transaction read(Long aLong) {
        return repository.findById(aLong).orElse(null);
    }

    @Override
    public Transaction update(Transaction transaction) {
        return repository.save(transaction);
    }

    @Override
    public void delete(Long transactionId) {
        repository.deleteById(transactionId);

    }
}
