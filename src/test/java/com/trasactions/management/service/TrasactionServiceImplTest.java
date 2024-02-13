package com.trasactions.management.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.management.rewards.entity.TransactionData;
import com.management.rewards.model.CustomerData;
import com.management.rewards.repository.TransactionDataRepository;
import com.management.rewards.service.TransactionDataService;

@ExtendWith(MockitoExtension.class)
public class TrasactionServiceImplTest {

    @InjectMocks
    public TransactionDataService trasactionService;

    @Mock
    public TransactionDataRepository transactionDataRepository;


    @Test
    public void testSave() {

        TransactionData transaction = new TransactionData();
        transaction.setCustomerId(1);
        transaction.setAmount(450);

        String date = "2023-12-22";

        transaction.setDate(LocalDate.parse(date));


        trasactionService.save(transaction);
        verify(transactionDataRepository).save(any(TransactionData.class));


        transaction.setAmount(95);
        trasactionService.save(transaction);
        verify(transactionDataRepository, times(2)).save(any(TransactionData.class));
    }

    @Test
    public void testSaveAll() {

        TransactionData transaction1 = new TransactionData();
        transaction1.setCustomerId(1);
        transaction1.setAmount(450);
        String date = "2023-12-02";

        transaction1.setDate(LocalDate.parse(date));

        TransactionData transaction2 = new TransactionData();
        transaction2.setCustomerId(2);
        transaction2.setAmount(88);
        date = "2023-12-03";

        transaction2.setDate(LocalDate.parse(date));


        List<TransactionData> theList = List.of(transaction1, transaction2);
        trasactionService.saveAll(theList);
        verify(transactionDataRepository).saveAll(any(List.class));
    }

    @Test
    public void testgetAllTransactions() {

        Integer data[] = new Integer[]{400, 200};
        List<Integer[]> theList = new ArrayList<>();
        theList.add(data);

        LocalDate startDate = LocalDate.parse("2022-09-01");
        LocalDate endDate = LocalDate.parse("2023-03-01");
        when(transactionDataRepository.getTransactions(startDate, endDate)).thenReturn(theList);

        List<CustomerData> result = trasactionService.getRewards(startDate, endDate);

        assertTrue(result.size() > 0);
    }

    @Test
    public void testgetAllTransactionsDataOfCustomer() {

        Integer data[] = new Integer[]{400, 300};
        List<Integer[]> theList = new ArrayList<>();
        theList.add(data);

        LocalDate startDate = LocalDate.parse("2022-09-01");
        LocalDate endDate = LocalDate.parse("2023-03-01");
        when(transactionDataRepository.getTransactions(startDate, endDate, 200)).thenReturn(theList);

        List<CustomerData> result = trasactionService.getRewards(startDate, endDate, 200);

        assertTrue(result.size() > 0);
    }

    @Test
    public void getAllTransactionsData() {

        TransactionData transaction = new TransactionData();
        transaction.setCustomerId(1);
        transaction.setAmount(450);
        String date = "2023-05-03";

        transaction.setDate(LocalDate.parse(date));
        when(transactionDataRepository.findAll()).thenReturn(List.of(transaction));


        List<TransactionData> result = trasactionService.getAllTransactions();
        assertTrue(result.size() > 0);

    }

    @Test
    public void getAllTransactionsDataByCustomerId() {

        TransactionData transaction = new TransactionData();
        transaction.setCustomerId(1);
        transaction.setAmount(450);
        String date = "2023-05-03";

        transaction.setDate(LocalDate.parse(date));
        when(transactionDataRepository.findByCustomerId(1)).thenReturn(List.of(transaction));


        List<TransactionData> result = trasactionService.getAllTransactions(1);
        assertTrue(result.size() > 0);

    }
}
