# Assigmment rewards
 
[Spring Boot](http://projects.spring.io/spring-boot/)


## Requirements

For building and running the application you need:

## Packages & Framework
- Spring Boot
- Lombok
- H2 Db
- JPA

- [Maven 3](https://maven.apache.org)
- [JDK 11 or above]  (https://jdk.java.net/)
- [H2 Database]
- 
## Running the application locally

execute the `main` method in the `com.management.rewards.RewardsApp`

or from shell

```shell
mvn spring-boot:run
```
#### Entity Class
#####  TransactionData  {
	id,
	customerId,
	amount,
	date,
	rewards,
}

Controller class

`com.management.rewards.TransactionDataController`

which has four end points 

http://localhost:8080/api/transaction/rewards?beginDate=?&endDate=?

example: http://localhost:8080/api/transaction/rewards?beginDate=2023-10-05&endDate=2024-01-31

http://localhost:8080/api/transaction/rewards/{customerId}/beginData=?/endDate=?

example: http://localhost:8080/api/transaction/rewards/20?beginDate=2023-10-05&endDate=2024-01-31

http://localhost:8080/api/transaction/all

http://localhost:8080/api/trasaction/{customerId}


#### Repository Class
TransactionDataRepository 

Used JPA to fetch transaction from database


