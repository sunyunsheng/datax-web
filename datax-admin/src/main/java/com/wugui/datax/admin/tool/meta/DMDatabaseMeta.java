package com.wugui.datax.admin.tool.meta;


/**
 * 达梦数据库元数据信息管理
 * @author sunyunsheng
 * @version 1.0
 * @date 2021/5/13
 */
public class DMDatabaseMeta extends BaseDatabaseMeta {

    private volatile static DMDatabaseMeta single;
    public static DMDatabaseMeta getInstance()
    {
        if(single==null)
        {
            synchronized (DMDatabaseMeta.class){
                if(single==null)
                {
                    single = new DMDatabaseMeta();
                }
            }
        }
        return single;
    }

    /**
     * 获取表或字段的注释信息
     * @param schemaName 用户名
     * @param tableName 表名
     * @param columnName 列名
     * @return
     */
    @Override
    public String getSQLQueryComment(String schemaName, String tableName, String columnName) {
        return String.format("select B.comments \n" +
                "  from all_tab_columns A, all_col_comments B\n" +
                " where a.OWNER = b.OWNER\n" +
                "   and a.COLUMN_NAME = b.column_name\n" +
                "   and A.Table_Name = B.Table_Name\n" +
                "    and A.OWNER = upper('%s')\n" +
                "   and A.Table_Name = upper('%s')\n" +
                "   AND A.column_name  = '%s'", schemaName, tableName, columnName);
    }

    /**
     * 获取表的主键字段名,增加 cu.owner=au.owner条件限制 2021-05-13
     * @return
     */
    @Override
    public String getSQLQueryPrimaryKey() {
        return "select cu.column_name from all_cons_columns cu, all_constraints au where cu.constraint_name = au.constraint_name and  cu.owner = au.owner and au.owner = ? and au.constraint_type = 'P' and au.table_name = ?";
    }

    /**
     * 获取表注释，增加owner限制条件 2021-05-13 sunyunsheng
     * @return
     */
    @Override
    public String getSQLQueryTablesNameComments() {
        return "select table_name,comments from all_tab_comments where owner = ?";
    }

    /**
     * 获取指定表注释，增加owner限制条件 2021-05-13 sunyunsheng
     * @return
     */
    @Override
    public String getSQLQueryTableNameComment() {
        return "select table_name,comments from all_tab_comments where table_name = ? and owner=?";
    }

    /**
     * 获取指定用户下的所有表名和视图名，下面语句为同时获取对应的表和视图注释
     * select t.owner||'.'||t.table_name as table_name,comments from all_tables t,all_tab_comments t2 where t.owner='YBHYCJ' and t.OWNER=t2.OWNER and t.TABLE_NAME=t2.TABLE_NAME union select t3.owner||'.'||view_name as table_name,comments from all_views t3,all_tab_comments t4 where t3.owner='YBHYCJ' and t3.OWNER=t4.OWNER and t3.VIEW_NAME=t4.TABLE_NAME  order by table_name
     * @param tableSchema 用户名
     * @return
     */
    @Override
    public String getSQLQueryTables(String... tableSchema) {
        return "select owner||'.'||table_name as table_name from all_tables where owner='" + tableSchema[0] + "' " +
                "union " +
                "select owner||'.'||view_name as table_name from all_views where owner='" + tableSchema[0] + "'" +
                "order by table_name";
    }

    @Override
    public String getSQLQueryTableSchema(String... args) {
        return "select username from all_users order by username";
    }


    @Override
    public String getSQLQueryTables() {
        return "select table_name from all_tab_comments";
    }

    @Override
    public String getSQLQueryColumns(String... args) {
        return "select column_name from all_tab_columns where owner = ? and table_name = ?";
    }
}
