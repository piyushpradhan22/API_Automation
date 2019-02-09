package package1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Send_XML {

	public static void main(String[] args) {
		try {
			URL url_1 = new URL("http://www.holidaywebservice.com/HolidayService_v2/HolidayService2.asmx?op=GetCountriesAvailable");
			HttpURLConnection con = (HttpURLConnection) url_1.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");
			String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" + 
					"<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\r\n" + 
					"  <soap12:Body>\r\n" + 
					"    <GetCountriesAvailable xmlns=\"http://www.holidaywebservice.com/HolidayService_v2/\" />\r\n" + 
					"  </soap12:Body>\r\n" + 
					"</soap12:Envelope>";
			con.setDoOutput(true);
			DataOutputStream wr=new DataOutputStream(con.getOutputStream());
			wr.writeBytes(xml);
			wr.flush();
			wr.close();
			String resStatus= con.getResponseMessage();
			System.out.println(resStatus);
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer responce = new StringBuffer();
			while ((inputLine = in.readLine())!=null) {
				responce.append(inputLine);
			}
			in.close();
			System.out.println("Responce is : "+ responce.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
