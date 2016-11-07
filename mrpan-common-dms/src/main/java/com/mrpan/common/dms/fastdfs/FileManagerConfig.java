package com.mrpan.common.dms.fastdfs;

import java.io.Serializable;

/**
 * Created by mrpan on 2016/11/6.
 */
public interface FileManagerConfig extends Serializable {

    public static final String FILE_DEFAULT_WIDTH = "900";
    public static final String FILE_DEFAULT_HEIGHT = "900";
    public static final String FILE_DEFAULT_AUTHOR = "aracwong";

    public static final String PROTOCOL = "http://";
    public static final String SEPARATOR = "/";

    public static final String TRACKER_NGNIX_PORT = "80";

    public static final String CLIENT_CONFIG_FILE = "fdfs_client.conf";
}

