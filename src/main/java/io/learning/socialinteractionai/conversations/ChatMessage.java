package io.learning.socialinteractionai.conversations;

import java.time.LocalDateTime;

public record ChatMessage(String messageText, String authorId, LocalDateTime timestamp) {}

