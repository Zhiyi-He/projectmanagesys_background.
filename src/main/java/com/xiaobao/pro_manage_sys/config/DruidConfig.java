package com.xiaobao.pro_manage_sys.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

  @ConfigurationProperties("spring.datasource")
  @Bean
  public DataSource druid() {
    return new DruidDataSource();
  }

  @Bean
  public ServletRegistrationBean statViewServlet() {
    ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
    Map<String, String> initParams = new HashMap<>();

    initParams.put("loginUsername", "admin");
    initParams.put("loginPassword", "123456");
    initParams.put("allow", "");
    initParams.put("deny", "localhost");
    //        initParams.put("deny","192.168.1.107");

    bean.setInitParameters(initParams);

    return bean;
  }

  public FilterRegistrationBean webStatFilter() {
    FilterRegistrationBean bean = new FilterRegistrationBean();
    bean.setFilter(new WebStatFilter());
    Map<String, String> initParams = new HashMap<>();

    initParams.put("exclusions", "*.js,*.css,/druid/*");

    bean.setInitParameters(initParams);
    bean.setUrlPatterns(Arrays.asList("/*"));

    return bean;
  }
}
