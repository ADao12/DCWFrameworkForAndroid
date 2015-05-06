package com.dcw.dao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class RatingDaoGenerator {

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema( 1, "com.dcw.app.rating.db.bean");
        // 1: 数据库版本号
        // com.xxx.bean:自动生成的Bean对象会放到/java-gen/com/xxx/bean中

        schema.setDefaultJavaPackageDao("com.dcw.app.rating.db.dao");
        schema.enableKeepSectionsByDefault();
        // DaoMaster.java、DaoSession.java、BeanDao.java会放到/java-gen/com/xxx/dao中

        // 上面这两个文件夹路径都可以自定义，也可以不设置

        initCacheDao(schema); // 初始化Bean了

        new DaoGenerator().generateAll(schema, "/Users/DaoCaoWu/Documents/Github/ADao12/DCWFrameworkForAndroid/DCWFramework/dcwdianping/src/main/java");// 自动创建
    }

    private static void initCacheDao(Schema schema) {
        Entity box = schema.addEntity("Cache");
        box.setTableName("cache");
        box.addIntProperty("groupId");
        box.addStringProperty("key").primaryKey();
        box.addStringProperty("value");
        box.addLongProperty("expireTime");
    }

    private static void initBoxBean(Schema schema) {
        Entity box = schema.addEntity("Box");
        box.addIdProperty();
        box.addStringProperty("name");
        box.addIntProperty("slots");
        box.addStringProperty("description");
    }

    private static void initUserBean(Schema schema) {
        Entity userBean = schema.addEntity("UserBean");// 表名
        userBean.setTableName("user"); // 可以对表重命名
        userBean.addStringProperty("id").primaryKey().index();// 主键，索引
        userBean.addStringProperty("phone");
        userBean.addStringProperty("profile_picture");
        userBean.addStringProperty("client_id");
        userBean.addStringProperty("name");
        userBean.addStringProperty("location");
        userBean.addStringProperty("gender");
    }
}
