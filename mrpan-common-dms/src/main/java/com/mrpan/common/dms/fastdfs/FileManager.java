package com.mrpan.common.dms.fastdfs;

import com.mrpan.common.dms.utils.NameValuePair;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by mrpan on 2016/11/6.
 */
public class FileManager implements FileManagerConfig {

    private static final long serialVersionUID = 1L;

    private static Logger logger = LoggerFactory.getLogger(FileManager.class);

    private static TrackerClient trackerClient;
    private static TrackerServer trackerServer;
    private static StorageServer storageServer;
    private static StorageClient storageClient;

    static { // Initialize Fast DFS Client configurations
        try {
            String classPath = new File(FileManager.class.getResource("/")
                    .getFile()).getCanonicalPath();
            String fdfsClientConfigFilePath = classPath + File.separator
                    + CLIENT_CONFIG_FILE;
            logger.info("Fast DFS configuration file path:"
                    + fdfsClientConfigFilePath);
            ClientGlobal.init(fdfsClientConfigFilePath);

            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();

            storageClient = new StorageClient(trackerServer, storageServer);

        } catch (Exception e) {
            logger.error("初始化FastDFS错误" + e.getMessage(), e);
        }
    }

    /**
     * 上传文件 返回文件的文件服务器访问路径
     * @param file
     * @return
     */
    public static String upload(FastDFSFile file) {
        logger.info("File Name: " + file.getName() + "     File Length: "
                + file.getContent().length);
        NameValuePair[] meta_list = new NameValuePair[3];
        meta_list[0] = new NameValuePair("width", "900");
        meta_list[1] = new NameValuePair("heigth", "900");
        meta_list[2] = new NameValuePair("author", "shby");

        long startTime = System.currentTimeMillis();
        String[] uploadResults = null;
        try {
            uploadResults = storageClient.upload_file(file.getContent(),
                    file.getExt(), meta_list);
        } catch (IOException e) {
            logger.error(
                    "IO Exception when uploadind the file: " + file.getName(),
                    e);
        } catch (Exception e) {
            logger.error(
                    "Non IO Exception when uploadind the file: "
                            + file.getName(), e);
        }
        logger.info("upload_file time used: "
                + (System.currentTimeMillis() - startTime) + " ms");

        if (uploadResults == null) {
            logger.error("upload file fail, error code: "
                    + storageClient.getErrorCode());
        }

        String groupName = uploadResults[0];
        String remoteFileName = uploadResults[1];

        String fileAbsolutePath = PROTOCOL
                + trackerServer.getInetSocketAddress().getHostName()
                // fix by aracwong 应该为: 非 /
                + ":" + TRACKER_NGNIX_PORT + SEPARATOR + groupName + SEPARATOR
                + remoteFileName;

        logger.info("upload file successfully!!!  " + "group_name: "
                + groupName + ", remoteFileName:" + " " + remoteFileName);
        return fileAbsolutePath;
    }

    /**
     * 上传文件 返回FileId
     * @param file
     * @return
     */
    public static String uploadWithRtFileId(FastDFSFile file) {
        logger.info("File Name: " + file.getName() + "     File Length: "
                + file.getContent().length);
        NameValuePair[] meta_list = new NameValuePair[3];
        meta_list[0] = new NameValuePair("width", "900");
        meta_list[1] = new NameValuePair("heigth", "900");
        meta_list[2] = new NameValuePair("author", "shby");

        long startTime = System.currentTimeMillis();
        String[] uploadResults = null;
        try {
            uploadResults = storageClient.upload_file(file.getContent(),
                    file.getExt(), meta_list);
        } catch (IOException e) {
            logger.error(
                    "IO Exception when uploadind the file: " + file.getName(),
                    e);
        } catch (Exception e) {
            logger.error(
                    "Non IO Exception when uploadind the file: "
                            + file.getName(), e);
        }
        logger.info("upload_file time used: "
                + (System.currentTimeMillis() - startTime) + " ms");

        if (uploadResults == null) {
            logger.error("upload file fail, error code: "
                    + storageClient.getErrorCode());
        }

        String groupName = uploadResults[0];
        String remoteFileName = uploadResults[1];

        String fileAbsolutePath = PROTOCOL
                + trackerServer.getInetSocketAddress().getHostName()
                // fix by aracwong 应该为: 非 /
                + ":" + TRACKER_NGNIX_PORT + SEPARATOR + groupName + SEPARATOR
                + remoteFileName;

        logger.info("upload file successfully!!!  " + "group_name: "
                + groupName + ", remoteFileName:" + " " + remoteFileName);
        return groupName + SEPARATOR + remoteFileName;
    }

