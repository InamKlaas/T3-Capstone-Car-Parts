package za.ac.cput.carpartmarket.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.carpartmarket.Domain.Transaction;
import za.ac.cput.carpartmarket.Service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping
    public Transaction create(@RequestBody Transaction transaction){
        return transactionService.create(transaction);
    }

    @GetMapping("/{id}")
    public Transaction read(@PathVariable("id") Long transactionId){
        return transactionService.read(transactionId);
    }

    @PutMapping
    public Transaction update(@RequestBody Transaction transaction){
        return transactionService.update(transaction);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long transactionId){
        transactionService.delete(transactionId);
    }
}
