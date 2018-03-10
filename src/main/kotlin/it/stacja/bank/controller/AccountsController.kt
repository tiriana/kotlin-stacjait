package it.stacja.bank.controller

import it.stacja.bank.model.Account
import it.stacja.bank.service.AccountsService
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder


@RequestMapping("accounts")
@RestController
class AccountsController(private val accountsService: AccountsService) {

    @PostMapping
    fun createAccount(): ResponseEntity<Unit> {
        val account = accountsService.createAccount()
        val uri = requestUriWithId(account.number)
        return created(uri).build()
    }

    @GetMapping("{number}")
    fun getAccount(@PathVariable(name = "number") number: String): ResponseEntity<Account> {
        val account = accountsService.findAccountByNumber(number)
        return if (account != null) { ok(account) } else { notFound().build() }
    }

    private fun requestUriWithId(id: String) =
            ServletUriComponentsBuilder.fromCurrentRequestUri()
            .path("/{id}")
            .buildAndExpand(id)
            .toUri()

}