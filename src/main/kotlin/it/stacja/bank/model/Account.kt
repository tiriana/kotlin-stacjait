package it.stacja.bank.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Account(@GeneratedValue @Id val id: Long = 0, val number: String, val balance: Long = 0) {

    constructor(): this(id = 0, number = "", balance = 0)

    fun deposit(funds: Long) = copy(balance = balance + funds)

    fun withdraw(funds: Long) = copy(balance = balance - funds)

    fun checkFunds(funds: Long) {
        if (balance < funds) {
            throw InsufficientFundsException()
        }
    }

}

class InsufficientFundsException: Throwable()
