package com.ep.http.method;

import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

public class PostMethodWithGzip extends PostMethod {

	public PostMethodWithGzip(String uri) {
		super(uri);
	}

	/**
	 * Get response as string whether response is GZipped or not
	 * 
	 * @return
	 * @throws IOException
	 */
	@Override
	public String getResponseBodyAsString() throws IOException {
		GZIPInputStream gzin;
		if (getResponseBody() != null || getResponseStream() != null) {

			if (getResponseHeader("Content-Encoding") != null
					&& getResponseHeader("Content-Encoding").getValue()
							.toLowerCase().indexOf("gzip") > -1) {
				// For GZip response
				InputStream is = getResponseBodyAsStream();
				gzin = new GZIPInputStream(is);

				InputStreamReader isr = new InputStreamReader(gzin,	getResponseCharSet());
				
				java.io.BufferedReader br = new java.io.BufferedReader(isr);
				StringBuffer sb = new StringBuffer();
				String tempbf;
				while ((tempbf = br.readLine()) != null) {
					sb.append(tempbf);
					sb.append("\r\n");
				}
				isr.close();
				gzin.close();
				return sb.toString();
			} else {
				// For deflate response
				return super.getResponseBodyAsString();
			}
		} else {
			return null;
		}
	}
}
