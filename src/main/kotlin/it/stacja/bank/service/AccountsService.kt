package it.stacja.bank.service

import it.stacja.bank.model.Account
import it.stacja.bank.repository.AccountsRepository
import org.springframework.stereotype.Service

@Service
class AccountsService(
        private val accountsRepository: AccountsRepository,
        private val accountNumberGenerator: AccountNumberGenerator) {

    fun createAccount(): Account {
        val accountNumber = accountNumberGenerator.next
        val account = Account(number = accountNumber)
        return accountsRepository.save(account)
    }

    fun findAccountByNumber(accountNumber: String): Account? = accountsRepository.findByNumber(accountNumber)

    fun deposit(accountNumber: String, funds: Long) {
        process(accountNumber) { it.deposit(funds) }
    }

    fun withdraw(accountNumber: String, funds: Long) {
        process(accountNumber) {
            it.checkFunds(funds)
            it.withdraw(funds)
        }
    }

    private fun process(accountNumber: String, operation: (Account) -> Account) {
        val account = accountsRepository.findByNumber(accountNumber)
        account?.let { accountsRepository.save(operation(it)) }
    }

}