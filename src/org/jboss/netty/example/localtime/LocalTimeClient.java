/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.jboss.netty.example.localtime;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

/**
 * Sends a list of continent/city pairs to a {@link LocalTimeServer} to
 * get the local times of the specified cities.
 */
public class LocalTimeClient {

    private final String host;
    private final int port;
    private final Collection<String> cities;
    
    private static final Logger LOG = LoggerFactory.getLogger(LocalTimeClient.class);
    
    
    public LocalTimeClient(String host, int port, Collection<String> cities) {
        this.host = host;
        this.port = port;
        this.cities = new ArrayList<String>();
        this.cities.addAll(cities);
    }

    public void run() {
        // Set up.
        ClientBootstrap bootstrap = new ClientBootstrap(
                new NioClientSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));

        // Configure the event pipeline factory.
        bootstrap.setPipelineFactory(new LocalTimeClientPipelineFactory());
        bootstrap.setOption("tcpNoDelay" , true);  
        bootstrap.setOption("keepAlive", true); 
        // Make a new connection.
        ChannelFuture connectFuture =
            bootstrap.connect(new InetSocketAddress(host, port));

        // Wait until the connection is made successfully.
        Channel channel = connectFuture.awaitUninterruptibly().getChannel();

        // Get the handler instance to initiate the request.
        LocalTimeClientHandler handler =
            channel.getPipeline().get(LocalTimeClientHandler.class);
        
        boolean flag = true;
        while(flag){
        	 String uuid = String.format("%s-%s", channel.getId(),System.currentTimeMillis());
             // Request and get the response.
             List<String> response = handler.getLocalTimes(cities,uuid);
             if(response==null){
            	LOG.info( "response==null"+channel.getId());
             }
            	 
         	
//             // Close the connection.
//             channel.close().awaitUninterruptibly();
     //
//             // Shut down all thread pools to exit.
//             bootstrap.releaseExternalResources();

             // Print the response at last but not least.
             Iterator<String> i1 = cities.iterator();
             Iterator<String> i2 = response.iterator();
             while (i1.hasNext()) {
             	//String msg = String.format("%s,%s", i2.next(), i1.next());
             	String returnUuid = i2.next();
             	String returnInput =  i1.next();
             	if(!returnUuid.endsWith(uuid)){
                   	LOG.info( String.format("%s,%s,%s",uuid, returnUuid, returnInput));
                   	flag = false;
                   	break;
             	}
            	//LOG.info( String.format("%s,%s,%s",uuid, returnUuid, returnInput));
            	
       
                 //System.out.format("###%s,%s", i2.next(), i1.next());
             }
        }
       
    }

    public static void main(String[] args) throws Exception {
        // Print usage if necessary.
        if (args.length < 3) {
            printUsage();
            return;
        }

        // Parse options.
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        Collection<String> cities = parseCities(args, 2);
        if (cities == null) {
            return;
        }

        new LocalTimeClient(host, port, cities).run();
    }

    private static void printUsage() {
        System.err.println(
                "Usage: " + LocalTimeClient.class.getSimpleName() +
                " <host> <port> <continent/city_name> ...");
        System.err.println(
                "Example: " + LocalTimeClient.class.getSimpleName() +
                " localhost 8080 America/New_York Asia/Seoul");
    }

    private static final Pattern CITY_PATTERN = Pattern.compile("^[_A-Za-z]+/[_A-Za-z]+$");

    private static List<String> parseCities(String[] args, int offset) {
        List<String> cities = new ArrayList<String>();
        for (int i = offset; i < args.length; i ++) {
            if (!CITY_PATTERN.matcher(args[i]).matches()) {
                System.err.println("Syntax error: '" + args[i] + '\'');
                printUsage();
                return null;
            }
            cities.add(args[i].trim());
        }
        return cities;
    }
}
