package com.jmeter.probuf;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.jboss.netty.example.localtime.jbossprobuf;
import org.jboss.netty.example.localtime.ProtoBufClient;
import org.jboss.netty.example.localtime.LocalTimeProtocol.Continent;
import org.jboss.netty.example.localtime.LocalTimeProtocol.Location;
import org.jboss.netty.example.localtime.LocalTimeProtocol.Locations;


public class jbosstest extends AbstractJavaSamplerClient {
	ProtoBufClient client;
//	Method method;
//	String status;
	//String methodName;
	public void setupTest(JavaSamplerContext arg0) {
		try {
			
			client = new ProtoBufClient(arg0.getParameter("ip"), Integer.parseInt(arg0.getParameter("port")),Thread.currentThread().getName());
				
			//methodName= arg0.getParameter("methodName");
		
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
	}

	public void teardownTest(JavaSamplerContext arg0) {
	
		 client.close();
		
	}

	@Override
	public Arguments getDefaultParameters() {
		Arguments args = new Arguments();
		//args.addArgument("userid", "probuftest${__counter(TRUE,)}@sohu.com");///s${__RandomString(8,zxcvbnmkjhgdfawt56io7890,)}@sohu.com
	//	args.addArgument("methodName", "");//1${__Random(11111,99999,)}${__Random(11111,99999,)}
		args.addArgument("ip", "10.11.156.63");//probuftest${__Random(1,1000,)}@sohu.com
		args.addArgument("port", "9666");
		//args.addArgument("status", "200");
		//args.addArgument("counter","${__counter(TRUE,)}");
		return args; 
	
	
	}

	@Override
	public SampleResult runTest(JavaSamplerContext arg0) {

		SampleResult sr = new SampleResult();
		String time=String.valueOf(System.currentTimeMillis());
		//String counter=arg0.getParameter("counter");
		final Pattern DELIM = Pattern.compile("/");
		String counter=String.valueOf(jbossprobuf.n++);
		sr.sampleStart();
		sr.setSuccessful(false);
		
		List<String> response = null;
		String UUID = String.format("%s-%s-%s", client.getChannelId(),
				time,counter);
	//System.out.println(UUID);
		//String phone=Tools.getPhoneNum();
		try {
			
			
				String data = "America/New_York";
				List<String> cities = new ArrayList<String>();				
				cities.add(data+DELIM);
				Locations.Builder builder = Locations.newBuilder();			
				for (String c : cities) {
					String[] components = DELIM.split(c);
						builder.addLocation(Location
								.newBuilder()
								.setContinent(
										Continent.valueOf(components[0]
												.toUpperCase()))
								.setCity(components[1]).setUuid(UUID).build());
				}
				 response = client.getResult(builder);

			if (response == null) {
				System.out.println("response==null" + client.getChannelId());
			}else{
			Iterator<String> i2 = response.iterator();
			while (i2.hasNext()) {
				// String msg = String.format("%s,%s", i2.next(),
				// i1.next());
				String returnUuid = i2.next();
				if(returnUuid.indexOf(time)!=-1){
					sr.setSuccessful(true);	
				}else{
					System.out.println("sohuerror"+UUID+"|"+returnUuid);
				}

				}
			}
			//sr.setSuccessful(true);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
			
		}
		sr.sampleEnd();
		return sr;
	}


}
