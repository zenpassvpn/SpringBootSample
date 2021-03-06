package com.tistory.heowc.web;

import com.tistory.heowc.domain.Message;
import com.tistory.heowc.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping("{idx}")
    public Mono<Message> findByOne(@PathVariable Long idx) {
        return Mono.fromCompletionStage(messageService.findByOne(idx));
    }

    @PostMapping
    public Mono<Message> insert(@RequestBody Mono<Message> message) {
        return Mono.fromCompletionStage(messageService.insert(message.block().getContent()));
    }

    @PutMapping
    public Mono<Message> update(@RequestBody Mono<Message> message) {
        return Mono.fromCompletionStage(messageService.update(message.block()));
    }

    @DeleteMapping("{idx}")
    public void deleteByIdx(@PathVariable Long idx) {
        messageService.delete(idx);
    }
}