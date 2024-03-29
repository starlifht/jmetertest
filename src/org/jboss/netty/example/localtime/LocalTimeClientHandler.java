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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Formatter;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.example.localtime.LocalTimeProtocol.Continent;
import org.jboss.netty.example.localtime.LocalTimeProtocol.LocalTime;
import org.jboss.netty.example.localtime.LocalTimeProtocol.LocalTimes;
import org.jboss.netty.example.localtime.LocalTimeProtocol.Location;
import org.jboss.netty.example.localtime.LocalTimeProtocol.Locations;

public class LocalTimeClientHandler extends SimpleChannelUpstreamHandler {

	
    private static final Logger logger = Logger.getLogger(
            LocalTimeClientHandler.class.getName());

    private static final Pattern DELIM = Pattern.compile("/");
    // Stateful properties
    private volatile Channel channel;
    private final BlockingQueue<LocalTimes> answer = new LinkedBlockingQueue<LocalTimes>();
  
    
    public List<String> getResult(Locations.Builder builder){

        channel.write(builder.build());

        LocalTimes localTimes = null;
        boolean interrupted = false;
       // for (;;) {
            try {
                //localTimes = answer.take();
                localTimes= answer.poll(2, TimeUnit.MILLISECONDS);
                if(localTimes==null){
                	System.out.println("localTimes==null");
                	return null;
                }
               // break;
            } catch (InterruptedException e) {
                interrupted = true;
            }
     //   }

        if (interrupted) {
            Thread.currentThread().interrupt();
        }

        List<String> result = new ArrayList<String>();
        for (LocalTime lt: localTimes.getLocalTimeList()) {
          result.add(
          new Formatter().format(
                  "%s",
                  lt.getUuid()).toString());
//            result.add(
//                    new Formatter().format(
//                            "[%s]%4d-%02d-%02d %02d:%02d:%02d %s",
//                            lt.getUuid(),
//                            lt.getYear(),
//                            lt.getMonth(),
//                            lt.getDayOfMonth(),
//                            lt.getHour(),
//                            lt.getMinute(),
//                            lt.getSecond(),
//                            lt.getDayOfWeek().name()).toString());
        }

        return result;
    }
    
    public List<String> getLocalTimes(Collection<String> cities, String uuid) {
        Locations.Builder builder = Locations.newBuilder();
        //String uuid = String.format("%s-%s", channel.getId(),System.currentTimeMillis());
        for (String c: cities) {
            String[] components = DELIM.split(c);
            builder.addLocation(Location.newBuilder().
                setContinent(Continent.valueOf(components[0].toUpperCase())).
                setCity(components[1]).
                setUuid(uuid).build());
        
        }

        channel.write(builder.build());

        LocalTimes localTimes;
        boolean interrupted = false;
        for (;;) {
            try {
                localTimes = answer.take();
                break;
            } catch (InterruptedException e) {
                interrupted = true;
            }
        }

        if (interrupted) {
            Thread.currentThread().interrupt();
        }

        List<String> result = new ArrayList<String>();
        for (LocalTime lt: localTimes.getLocalTimeList()) {
          result.add(
          new Formatter().format(
                  "%s",
                  lt.getUuid()).toString());
//            result.add(
//                    new Formatter().format(
//                            "[%s]%4d-%02d-%02d %02d:%02d:%02d %s",
//                            lt.getUuid(),
//                            lt.getYear(),
//                            lt.getMonth(),
//                            lt.getDayOfMonth(),
//                            lt.getHour(),
//                            lt.getMinute(),
//                            lt.getSecond(),
//                            lt.getDayOfWeek().name()).toString());
        }

        return result;
    }

    @Override
    public void handleUpstream(
            ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
        if (e instanceof ChannelStateEvent) {
            logger.info(e.toString());
        }
        super.handleUpstream(ctx, e);
    }

    @Override
    public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e)
            throws Exception {
        channel = e.getChannel();
        super.channelOpen(ctx, e);
    }

    @Override
    public void messageReceived(
            ChannelHandlerContext ctx, final MessageEvent e) {
        boolean offered = answer.offer((LocalTimes) e.getMessage());
        assert offered;
    }

    @Override
    public void channelClosed(ChannelHandlerContext ctx,
            ChannelStateEvent e){
    	try {
			ProtoBufClient.shutdown();
		} catch (Exception e1) {
			
			e1.printStackTrace();
			logger.info("close,close,close,{}"+e1.getMessage());
				
		}
    	logger.info("close,close,close");
    	
    }
              
    @Override
    public void exceptionCaught(
            ChannelHandlerContext ctx, ExceptionEvent e) {
    	logger.info("123123213213");
        logger.log(
                Level.WARNING,
                "Unexpected exception from downstream.",
                e.getCause());
        e.getChannel().close();
    }
}
