package com.wugui.datax.admin.tool.datax.writer;


/**
 * 达梦数据库的写插件名称
 *
 * @author sunyunsheng
 * @version 1.0
 * @date 2021/5/13
 */
public class DMWriter extends BaseWriterPlugin implements DataxWriterInterface {

    /**
     * 获取writer插件名称
     *在datax中 达梦的读写插件归于 rdbmsreader 和 rdbmswriter,所以此处的writer插件名称返回rdbmswriter
     * @return
     */
    @Override
    public String getName() {
        return "rdbmswriter";
    }
}