    /**
     * 上传带有Group Name的文件
     * @param groupName
     * @param file
     * @return
     */
    public static String[] upload(String groupName, FastDFSFile file) {
        logger.info("File Name: " + file.getName() + "     File Length: "
                + file.getContent().length);
        NameValuePair[] meta_list = new NameValuePair[3];
        meta_list[0] = new NameValuePair("width", "900");
        meta_list[1] = new NameValuePair("heigth", "900");
        meta_list[2] = new NameValuePair("author", "shby");

        long startTime = System.currentTimeMillis();
        String[] uploadResults = null;
        try {
            uploadResults = storageClient.upload_file(groupName, file.getContent(),
                    file.getExt(), meta_list);
        } catch (IOException e) {
            logger.error("IO Exception when uploadind the file: " + file.getName(),e);
        } catch (Exception e) {
            logger.error("Non IO Exception when uploadind the file: " + file.getName(), e);
        }
        logger.info("upload_file time used: "
                + (System.currentTimeMillis() - startTime) + " ms");

        if (uploadResults == null) {
            logger.error("upload file fail, error code: "
                    + storageClient.getErrorCode());
        }

        String _groupName = uploadResults[0];
        String remoteFileName = uploadResults[1];

        String fileAbsolutePath = PROTOCOL
                + trackerServer.getInetSocketAddress().getHostName()
                + ":" + TRACKER_NGNIX_PORT + SEPARATOR + groupName + SEPARATOR
                + remoteFileName;

        logger.info("upload file successfully!!!  " + "group_name: "
                + _groupName + ", remoteFileName:" + " " + remoteFileName);
        logger.info("file absolute path : " + fileAbsolutePath);
        return uploadResults;
    }

    public static FileInfo getFile(String groupName, String remoteFileName) {
        try {
            logger.info("###########" + groupName + "---" + remoteFileName);
            return storageClient.get_file_info(groupName, remoteFileName);
        } catch (IOException e) {
            logger.error("IO Exception: Get File from Fast DFS failed", e);
        } catch (Exception e) {
            logger.error("Non IO Exception: Get File from Fast DFS failed", e);
        }
        return null;
    }

    public static FileInfo getFile(String groupRemoteFileName) {
        try {
            String groupName = "";
            String remoteFileName = "";
            if (groupRemoteFileName.startsWith("/")) {
                String tmp = groupRemoteFileName.substring(1);
                groupName = groupRemoteFileName.substring(1, tmp.indexOf("/") + 1);
                remoteFileName = groupRemoteFileName.substring(groupName.length() + 2);// 去掉前面的 /
            } else {
                groupName = groupRemoteFileName.substring(0, groupRemoteFileName.indexOf("/"));
                remoteFileName = groupRemoteFileName.substring(groupName.length() + 1);
            }
            logger.info("###########" + groupName + "---" + remoteFileName);
            return storageClient.get_file_info(groupName, remoteFileName);
        } catch (IOException e) {
            logger.error("IO Exception: Get File from Fast DFS failed", e);
        } catch (Exception e) {
            logger.error("Non IO Exception: Get File from Fast DFS failed", e);
        }
        return null;
    }

    public static void deleteFile(String groupName, String remoteFileName)
            throws Exception {
        storageClient.delete_file(groupName, remoteFileName);
    }

    public static void deleteFile(String groupAndRemoteFileName) throws Exception {
        if (StringUtils.isNotBlank(groupAndRemoteFileName)) {
            String groupName = "";
            String remoteFileName = "";
            if (groupAndRemoteFileName.startsWith("/")) {
                String tmp = groupAndRemoteFileName.substring(1);
                groupName = groupAndRemoteFileName.substring(1, tmp.indexOf("/") + 1);
                remoteFileName = groupAndRemoteFileName.substring(groupName.length() + 1);
            } else {
                groupName = groupAndRemoteFileName.substring(0, groupAndRemoteFileName.indexOf("/"));
                remoteFileName = groupAndRemoteFileName.substring(groupName.length());
            }
            logger.info("###########" + groupName + "---" + remoteFileName);
            deleteFile(groupName, remoteFileName);
        }
    }

    public static StorageServer[] getStoreStorages(String groupName)
            throws IOException {
        return trackerClient.getStoreStorages(trackerServer, groupName);
    }

    public static ServerInfo[] getFetchStorages(String groupName,
                                                String remoteFileName) throws IOException {
        return trackerClient.getFetchStorages(trackerServer, groupName,
                remoteFileName);
    }
}

