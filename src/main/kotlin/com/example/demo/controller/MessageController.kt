package com.example.demo.controller

import com.example.demo.model.Message
import com.example.demo.repository.MessageRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/messages")
class MessageController(private val repository: MessageRepository) {

    @GetMapping
    fun getAllMessages(): List<Message> = repository.findAll()

    @PostMapping
    fun createMessage(@RequestBody message: Message): Message = repository.save(message)

    @GetMapping("/{id}")
    fun getMessage(@PathVariable id: Long): ResponseEntity<Message> =
        repository.findById(id)
            .map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())

    @DeleteMapping("/{id}")
    fun deleteMessage(@PathVariable id: Long): ResponseEntity<Void> {
        return if (repository.existsById(id)) {
            repository.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
