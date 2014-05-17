package org.jboss.netty.example.localtime;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.jboss.netty.example.localtime.LocalTimeProtocol.Continent;
import org.jboss.netty.example.localtime.LocalTimeProtocol.Location;
import org.jboss.netty.example.localtime.LocalTimeProtocol.Locations;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class jbossprobuf {
	private static String HA_TEST_IP = "10.11.156.63";
	private static int HA_TEST_PORT = 9666;

	private static final Pattern DELIM = Pattern.compile("/");
      public static long n;
	public static ProtoBufClient client ;
	public static List<String> testClient2( String uuid) {
		List<String> response = null;
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
							.setCity(components[1]).setUuid(uuid).build());
			}
			 response = client.getResult(builder);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	public static void main(String[] args) throws Exception {
		 client = new ProtoBufClient(HA_TEST_IP, HA_TEST_PORT,
				"client1");
		String UUID = String.format("%s-%s-%s", client.getChannelId(),
				System.currentTimeMillis(),"1");
		Iterator<String> i2 = jbossprobuf.testClient2(UUID).iterator();
		int i=0;
		while (i2.hasNext()) {
			System.out.println(i2.next().toString());
			i++;
			System.out.print(i);
		}
	}
}
