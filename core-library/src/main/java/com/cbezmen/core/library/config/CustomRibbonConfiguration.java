package com.cbezmen.core.library.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.ConfigurationBasedServerList;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import feign.Client;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.ssl.SSLContexts;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.openfeign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;


/**
 * @author canbezmen
 */
@RibbonClients(
    value = {
        @RibbonClient(name = "address", configuration = InnerLocalRibbonConfiguration.class)
    }
)
@Profile("default")
public class CustomRibbonConfiguration {
}

@Configuration
class SSLConfiguration {
    private SSLSocketFactory getSSLSocketFactory() {
        try {
            TrustStrategy acceptingTrustStrategy = (chain, authType) -> true;

            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
            return sslContext.getSocketFactory();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    @Bean
    @ConditionalOnMissingBean
    public Client feignClient(CachingSpringLoadBalancerFactory cachingFactory, SpringClientFactory clientFactory) {
        return new LoadBalancerFeignClient(new Client.Default(getSSLSocketFactory(), new NoopHostnameVerifier()),
            cachingFactory, clientFactory);
    }
}

class InnerLocalRibbonConfiguration {
    @Bean
    public ServerList<Server> ribbonServerList(IClientConfig config) {
        ConfigurationBasedServerList serverList = new ConfigurationBasedServerList();
        serverList.initWithNiwsConfig(config);
        return serverList;
    }
}
