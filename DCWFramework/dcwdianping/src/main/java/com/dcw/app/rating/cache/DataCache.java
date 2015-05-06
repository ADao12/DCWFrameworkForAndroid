package com.dcw.app.rating.cache;

import android.support.v4.util.LruCache;

import com.dcw.app.rating.app.RatingApplication;
import com.dcw.app.rating.db.bean.Cache;
import com.dcw.app.rating.db.dao.CacheDao;
import com.dcw.app.rating.log.L;

import java.util.List;

/**
 * @author JiaYing.Cheng
 * @version 1.0
 * @email adao12.vip@gmail.com
 * @create 15/5/6
 */
public class DataCache {

    private LruCache<String, Cache> mLruCache;
    CacheDao mCacheDAO = null;

    /**
     * 默认缓存大小100k
     */
    public final static int DEFAULT_CACHE_SIZE = 1024 * 100;

    /**
     * 数据库缓存
     */
    public String mCachePrefix = "cache_";

    /**
     * 延迟数据库删除缓存时间，使数据库缓存有效期大于内存缓存, 1天
     */
    public final static int DELETE_CACHE_DALAY_TIME = 7 * 86400;

    public DataCache(int cacheSizeInBytes) {

        if (cacheSizeInBytes < DEFAULT_CACHE_SIZE) {
            L.w("DataCache#Specified cache size is too small: %d, use default: %d", cacheSizeInBytes, DEFAULT_CACHE_SIZE);
            cacheSizeInBytes = DEFAULT_CACHE_SIZE;
        }

        mLruCache = new LruCache<String, Cache>(cacheSizeInBytes) {
            @Override
            protected int sizeOf(String key, Cache value) {
                return value.getValue().length() * 2;    // in java, char encoding is UTF16_BE, always 2 bytes
            }
        };

        mCacheDAO = RatingApplication.getInstance().getDaoSession().getCacheDao();
    }

    public void setCachePrefix(String prefix) {
        mCachePrefix = prefix;
    }

    /**
     * 保存数据到LruCache，不保存到数据库
     *
     * @param key     缓存key
     * @param value   缓存值
     * @param seconds 缓存时间，单位秒
     */
    public void set(String key, String value, int seconds) {
        set(key, value, seconds, false);
    }

    /**
     * 保存数据到LruCache，根据saveData可以指定是否保存到数据库
     *
     * @param key      缓存key
     * @param value    缓存值
     * @param seconds  缓存时间，单位秒
     * @param saveData true则保存到数据库，否则不保存
     */
    public void set(String key, String value, int seconds, boolean saveData) {
        long expireTime = System.currentTimeMillis() / 1000 + seconds;
        mLruCache.put(key, new Cache(value, expireTime, Cache.CACHE_FROM_MEMORY));

        if (saveData) {
            L.d("DataCache#Save to database: " + key);
            Cache dbCache = new Cache();
            dbCache.setKey(key);
            dbCache.setValue(value);
            dbCache.setExpireTime(expireTime);
            mCacheDAO.insertOrReplace(new Cache(key, value, expireTime));

        } else {
            L.d("DataCache#Do not save to database: " + key);
        }
    }

    /**
     * 通过key值获取缓存值
     * 1.当命中内存时，如果缓存失效则返回null
     * 2.当命中数据库时，不管缓存失效时间(保证数据库数据没那么快失效)
     *
     * @param key 缓存key
     * @return 缓存值
     */
    public Cache get(String key) {
        Cache entry = mLruCache.get(key);

        // 缓存获取
        if (entry != null) {
            if (entry.getExpireTime() < System.currentTimeMillis() / 1000) {
                mLruCache.remove(key);
                L.d("DataCache#The Key(" + key + ") of value is expire.");
                return null;
            }

            L.d("DataCache#Hit Memory Key: " + key);
            entry.type = Cache.CACHE_FROM_MEMORY;
            return entry;
        }

        // 数据库获取
        List<Cache> caches = mCacheDAO.queryBuilder().where(CacheDao.Properties.Key.eq(key)).list();
        if (caches != null && caches.size() > 0) {
            entry = caches.get(0);
        }
        if (entry != null) {
            if (entry.getExpireTime() > System.currentTimeMillis() / 1000) {
                mLruCache.put(key, entry);
            }

            L.d("DataCache#Hit Database Key: " + key);
            entry.type = Cache.CACHE_FROM_DATABASE;
            return entry;
        }

        L.d("DataCache#No Hit Cache Key: " + key);

        return null;
    }

    public Cache getNotCareExpire(String key) {
        Cache entry = mLruCache.get(key);

        // 缓存获取
        if (entry != null) {
            L.d("DataCache#Hit Memory Key: " + key);
            entry.type = Cache.CACHE_FROM_MEMORY;
            return entry;
        }

        // 数据库获取
        List<Cache> caches = mCacheDAO.queryBuilder().where(CacheDao.Properties.Key.eq(key)).list();
        if (caches != null && caches.size() > 0) {
            entry = caches.get(0);
        }
        if (entry != null) {
            L.d("DataCache#Hit Database Key: " + key);
            entry.type = Cache.CACHE_FROM_DATABASE;
            return entry;
        }

        L.d("DataCache#No Hit Cache Key: " + key);

        return null;
    }

    /**
     * 根据key值获取缓存值
     * 1.当命中缓存时，如果缓存失效则返回null
     * 2.当命中数据库时，如果缓存失效则返回null
     *
     * @param key 缓存key
     * @return 缓存值
     */
    public String getString(String key) {
        Cache entry = get(key);

        if (entry == null) {
            return null;
        }

        if (entry.type == Cache.CACHE_FROM_MEMORY) {
            return entry.getValue();

        } else {
            if (entry.getExpireTime() < System.currentTimeMillis() / 1000) {
                remove(key);
                return null;
            }

            return entry.getValue();
        }
    }

    /**
     * 删除内存和数据库缓存
     *
     * @param key
     * @return
     */
    public boolean remove(String key) {
        if (key != null) {
            mLruCache.remove(key);
            Cache cache = new Cache();
            cache.setKey(key);
            mCacheDAO.delete(cache);
            return true;
        }

        return false;
    }

    public void deleteExpiredCache() {
        List<Cache> caches = mCacheDAO.queryBuilder()
                .where(CacheDao.Properties.ExpireTime.lt(System.currentTimeMillis() / 1000 - DELETE_CACHE_DALAY_TIME))
                .list();
        mCacheDAO.deleteInTx(caches);
    }

    public String getCacheKey(String key) {
        return mCachePrefix + key;
    }
}
