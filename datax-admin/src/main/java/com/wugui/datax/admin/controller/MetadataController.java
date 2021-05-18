package com.wugui.datax.admin.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.wugui.datax.admin.service.DatasourceQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 查询数据库表名，字段的控制器
 *
 * @author jingwk
 * @ClassName MetadataController
 * @Version 2.1.2
 * @since 2020/05/31 20:48
 */
@RestController
@RequestMapping("api/metadata")
@Api(tags = "jdbc数据库查询控制器")
public class MetadataController extends BaseController {

    @Autowired
    private DatasourceQueryService datasourceQueryService;

    /**
     * 根据数据源id获取mongo库名
     *
     * @param datasourceId
     * @return
     */
    @GetMapping("/getDBs")
    @ApiOperation("根据数据源id获取mongo库名")
    public R<List<String>> getDBs(Long datasourceId) throws IOException {
        return success(datasourceQueryService.getDBs(datasourceId));
    }


    /**
     * 根据数据源id,dbname获取CollectionNames
     *
     * @param datasourceId
     * @return
     */
    @GetMapping("/collectionNames")
    @ApiOperation("根据数据源id,dbname获取CollectionNames")
    public R<List<String>> getCollectionNames(Long datasourceId,String dbName) throws IOException {
        return success(datasourceQueryService.getCollectionNames(datasourceId,dbName));
    }

    /**
     * 获取PG table schema
     *
     * @param datasourceId
     * @return
     */
    @GetMapping("/getDBSchema")
    @ApiOperation("根据数据源id获取 db schema")
    public R<List<String>> getDbSchema(Long datasourceId) {
        return success(datasourceQueryService.getDbSchema(datasourceId));
    }

    /**
     * 根据数据源id获取可用表名
     *
     * @param datasourceId
     * @return
     */
    @GetMapping("/getTables")
    @ApiOperation("根据数据源id获取可用表名")
    public R<List<String>> getTableNames(Long datasourceId,String tableSchema) throws IOException {
        return success(datasourceQueryService.getTables(datasourceId,tableSchema));
    }

    /**
     * 根据数据源id和表名获取所有字段
     *
     * @param datasourceId 数据源id
     * @param tableName    表名
     * @return
     */
    @GetMapping("/getColumns")
    @ApiOperation("根据数据源id和表名获取所有字段")
    public R<List<String>> getColumns(Long datasourceId, String tableName) throws IOException {
        return success(datasourceQueryService.getColumns(datasourceId, tableName));
    }

    /**
     * 根据数据源id和sql语句获取所有字段
     *
     * @param datasourceId 数据源id
     * @param querySql     表名
     * @return
     */
    @GetMapping("/getColumnsByQuerySql")
    @ApiOperation("根据数据源id和sql语句获取所有字段")
    public R<List<String>> getColumnsByQuerySql(Long datasourceId, String querySql) throws SQLException {
        return success(datasourceQueryService.getColumnsByQuerySql(datasourceId, querySql));
    }

    /**
     * 根据数据源id和schema、tablename获取指定表的主键字段
     * @param datasourceId 数据源id
     * @param schema schema
     * @param tableName 表名
     * @return 表的主键字段
     * @author sunyunsheng
     * @date 2021-05-17 17:25
     */
    @GetMapping("/getTablePrimaryKeys")
    @ApiOperation("根据数据源id和schema、tablename获取指定表的主键字段")
    public R<List<String>> getTablePrimaryKeys(Long datasourceId,String schema,String  tableName)
    {
        try
        {

           return  success(datasourceQueryService.getTablePrimaryKeys(datasourceId,schema,tableName));
        }catch (Exception e)
        {
            e.printStackTrace();
            return failed(e.getMessage());
        }

    }
}
