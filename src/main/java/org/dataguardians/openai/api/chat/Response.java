package org.dataguardians.openai.api.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * Response body for ChatGPT API.
 * </p>
 * see: <a href="https://platform.openai.com/docs/api-reference/chat">https://platform.openai.com/docs/api-reference/chat</a>
 *
 * taken from
 * @author <a href="https://github.com/LiLittleCat">LiLittleCat</a>
 * @since 2023/3/2
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response {


    @JsonProperty(value = "id")
    public String id;
    @JsonProperty(value = "object")
    public String object;
    @JsonProperty(value = "created")
    public Long created;
    @JsonProperty(value = "model")
    public String model;
    @JsonProperty(value = "choices")
    public List<Choice> choices;
    @JsonProperty(value = "usage")
    public Usage usage;

    @Data
    public static class Choice {
        @JsonProperty(value = "index")
        public Integer index;
        @JsonProperty(value = "message")
        public Message message;
        @JsonProperty(value = "finish_reason")
        public String finishReason;
    }


    @Data
    public static class Usage {
        @JsonProperty(value = "prompt_tokens")
        public Integer promptTokens;
        @JsonProperty(value = "completion_tokens")
        public Integer completionTokens;
        @JsonProperty(value = "total_tokens")
        public Integer totalTokens;
    }

    public String concatenateResponses(){
        return getChoices().stream().map( x -> x.getMessage().getContent() ).collect(Collectors.joining());
    }

}
