package com.sid.gl.process;

import com.sid.gl.model.BankTransaction;
import lombok.Getter;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

//@Component
public class BankTransactionAnalyticProcessor implements ItemProcessor<BankTransaction,BankTransaction> {
    @Getter
    private double totalDebit;
    @Getter
    private double totalCredit;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
    @Override
    public BankTransaction process(BankTransaction bankTransaction) throws Exception {
        //bankTransaction.setTransactionDate(dateFormat.parse(bankTransaction.getStrTransactionDate()));
        if(bankTransaction.getTransactionType().equals("D"))
            totalDebit+=bankTransaction.getAmount();
        else if(bankTransaction.getTransactionType().equals("C"))
            totalCredit+=bankTransaction.getAmount();
        return bankTransaction;
    }
}
