package org.jboss.netty.example.localtime;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.ChannelGroupFuture;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.example.localtime.LocalTimeProtocol.Locations;
import org.jboss.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProtoBufClient {

	private static final Logger LOG = LoggerFactory.getLogger(ProtoBufClient.class);
	private static ChannelGroup group = new DefaultChannelGroup("ProtoBufClient-Group");
	private static String clientName ;

	private static final ClientBootstrap bootstrap  = new ClientBootstrap(
            new NioClientSocketChannelFactory(
                    Executors.newCachedThreadPool(),
                    Executors.newCachedThreadPool()));
	private Channel ch;
	

	public ProtoBufClient(String ip, int port, String name) throws InterruptedException {
		try {
//		 ClientBootstrap bootstrap = new ClientBootstrap(
//	                new NioClientSocketChannelFactory(
//	                        Executors.newCachedThreadPool(),
//	                        Executors.newCachedThreadPool()));

			clientName = name;
	        // Configure the event pipeline factory.
	        bootstrap.setPipelineFactory(new LocalTimeClientPipelineFactory());
	        bootstrap.setOption("tcpNoDelay" , true);  
	        bootstrap.setOption("keepAlive", true); 
	        // Make a new connection.
	        ChannelFuture connectFuture =
	            bootstrap.connect(new InetSocketAddress(ip, port));
	        
	        // Wait until the connection is made successfully.
	        ch = connectFuture.awaitUninterruptibly().getChannel();
	        group.add(ch);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public long getChannelId(){
		return ch.getId();
		
	}
	public List<String> getResult(Locations.Builder builder) {
		 // Get the handler instance to initiate the request.
        LocalTimeClientHandler handler =
            ch.getPipeline().get(LocalTimeClientHandler.class);
        
		// Request and get the response.
        List<String> result = handler.getResult(builder);
	
		return result;
	}

	public static void shutdown() throws Exception {
		try {
			ChannelGroupFuture future = group.close();
			future.awaitUninterruptibly();
			bootstrap.releaseExternalResources();
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info("ProtoBufClient Shutdown on  {} ......!",e.getMessage());
		}
		LOG.info("ProtoBufClient Shutdown on  {} ......!",clientName);
		System.exit(1);
	}
	public void close() {
		try {
			// Close the connection.
			ch.close();
		}finally {
			bootstrap.releaseExternalResources();
		}
	}
}
