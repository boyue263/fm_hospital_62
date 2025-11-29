package com.hospital.wx.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisTemplateConfigWx {
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();

        // 设置键的序列化器
        template.setKeySerializer(new StringRedisSerializer());

        // 设置哈希键的序列化器
        template.setHashKeySerializer(new StringRedisSerializer());

        // 设置哈希值的序列化器
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        // 设置连接工厂
        template.setConnectionFactory(factory);

        // 启用事务支持
//        template.setEnableTransactionSupport(true);

        return template;
    }
}
