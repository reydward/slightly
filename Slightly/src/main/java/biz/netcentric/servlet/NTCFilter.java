package biz.netcentric.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import biz.netcentric.parser.HTMLJsoupParser;

/**
 * Servlet Filter implementation class FilterTest in order to do a specific
 * behavior to *.ntc templates
 */
@WebFilter("/NTCFilter")
public class NTCFilter implements Filter {

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HTMLWrapper responseWrapper = new HTMLWrapper((HttpServletResponse) response);
        responseWrapper.addHeader("bypass-cache", "true");
        responseWrapper.addHeader("cache-control", "no-cache");
        chain.doFilter(request, responseWrapper);

        HTMLJsoupParser htmlParser = new HTMLJsoupParser();

        StringBuffer requestURL = httpRequest.getRequestURL();
        if (httpRequest.getQueryString() != null) {
            requestURL.append("?").append(httpRequest.getQueryString());
        }

        String url = requestURL.toString();
        String realPath = httpRequest.getServletContext().getRealPath(httpRequest.getRequestURI());

        String finalHTML = htmlParser.doParse(url, realPath);
        response.getOutputStream().write(finalHTML.getBytes());
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}
