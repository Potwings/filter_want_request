package org.potwings.common;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.springframework.util.StreamUtils;

public class RequestWrapper extends HttpServletRequestWrapper {

  private String requestData;

  public RequestWrapper(HttpServletRequest request) throws IOException {
    super(request);
    requestData = requestDataByte(request);
  }

  private String requestDataByte(HttpServletRequest request) throws IOException {
    ServletInputStream inputStream = request.getInputStream();
    byte[] rawData = StreamUtils.copyToByteArray(inputStream);
    return new String(rawData);
  }

  @Override
  public ServletInputStream getInputStream() {
    ByteArrayInputStream inputStream = new ByteArrayInputStream(
        this.requestData.getBytes(StandardCharsets.UTF_8));
    return new ServletInputStream() {
      @Override
      public boolean isFinished() {
        return inputStream.available() == 0;
      }

      @Override
      public boolean isReady() {
        return true;
      }

      @Override
      public void setReadListener(ReadListener listener) {
        throw new UnsupportedOperationException();
      }

      @Override
      public int read() {
        return inputStream.read();
      }
    };
  }

  @Override
  public BufferedReader getReader() {
    return new BufferedReader(new InputStreamReader(this.getInputStream()));
  }
}
