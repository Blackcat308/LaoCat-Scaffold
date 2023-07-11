package org.laocat.security.serializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;
import org.laocat.auth.service.sms.LaoCatSmsAuthenticationToken;

import java.io.IOException;

/**
 * @description: @see
 * @author: LaoCat
 * @date: 2023/7/11
 */
public class LaoCatSmsAuthenticationTokenDeserializer extends JsonDeserializer<LaoCatSmsAuthenticationToken> {

    @Override
    public LaoCatSmsAuthenticationToken deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode jsonNode = mapper.readTree(jp);

        String principal = readJsonNode(jsonNode, "principal").asText();
        String credentials = readJsonNode(jsonNode, "credentials").asText();

        return new LaoCatSmsAuthenticationToken(principal, credentials);
    }

    private JsonNode readJsonNode(JsonNode jsonNode, String field) {
        return jsonNode.has(field) ? jsonNode.get(field) : MissingNode.getInstance();
    }
}
