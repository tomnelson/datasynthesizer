package org.dataguardians.openai.endpoints;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;
import org.dataguardians.openai.api.chat.ChatRequest;
import org.dataguardians.openai.api.chat.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an API endpoint request for the OpenAI Chat API.
 *
 * This class extends the ApiEndPointRequest class and provides functionality for sending requests to the OpenAI Chat
 * API endpoint. It includes a static API_ENDPOINT field which contains the URL of the Chat API endpoint. It also
 * overrides the getEndpoint method of the ApiEndPointRequest class to return the API_ENDPOINT value.
 */
@Data
@SuperBuilder
public class ChatApiEndpointRequest extends ApiEndPointRequest {

    /**
     * The URL of the OpenAI Chat API endpoint.
     */
    public static final String API_ENDPOINT = "https://api.openai.com/v1/chat/completions";

    /**
     * Overrides the getEndpoint method of the ApiEndPointRequest class to return the Chat API endpoint URL.
     *
     * @return The URL of the OpenAI Chat API endpoint.
     */
    @Override
    public String getEndpoint() {
        return API_ENDPOINT;
    }

    /**
     * Creates a new instance of the ChatApiEndpointRequest with the specified API key.
     *
     * This method is used to create a new instance of the ChatApiEndpointRequest with the specified API key. The API key
     * is required to send requests to the OpenAI Chat API endpoint. If the API key is invalid or not provided, an
     * IllegalArgumentException will be thrown.
     *
     * Example usage:
     *
     * <pre>{@code
     * ChatApiEndpointRequest endpoint = ChatApiEndpointRequest.create("my-api-key");
     * }</pre>
     *
     * @param apiKey The API key to use for requests to the OpenAI Chat API endpoint.
     * @return A new instance of the ChatApiEndpointRequest.
     * @throws IllegalArgumentException If the API key is null or empty.
     */
    @Override
    public Object create() {
        List<Message> messages = new ArrayList<>();
        String role = StringUtils.isBlank(user) ? "user" : user;
        messages.add(Message.builder().role(role).content(input).build());
        ChatRequest requestBody = ChatRequest.builder()
                .model("gpt-3.5-turbo")
                .user(role)
                .maxTokens(maxTokens)
                .messages(messages)
                .build();
        return requestBody;
    }

}
