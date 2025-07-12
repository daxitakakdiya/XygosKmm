package org.example.xygos

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform