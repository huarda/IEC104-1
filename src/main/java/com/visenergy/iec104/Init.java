package com.visenergy.iec104;

import com.flying.jdbc.SqlHelper;
import com.flying.jdbc.util.DBConnectionPool;
import com.visenergy.iec104.util.FileUtils;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Properties;

/**
 * Created by Administrator on 2017/7/25 0025.
 */
public class Init {
    private static Log log = LogFactory.getLog(Init.class);

    public static Properties typeIdProp = null;
    public static Properties causeProp = null;
    public static JSONObject ycJsonObj = null;
    public static JSONObject yxJsonObj = null;

    public static void  start(){
        initDb();
        initBusinessData();
        DataProcessPool.initPool();
    }

    public static void initDb(){
        log.debug("建立数据库连接池，连接数量是10");
        SqlHelper.connPool = new DBConnectionPool(10);
    }

    public static void initBusinessData(){
        try {
            log.debug("解析配置文件");
            typeIdProp = FileUtils.loadPropFile("typeId.properties");
            causeProp = FileUtils.loadPropFile("cause.properties");
            ycJsonObj = FileUtils.loadJsonFile("yc.json");
            yxJsonObj = FileUtils.loadJsonFile("yx.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
