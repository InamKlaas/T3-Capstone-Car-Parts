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

    @PostMapping("/create")
    public Transaction create(@RequestBody Transaction transaction){
        return transactionService.create(transaction);
    }

    @GetMapping("/read/{transactionId}")
    public Transaction read(@PathVariable("transactionId") Long transactionId){
        return transactionService.read(transactionId);
    }

    @PutMapping("/update")
    public Transaction update(@RequestBody Transaction transaction){
        return transactionService.update(transaction);
    }

    @DeleteMapping("/delete/{transactionId}")
    public void delete(@PathVariable("transactionId") Long transactionId){
        transactionService.delete(transactionId);
    }
}
