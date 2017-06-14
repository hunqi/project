package com.raysun.memcached.demo;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.utils.AddrUtil;

public class MemcachedClientTest {

	public static void main(String[] args) throws Exception {

		MemcachedClientBuilder builder = new XMemcachedClientBuilder(
				AddrUtil.getAddresses("localhost:11211"));
		MemcachedClient client = builder.build();

		String value = client.get("hello");
		System.out.println("value=" + value);
		client.set("hello", 0, "Hello, world");
		value = client.get("hello");
		System.out.println("value=" + value);
		client.delete("hello");
		value = client.get("hello");
		System.out.println("value=" + value);
		
		System.out.println("-----test append & prepend -----");
		
		client.set("hello", 0, "Hello, world");
		client.append("hello", ", my world");
		System.out.println(client.get("hello"));
		
		client.set("hello", 0, "world");
		System.out.println(client.get("hello"));
		
		client.prepend("hello", "Hello, ");
		System.out.println(client.get("hello"));
	}

}
