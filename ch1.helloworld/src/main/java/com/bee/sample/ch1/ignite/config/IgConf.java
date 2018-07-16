package com.bee.sample.ch1.ignite.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bee.sample.ch1.ignite.vo.Book;


@Configuration
public class IgConf {
	
	@Bean("ignite")
//	@Bean
	public Ignite igniteInstance(){
		Ignite ignite = Ignition.start("conf/igConf.xml");
        ignite.active(true);
        return ignite;
		
	}
	
	@Bean("igCache")
	public IgniteCache<Integer, Book>  getBookCache(Ignite ignite){
		CacheConfiguration<Integer, Book> cacheCfg = new CacheConfiguration<>(Book.class.getSimpleName()+ "Cache");
		cacheCfg.setIndexedTypes(Integer.class,Book.class);
		 IgniteCache<Integer, Book> BookCache = ignite.getOrCreateCache(cacheCfg);

		return BookCache;
		
	}
	

}
