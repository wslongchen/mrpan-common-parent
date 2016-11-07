package com.mrpan.common.dms.fastdfs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by mrpan on 2016/11/6.
 */
public class TrackerServer
{
    protected Socket sock;
    protected InetSocketAddress inetSockAddr;

    /**
     * Constructor
     * @param sock Socket of server
     * @param inetSockAddr the server info
     */
    public TrackerServer(Socket sock, InetSocketAddress inetSockAddr)
    {
        this.sock = sock;
        this.inetSockAddr = inetSockAddr;
    }

    /**
     * get the connected socket
     * @return the socket
     */
    public Socket getSocket() throws IOException
    {
        if (this.sock == null)
        {
            this.sock = ClientGlobal.getSocket(this.inetSockAddr);
        }

        return this.sock;
    }

    /**
     * get the server info
     * @return the server info
     */
    public InetSocketAddress getInetSocketAddress()
    {
        return this.inetSockAddr;
    }

    public OutputStream getOutputStream() throws IOException
    {
        return this.sock.getOutputStream();
    }

    public InputStream getInputStream() throws IOException
    {
        return this.sock.getInputStream();
    }

    public void close() throws IOException
    {
        if (this.sock != null)
        {
            try
            {
                ProtoCommon.closeSocket(this.sock);
            }
            finally
            {
                this.sock = null;
            }
        }
    }

    protected void finalize() throws Throwable
    {
        this.close();
    }
}

