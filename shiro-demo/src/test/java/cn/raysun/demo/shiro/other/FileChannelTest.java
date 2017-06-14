package cn.raysun.demo.shiro.other;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

public class FileChannelTest {
	String filePath = "D:/esignature/other-file/test1.txt";
	byte[] data = ("New String to write to file...").getBytes();
	
	@Test
	public void test1() throws Exception{
		long start = System.currentTimeMillis();
		
		//define channel
		RandomAccessFile aFile = new RandomAccessFile(filePath, "rw");
		FileChannel channel = aFile.getChannel();
		
		//define buffer
		ByteBuffer buf = ByteBuffer.allocate(48);
		buf.clear();
		buf.put(data);
		
		buf.flip();
		
		//write
		while(buf.hasRemaining()){
			channel.write(buf);
		}
		
		channel.close();
		aFile.close();
		long end = System.currentTimeMillis();
		System.out.println("nio cost time=" + (end - start));
	}
	
	@Test
	public void test2(){
		long start = System.currentTimeMillis();
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(filePath));
			fos.write(data);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		long end = System.currentTimeMillis();
		System.out.println("io cost time=" + (end - start));
	}
	
}
