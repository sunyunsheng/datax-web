package com.wugui.datax.admin.tool.datax.reader;


/**
 * 达梦数据库的读插件名称
 *
 * @author sunyunsheng
 * @version 1.0
 * @date 2021/5/13
 */
public class DMReader extends  BaseReaderPlugin implements DataxReaderInterface {

    /**
     * 获取reader插件名称
     * 在datax中 达梦的读写插件归于 rdbmsreader 和 rdbmswriter,所以此处的reader插件名称返回rdbmsreader
     * @return
     */
    @Override
    public String getName() {
        return "rdbmsreader";
    }
}
