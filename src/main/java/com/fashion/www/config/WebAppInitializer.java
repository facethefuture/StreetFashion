package com.fashion.www.config;

import java.nio.charset.StandardCharsets;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

//public class WebAppInitializer implements WebApplicationInitializer{
//
//	 /**
//     * 配置其他的 servlet 和 filter
//     *
//     * @param servletContext
//     * @throws ServletException
//     */
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
//        encodingFilter.setInitParameter("encoding", String.valueOf(StandardCharsets.UTF_8));
//        encodingFilter.setInitParameter("forceEncoding", "true");
//        encodingFilter.addMappingForUrlPatterns(null, false, "/*");
//    }
//
//}
