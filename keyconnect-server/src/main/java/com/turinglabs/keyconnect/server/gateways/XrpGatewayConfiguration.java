package com.turinglabs.keyconnect.server.gateways;

import com.keyconnect.rippled.api.spring.JacksonConfig;
import com.turinglabs.keyconnect.server.factories.configuration.YamlConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
@Import({JacksonConfig.class, YamlConfiguration.class})
public class XrpGatewayConfiguration {

  @Bean("XrpGateway")
  public XrpGateway xrpGateway(YamlConfiguration configuration,
      MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter) {
    return new XrpGateway(configuration,
        () -> JacksonConfig.constructRestTemplate(mappingJackson2HttpMessageConverter));
  }

}
