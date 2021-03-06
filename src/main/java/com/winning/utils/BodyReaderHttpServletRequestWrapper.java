package com.winning.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Enumeration;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 用于将request重新进行封装
 *
 * 
 */
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper
{
    private final byte[] body;

    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException
    {
        super(request);
        body = HttpHelper.getBodyString(request).getBytes(Charset.forName("UTF-8"));
    }

    @Override
    public BufferedReader getReader() throws IOException
    {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException
    {

        final ByteArrayInputStream bais = new ByteArrayInputStream(body);

        return new ServletInputStream()
        {
            @Override
            public int read() throws IOException
            {
                return bais.read();
            }

            @Override
            public boolean isFinished()
            {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean isReady()
            {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public void setReadListener(ReadListener arg0)
            {
                // TODO Auto-generated method stub
                
            }
        };
    }
    
    @Override
    public String getParameter(String name)
    {
        // TODO Auto-generated method stub
        return super.getParameter(name);
    }

    @Override
    public String getHeader(String name)
    {
        return super.getHeader(name);
    }

    @Override
    public Enumeration<String> getHeaderNames()
    {
        return super.getHeaderNames();
    }

    @Override
    public Enumeration<String> getHeaders(String name)
    {
        return super.getHeaders(name);
    }
}
