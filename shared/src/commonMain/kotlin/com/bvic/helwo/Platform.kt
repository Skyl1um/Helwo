package com.bvic.helwo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform