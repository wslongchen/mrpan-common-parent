package com.mrpan.common.dms.fastdfs;

/**
 * Created by mrpan on 2016/11/6.
 */
public interface DownloadCallback
{
    /**
     * recv file content callback function, may be called more than once when the file downloaded
     * @param file_size file size
     *	@param data data buff
     * @param bytes data bytes
     * @return 0 success, return none zero(errno) if fail
     */
    public int recv(long file_size, byte[] data, int bytes);
}

