package com.temenos.jbpm.humantask.callback;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.infinispan.Cache;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.eviction.EvictionStrategy;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.transaction.LockingMode;
import org.infinispan.transaction.TransactionMode;

public class UserGroupCallbackCacheProvider implements IUserGroupCallbackCacheProvider {
   private Cache<String, Object> cache = null;
   private boolean isExpire = false;
   private UserGroupCallbackCacheProvider.CacheProviderBuilder cacheProviderBuilder;
   private static final String CACHE_NAME = "CallbackCache";

   public UserGroupCallbackCacheProvider() {
      this.initDefault();
   }

   public UserGroupCallbackCacheProvider(ICacheProviderConfiguration cacheProviderBuilder) throws IOException {
      this.cacheProviderBuilder = (UserGroupCallbackCacheProvider.CacheProviderBuilder)cacheProviderBuilder;
      this.init();
   }

   private void initDefault() {
      this.cacheProviderBuilder = new UserGroupCallbackCacheProvider.CacheProviderBuilder();
      Configuration config = (new ConfigurationBuilder()).clustering().cacheMode(CacheMode.LOCAL).eviction().maxEntries(this.cacheProviderBuilder.getMaxEntities()).strategy(this.cacheProviderBuilder.getEvictionStrategy()).expiration().wakeUpInterval((long)this.cacheProviderBuilder.getWakeUpInterval(), TimeUnit.SECONDS).lifespan((long)this.cacheProviderBuilder.getLifespan(), TimeUnit.SECONDS).maxIdle((long)this.cacheProviderBuilder.getMaxIdle(), TimeUnit.SECONDS).transaction().transactionMode(TransactionMode.TRANSACTIONAL).lockingMode(this.cacheProviderBuilder.getTransactionLocking()).build();
      this.cache = (new DefaultCacheManager(config)).getCache(this.cacheProviderBuilder.getName());
   }

   private void init() throws IOException {
      if (!this.cacheProviderBuilder.getConfigFile().isEmpty()) {
         this.cache = (new DefaultCacheManager(this.cacheProviderBuilder.getConfigFile())).getCache(this.cacheProviderBuilder.getName());
      } else {
         this.cache = this.localCacheManager().getCache(this.cacheProviderBuilder.getConfigFile());
      }

   }

   private DefaultCacheManager localCacheManager() {
      GlobalConfiguration globalConfig = (new GlobalConfigurationBuilder()).nonClusteredDefault().globalJmxStatistics().allowDuplicateDomains(true).enable().build();
      Configuration config = (new ConfigurationBuilder()).clustering().cacheMode(CacheMode.LOCAL).eviction().maxEntries(this.cacheProviderBuilder.getMaxEntities()).strategy(this.cacheProviderBuilder.getEvictionStrategy()).expiration().wakeUpInterval((long)this.cacheProviderBuilder.getWakeUpInterval(), TimeUnit.SECONDS).lifespan((long)this.cacheProviderBuilder.getLifespan(), TimeUnit.SECONDS).maxIdle((long)this.cacheProviderBuilder.getMaxIdle(), TimeUnit.SECONDS).transaction().transactionMode(TransactionMode.TRANSACTIONAL).lockingMode(this.cacheProviderBuilder.getTransactionLocking()).build();
      return new DefaultCacheManager(globalConfig, config);
   }

   public String getCacheName() {
      return "CallbackCache";
   }

   public void setExpire(boolean isExpire) {
      this.isExpire = isExpire;
   }

   public boolean isExpire() {
      return this.isExpire;
   }

   public void put(String key, Object value) {
      this.cache.put(key, value);
   }

   public Object getCacheValue(String key) {
      return this.cache.get(key);
   }

   public ICacheProviderConfiguration getCacheProviderConfiguration() {
      return this.cacheProviderBuilder;
   }

   public Object getCache() {
      return this.cache;
   }

   public static ICacheProviderConfiguration getCacheProviderBuilder() {
      return new UserGroupCallbackCacheProvider.CacheProviderBuilder();
   }

