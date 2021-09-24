package com.marcoscouto

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("com.marcoscouto")
		.start()
}

