package LosOdiosos3.prueba_servidor.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;
import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.NetworkConfig;

@EnableCaching
@SpringBootApplication
@EnableHazelcastHttpSession
public class App {
    public static void main( String[] args ){    	
    	SpringApplication.run(App.class, args);
    }
    
    @Value("${hazelcast.member}")
	private String HAZELCAST_MEMBER;
    
    private static final Log LOG = LogFactory.getLog(App.class);

    @Bean
    public Config config() {
    	Config config = new Config();
    	NetworkConfig network = config.getNetworkConfig();
    	
    	JoinConfig joinConfig = config.getNetworkConfig().getJoin();
    	joinConfig.getMulticastConfig().setEnabled(false);
    	joinConfig.getTcpIpConfig().setEnabled(true).setMembers(Collections.singletonList(HAZELCAST_MEMBER));
    	network.getInterfaces().setEnabled(true).addInterface(HAZELCAST_MEMBER);
    	
    	return config;
    }
    
    @Bean
    public CacheManager cacheManager() {
    	LOG.info("Activating cache...");
    	return new ConcurrentMapCacheManager("games");
    }
}
