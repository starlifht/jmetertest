package com.test.probuf;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import com.sohu.passport.protocol.protobuf.Passport.Parameter;
import com.sohu.passport.protocol.protobuf.Passport.Result;

public class ProtoBufClient {

	private EventLoopGroup group;
	private Channel ch;
	private ProtoBufClientHandler handler;

	public ProtoBufClient(String ip, int port) throws InterruptedException {

		group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).handler(new ProtoBufClientInitializer());
			b .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,1000);
			// Make a new connection.
			ch = b.connect(ip, port).sync().channel();

			// Get the handler instance to initiate the request.
			handler = ch.pipeline().get(ProtoBufClientHandler.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Result getResult(Parameter.Builder builder) {
		// Request and get the response.
		Result result = handler.getResult(builder);
		return result;
	}

	public void close() {
		try {
			// Close the connection.
			ch.close();
		}finally {
			group.shutdownGracefully();
		}
	}
}
