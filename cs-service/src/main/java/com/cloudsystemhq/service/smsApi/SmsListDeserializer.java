package com.cloudsystemhq.service.smsApi;

import com.cloudsystemhq.service.smsApi.SmsList.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class SmsListDeserializer extends JsonDeserializer<SmsList> {


  @Override
  public SmsList deserialize(com.fasterxml.jackson.core.JsonParser jsonParser,
      com.fasterxml.jackson.databind.DeserializationContext deserializationContext)
      throws IOException, JsonProcessingException {
    JsonNode node = jsonParser.readValueAsTree();
    String status = node.get("status").asText();
    Integer error = node.has("error") ? node.get("error").asInt() : 0;
    String message = node.has("message") ? node.get("message").asText() : "";
    List<Message> smsList = new ArrayList<>();
    if (node.has("messages")) {
      for (Iterator<Entry<String, JsonNode>> it = node.get("messages").fields(); it.hasNext(); ) {
        JsonNode messageNode = it.next().getValue();
        smsList.add(
            new Message(
                messageNode.get("id").asInt(),
                messageNode.get("bulk_id").asInt(),
                messageNode.get("sender").asText(),
                messageNode.get("recipient").asText(),
                messageNode.get("text").asText(),
                messageNode.get("send_datetime").asText(),
                messageNode.get("confirm_datetime").asText(),
                messageNode.get("status").asText(),
                messageNode.get("cost").asText(),
                messageNode.get("parts").asInt()
            ));
      }
    }
    return new SmsList(smsList, status, error, message);
  }
}