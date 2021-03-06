// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.spring.sample.servicebus.queue.multibinders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.EmitterProcessor;

import javax.annotation.Resource;

@RestController
public class ServiceProducerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceProducerController.class);

    @Resource(name = "emitterProcessor1")
    private EmitterProcessor<Message<String>> emitterProcessor1;

    @Resource(name = "emitterProcessor2")
    private EmitterProcessor<Message<String>> emitterProcessor2;

    @PostMapping("/messages1")
    public ResponseEntity<String> sendMessage1(@RequestParam String message) {
        LOGGER.info("Going to add message {} to emitter1", message);
        emitterProcessor1.onNext(MessageBuilder.withPayload(message).build());
        return ResponseEntity.ok("Sent1!");
    }

    @PostMapping("/messages2")
    public ResponseEntity<String> sendMessage2(@RequestParam String message) {
        LOGGER.info("Going to add message {} to emitter2", message);
        emitterProcessor2.onNext(MessageBuilder.withPayload(message).build());
        return ResponseEntity.ok("Sent2!");
    }
}
