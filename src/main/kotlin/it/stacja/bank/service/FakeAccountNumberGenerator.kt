package it.stacja.bank.service

import org.springframework.stereotype.Service
import java.lang.String.format
import java.util.concurrent.atomic.AtomicLong

@Service
class FakeAccountNumberGenerator: AccountNumberGenerator {

    override val next: String
        get() = format("%026d", counter.incrementAndGet())

    private val counter = AtomicLong()

}