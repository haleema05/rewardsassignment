package com.management.rewards.controller;

import java.time.LocalDate;
import java.util.List;

import com.management.rewards.entity.TransactionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.management.rewards.model.CustomerData;
import com.management.rewards.service.TransactionDataService;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

	@Autowired
	private TransactionDataService transactionDataService;

	// Gets all rewards of all customers for given begin and end date
	@GetMapping("/rewards")
	public List<CustomerData> getRewards(@RequestParam(name = "beginDate") String beginDate,
										 @RequestParam(name = "endDate") String endDate) {
		
		LocalDate dateStart = LocalDate.parse(beginDate);
		
		LocalDate dateEnd = LocalDate.parse(endDate);
		
		return transactionDataService.getRewards(dateStart, dateEnd);
	}

	// Gets rewards for the given customer id and given start and end date
	@GetMapping("/rewards/{customerId}")
	public List<CustomerData> getRewards(@RequestParam(name = "beginDate") String beginDate,
										 @RequestParam(name = "endDate") String endDate,
										 @PathVariable(name = "customerId") int customerId) {
		
		LocalDate dateStart = LocalDate.parse(beginDate);
		
		LocalDate dateEnd = LocalDate.parse(endDate);
		
		return transactionDataService.getRewards(dateStart, dateEnd, customerId);
	}

	// Gets all transactions
	@GetMapping("/all")
	public List<TransactionData> getAllData() {
		return transactionDataService.getAllTransactions();
	}

	// Gets all transactions for the given customer id
	@GetMapping("/{customerId}")
	public List<TransactionData> getAllDataForCustomer(@PathVariable(name = "customerId") int customerId) {
		return transactionDataService.getAllTransactions(customerId);
	}
}
