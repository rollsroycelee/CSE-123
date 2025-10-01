## Description:

You are responsible for implementing a new banking account system at the all new Intro CS Credit Union. The new bank will offer basic bank-accounts, high-yield savings accounts, and credit card accounts. The branch manager has tasked you with creating the new banking system. Below are the features that each account must have. 

## Requirements

In particular, you will be implementing the following two classes with their respective functionality:

`HYSA.java `

* A constructor that takes in a username, bank account number, interest rate, and starting deposit. The starting deposit and interest rate must be positive 

* `deposit(double value)` - add money to the account. Each time money is deposited, the interest rate is applied to the previous total

* `withdraw(double value)` - take money out of the account if possible. Do nothing if funds are insufficient. Input should not be negative

* `getValue()` - return the amount of money in the account

`CreditCard.java`

* A constructor that takes in a username, bank account number, and credit limit

* `deposit(double value)` - pay off a portion of the credit card total. The inputted value should not be larger than the amount owed

* `withdraw(double value)` - Make a purchase of the inputted value. The total amount on the credit card should not surpass the credit limit

* `getValue()` - return the amount of money owed on the credit card

## Implementation Notes

You must aim to avoid redundancy in your code using inheritance.

* Note that we have provided you with a basic `BankAccount` class. You should use this to your advantage in your solution.

The provided class headers do not necessarily correspond to the best implementation and should be changed as appropriate. 