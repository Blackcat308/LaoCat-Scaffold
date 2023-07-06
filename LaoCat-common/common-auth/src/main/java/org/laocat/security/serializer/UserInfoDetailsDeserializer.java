package org.laocat.security.serializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;
import org.laocat.auth.service.UserInfoDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.Set;

/**
 * @description:
 * @author: LaoCat
 * @date: 2023/7/6
 * @see org.springframework.security.core.userdetails.User
 */
public class UserInfoDetailsDeserializer extends JsonDeserializer<UserInfoDetails> {

    private static final TypeReference<Set<SimpleGrantedAuthority>> SIMPLE_GRANTED_AUTHORITY_SET = new TypeReference<Set<SimpleGrantedAuthority>>() {
    };

    @Override
    public UserInfoDetails deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode jsonNode = mapper.readTree(jp);

        String id = readJsonNode(jsonNode, "id").asText();
        String username = readJsonNode(jsonNode, "username").asText();
        JsonNode passwordNode = readJsonNode(jsonNode, "password");
        String password = passwordNode.asText("");
        Set<SimpleGrantedAuthority> authorities = mapper.convertValue(jsonNode.get("authorities"),
                SIMPLE_GRANTED_AUTHORITY_SET);
        boolean accountNonExpired = readJsonNode(jsonNode, "accountNonExpired").asBoolean();
        boolean accountNonLocked = readJsonNode(jsonNode, "accountNonLocked").asBoolean();
        boolean credentialsNonExpired = readJsonNode(jsonNode, "credentialsNonExpired").asBoolean();
        boolean enabled = readJsonNode(jsonNode, "enabled").asBoolean();

        UserInfoDetails result = new UserInfoDetails(id, username, password, authorities, accountNonExpired, credentialsNonExpired, accountNonLocked,
                enabled);
        return result;
    }

    private JsonNode readJsonNode(JsonNode jsonNode, String field) {
        return jsonNode.has(field) ? jsonNode.get(field) : MissingNode.getInstance();
    }
}
