package com.test.probuf;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.ChannelHandler.Sharable;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.sohu.passport.protocol.protobuf.Passport.Parameter;
import com.sohu.passport.protocol.protobuf.Passport.Result;

//@Sharable
public class ProtoBufClientHandler extends SimpleChannelInboundHandler<Result> {

	private String isValue;
	
	private String resultValue;
	
	
    // Stateful properties
    private volatile Channel channel;
    private final BlockingQueue<Result> answer = new LinkedBlockingQueue<Result>();
    public ProtoBufClientHandler() {
        super(false);
    }
   // public synchronized Result getResult(Parameter.Builder builder) {
    public  Result getResult(Parameter.Builder builder) {
    	System.out.println(Thread.currentThread().getName() + " 1");
    	
        channel.writeAndFlush(builder.build());
        
        resultValue = builder.build().getData();
        
        Result result = null;
        
		boolean interrupted = false;
		
	//	for(;;){
			try {
				result=answer.take();
				//result = answer.poll(timeout,TimeUtil);
				//break;
			} catch (InterruptedException e) {
				interrupted = true;
			}
	//	}
		if (interrupted) {
			Thread.currentThread().interrupt();
         }

	//	System.out.println(Thread.currentThread().getName() + " 2");
        return result;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        channel = ctx.channel();
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Result result) throws Exception {
    	
        answer.add(result);
        System.out.println(Thread.currentThread().getName() + " 3");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

}
