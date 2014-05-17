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

public class ProtoBufAPITester {

	private static final Logger LOG = LoggerFactory
			.getLogger(ProtoBufAPITester.class);

	private static String HA_IP = "10.10.50.21";
	private static int HA_PORT = 8001;

	private static String NGINX_IP = " 10.11.50.196";
	private static int NGINX_PORT = 8001;

	private static String HA_TEST_IP = "10.11.156.63";
	private static int HA_TEST_PORT = 9666;

	private static final Pattern DELIM = Pattern.compile("/");

	@Test
	public void testClient() {
		try {
			ProtoBufClient client = new ProtoBufClient(HA_TEST_IP, HA_TEST_PORT,
					"client1");

			String data = "America/New_York";
			long n = 0;
			boolean flag = true;
			while (n<=10) {
				List<String> cities = new ArrayList<String>();
				
				cities.add(data+DELIM);
				Locations.Builder builder = Locations.newBuilder();
				String uuid = String.format("%s-%s-%s", client.getChannelId(),
						System.currentTimeMillis(),n++);
				for (String c : cities) {
					String[] components = DELIM.split(c);
					builder.addLocation(Location
							.newBuilder()
							.setContinent(
									Continent.valueOf(components[0]
											.toUpperCase()))
							.setCity(components[1]).setUuid(uuid).build());
				}

				List<String> response = client.getResult(builder);

				if (response == null) {
					LOG.info("response==null" + client.getChannelId());
				}

				// Print the response at last but not least.
				Iterator<String> i1 = cities.iterator();
				Iterator<String> i2 = response.iterator();
				while (i1.hasNext()) {
					// String msg = String.format("%s,%s", i2.next(),
					// i1.next());
					String returnUuid = i2.next();
					String returnInput = i1.next();
					if (!returnUuid.endsWith(uuid)) {
						LOG.info(String.format("%s,%s,%s", uuid, returnUuid,
								returnInput));
						flag = false;
						break;
					}
					//LOG.info( String.format("%s,%s,%s",uuid, returnUuid, returnInput));

					// System.out.format("###%s,%s", i2.next(), i1.next());
				}
			}

		} catch (Exception e) {

		}
	}
}