   private static class CacheProviderBuilder implements ICacheProviderConfiguration {
      private int maxEntities;
      private int wakeUpInterval;
      private int maxIdle;
      private int lifespan;
      private EvictionStrategy evictionStrategy;
      private LockingMode transactionLocking;
      private String name;
      private String configFile;

      private CacheProviderBuilder() {
         this.maxEntities = 10000;
         this.wakeUpInterval = 5;
         this.maxIdle = 10;
         this.lifespan = 10;
         this.evictionStrategy = EvictionStrategy.LIRS;
         this.transactionLocking = LockingMode.OPTIMISTIC;
         this.name = "CallbackCache";
         this.configFile = "";
      }

      public String getConfigFile() {
         return this.configFile;
      }

      public String getName() {
         return this.name;
      }

      public int getMaxEntities() {
         return this.maxEntities;
      }

      public int getWakeUpInterval() {
         return this.wakeUpInterval;
      }

      public int getMaxIdle() {
         return this.maxIdle;
      }

      public int getLifespan() {
         return this.lifespan;
      }

      public EvictionStrategy getEvictionStrategy() {
         return this.evictionStrategy;
      }

      public LockingMode getTransactionLocking() {
         return this.transactionLocking;
      }

      public UserGroupCallbackCacheProvider.CacheProviderBuilder setConfigFile(String _configFile) {
         if (!this.isNullOrEmtpty(_configFile)) {
            this.configFile = _configFile;
         }

         return this;
      }

      public UserGroupCallbackCacheProvider.CacheProviderBuilder setName(String _name) {
         if (!this.isNullOrEmtpty(_name)) {
            this.name = _name;
         }

         return this;
      }

      public UserGroupCallbackCacheProvider.CacheProviderBuilder setMaxEntities(String string) {
         this.maxEntities = Integer.parseInt(string);
         return this;
      }

      public UserGroupCallbackCacheProvider.CacheProviderBuilder setWakeUpInterval(String string) {
         this.wakeUpInterval = Integer.parseInt(string);
         return this;
      }

      public UserGroupCallbackCacheProvider.CacheProviderBuilder setMaxIdle(String string) {
         this.maxIdle = Integer.parseInt(string);
         return this;
      }

      public UserGroupCallbackCacheProvider.CacheProviderBuilder setLifespan(String string) {
         this.lifespan = Integer.parseInt(string);
         return this;
      }

      public String toString() {
         return "[maxEntries: " + this.getMaxEntities() + ", lifespan: " + this.getLifespan() + "s, maxIdle: " + this.getMaxIdle() + "s, wakeUpInterval: " + this.getWakeUpInterval() + "s, evicationStrategy: " + this.getEvictionStrategy().name() + ", caheName: " + this.getName() + ", configFile: " + this.getConfigFile() + ", transactionType: " + this.getTransactionLocking().name() + "]";
      }

      public UserGroupCallbackCacheProvider.CacheProviderBuilder setEvictionStrategy(String _evictionStrategy) {
         if (this.isNullOrEmtpty(_evictionStrategy)) {
            return this;
         } else {
            if (_evictionStrategy.equals(EvictionStrategy.LRU.name())) {
               this.evictionStrategy = EvictionStrategy.LRU;
            }

            return this;
         }
      }

      public UserGroupCallbackCacheProvider.CacheProviderBuilder setTransactionLocking(String _transactionLocking) {
         if (this.isNullOrEmtpty(_transactionLocking)) {
            return this;
         } else {
            if (_transactionLocking.equals(LockingMode.PESSIMISTIC.name())) {
               this.transactionLocking = LockingMode.PESSIMISTIC;
            }

            return this;
         }
      }

      private boolean isNullOrEmtpty(String value) {
         return value == null || value.isEmpty();
      }

      public IUserGroupCallbackCacheProvider build() throws IOException {
         return new UserGroupCallbackCacheProvider(this);
      }

      // $FF: synthetic method
      CacheProviderBuilder(Object x0) {
         this();
      }
   }
}
