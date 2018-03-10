package it.stacja.bank.model

import java.util.*

data class Customer(val name: String,
                    val dateOfBirth: Date,
                    val address: List<Address>)
