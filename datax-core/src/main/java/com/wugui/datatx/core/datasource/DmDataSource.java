package com.wugui.datatx.core.datasource;


import com.wugui.datatx.core.enums.DbType;
import com.wugui.datatx.core.util.Constants;

/**
 *  达梦数据源
 *
 * @author sunyunsheng
 * @version 1.0
 * @date 2021/5/13
 */
public class DmDataSource extends BaseDataSource {

    /**
     * @return 达梦驱动类
     */
    @Override
    public String driverClassSelector() {
        return Constants.DM_JDBC_DRIVER_DMDRIVER;
    }

    /**
     * @return db type
     */
    @Override
    public DbType dbTypeSelector() {
        return DbType.DM;
    }
}
