package org.laocat.security.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.laocat.security.serializer.mixin.HashSetMixin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.jackson2.CoreJackson2Module;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.security.web.jackson2.WebJackson2Module;
import org.springframework.security.web.jackson2.WebServletJackson2Module;
import org.springframework.security.web.server.jackson2.WebServerJackson2Module;

import java.util.HashSet;

/**
 * @description: 自定义 redis 序列化 已支持security 相关实例反序列化
 * @author: LaoCat
 * @date: 2023/7/6
 */
@Configuration
public class CustomRedisConfig {

    private final ObjectMapper objectMapper;

    @Autowired
    public CustomRedisConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    public RedisSerializer<Object> redisSerializer() {
        ObjectMapper mapper = objectMapper.copy();

        mapper.registerModule(new CoreJackson2Module());
        mapper.registerModule(new WebJackson2Module());
        mapper.registerModule(new WebServerJackson2Module());
        mapper.registerModules(new WebServletJackson2Module());
        /*
         * 仿照security进行反序列化 解决报错
         * Could not read JSON:
         * The class with org.laocat.auth.service.UserInfoDetails and name of org.laocat.auth.service.UserInfoDetails is not in the allowlist.
         * If you believe this class is safe to deserialize, please provide an explicit mapping using Jackson annotations or by providing a Mixin.
         *  If the serialization is only done by a trusted source, you can also enable default typing.
         * */
        mapper.registerModules(new UserInfoDetailsJackson2Module());
        mapper.addMixIn(HashSet.class, HashSetMixin.class);

        SecurityJackson2Modules.enableDefaultTyping(mapper);
        return new GenericJackson2JsonRedisSerializer(mapper);
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setDefaultSerializer(redisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


}
