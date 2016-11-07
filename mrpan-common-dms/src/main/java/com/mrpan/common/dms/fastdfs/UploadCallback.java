package com.mrpan.common.dms.fastdfs;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by mrpan on 2016/11/6.
 */
public interface UploadCallback
{
    /**
     * send file content callback function, be called only once when the file uploaded
     * @param out output stream for writing file content
     * @return 0 success, return none zero(errno) if fail
     */
    public int send(OutputStream out) throws IOException;
}

