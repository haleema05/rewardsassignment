package com.management.rewards.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.management.rewards.entity.TransactionData;
import com.management.rewards.repository.TransactionDataRepository;
import com.management.rewards.util.TransactionDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.rewards.model.CustomerData;

@Service
public class TransactionDataService {

	@Autowired
	private TransactionDataRepository transactionDataRepository;

	public void save(TransactionData transaction) {
		computeRewards(transaction);
		transactionDataRepository.save(transaction);
	}

	/**
	 * Computes rewards points for the transaction
	 */
	private void computeRewards(TransactionData transaction) {

		double amount = transaction.getAmount();

		transaction.setRewards(TransactionDataUtil.calculateRewards(amount));
	}

	public void saveAll(List<TransactionData> transactionList) {

		for (TransactionData transaction : transactionList) {
			computeRewards(transaction);
		}

		transactionDataRepository.saveAll(transactionList);
	}

	public List<CustomerData> getRewards(LocalDate startDate, LocalDate endDate) {
		List<Integer[]> theList = transactionDataRepository.getTransactions(startDate, endDate);

		return convertToModel(theList);
	}

	public List<CustomerData> getRewards(LocalDate startDate, LocalDate endDate, int customerId) {
		List<Integer[]> theList = transactionDataRepository.getTransactions(startDate, endDate, customerId);

		return convertToModel(theList);
	}

	public List<TransactionData> getAllTransactions() {
		return transactionDataRepository.findAll();
	}

	public List<TransactionData> getAllTransactions(int customerId) {
		return transactionDataRepository.findByCustomerId(customerId);
	}

	private List<CustomerData> convertToModel(List<Integer[]> theList) {
		return theList.stream().map(p -> {
			CustomerData theData = new CustomerData();
			theData.setCustomerId(p[0]);
			theData.setRewards(p[1]);

			return theData;
		}).collect(Collectors.toList());

	}
}
