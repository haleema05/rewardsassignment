package com.management.rewards.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class TransactionData {

	@Id
	@JsonIgnore
	private int id;

	private int customerId;

	private double amount;

	private LocalDate date;

	private int rewards;
}
