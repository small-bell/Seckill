package com.smallbell.springcloud.j2cache.serializer;

import com.smallbell.springcloud.common.util.SerializationUtils;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.IOException;



public class J2CacheSerializer implements RedisSerializer<Object>{

	@Override
	public byte[] serialize(Object t) throws SerializationException {	
		try {
			return SerializationUtils.serialize(t);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object deserialize(byte[] bytes) throws SerializationException {
		try {
			return SerializationUtils.deserialize(bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
