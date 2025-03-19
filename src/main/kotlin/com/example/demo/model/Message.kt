package com.example.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "messages")
data class Message(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    
    @Column(nullable = false)
    val content: String,
    
    @Column(nullable = false)
    val timestamp: Long = System.currentTimeMillis()
)
