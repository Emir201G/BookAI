package com.app.bookai.ai.infrastructure.adapter;

import com.app.bookai.ai.domain.port.out.AIChatPort;
import com.app.bookai.ai.infrastructure.tool.AppointmentTool;
import com.app.bookai.ai.infrastructure.tool.BarberTool;
import com.app.bookai.ai.infrastructure.tool.CustomerTool;
import com.app.bookai.ai.infrastructure.tool.TreatmentTool;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AIChatAdapter implements AIChatPort {

    private final ChatClient chatClient;
    private final TreatmentTool treatmentTool;
    private final BarberTool barberTool;
    private final CustomerTool customerTool;
    private final AppointmentTool appointmentTool;

    @Value("classpath:prompts/bookai-system-prompt.st")
    private Resource systemPromptResource;

    @Override
    public String generateResponse(String message, Long conversationId) {

        try {

            return chatClient
                    .prompt()
                    .system(systemPromptResource)
                    .user(message)
                    .advisors(
                            advisor -> advisor.param(
                                    ChatMemory.CONVERSATION_ID,
                                    conversationId.toString()
                            )
                    )
                    .tools(
                            treatmentTool,
                            barberTool,
                            customerTool,
                            appointmentTool
                    )
                    .call()
                    .content();

        } catch (
                Exception e) {

            e.printStackTrace();

            throw e;
        }
    }
}