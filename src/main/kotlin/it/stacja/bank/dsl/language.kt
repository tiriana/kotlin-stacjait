package it.stacja.bank.dsl

import it.stacja.bank.model.Address
import it.stacja.bank.model.Customer
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

// https://www.cuba-platform.com/blog/kotlin-dsl-from-theory-to-practice

@CustomerDsl
class AddressBuilder {

    var info = ""
    var city = ""
    var postalCode = ""

    fun build(): Address = Address(info, city, postalCode)

}

@CustomerDsl
class Addresses: ArrayList<Address>() {

    fun address(block: AddressBuilder.() -> Unit) {
        add(AddressBuilder().apply(block).build())
    }

}

@CustomerDsl
class CustomerBuilder {

    var name = ""
    var dateOfBirth: String = ""
        set(value) {
            dob = SimpleDateFormat("dd-MM-yyyy").parse(value)
        }

    private var dob = Date()
    private val addresses = mutableListOf<Address>()

    fun addresses(block: Addresses.() -> Unit) {
        addresses.addAll(Addresses().apply(block))
    }

    fun build(): Customer = Customer(name, dob, addresses)
}

fun customer(block: CustomerBuilder.() -> Unit): Customer = CustomerBuilder()
        .apply(block)
        .build()


@DslMarker
annotation class CustomerDsl

//-------------------------------------------------------

val newCustomer = customer {
    name = "Jan Kowalski"
    dateOfBirth = "01-01-2018"
    addresses {
        address {
            info = "Dąbrowskiego 79a"
            city = "Poznań"
            postalCode = "60-599"
        }
        address {
            info = "Polska 111"
            city = "Poznań"
            postalCode = "60-529"
        }
    }
}