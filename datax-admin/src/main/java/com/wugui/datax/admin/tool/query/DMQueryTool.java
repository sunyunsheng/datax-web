package com.wugui.datax.admin.tool.query;


import com.wugui.datatx.core.enums.DbType;

/**
 * 达梦数据库查询工具
 *
 * @author sunyunsheng
 * @version 1.0
 * @date 2021/5/13
 */
public class DMQueryTool extends BaseQueryTool implements QueryToolInterface{

    public DMQueryTool(DbType dbType, String parameter)
    {
        super(dbType,parameter);
    }
}
