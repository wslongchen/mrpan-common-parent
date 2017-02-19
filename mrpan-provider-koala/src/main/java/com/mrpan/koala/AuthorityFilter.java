package com.mrpan.koala;

import com.alibaba.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by mrpan on 2016/11/3.
 */
public class AuthorityFilter implements Filter {
    private static final Logger logger = LoggerFactory
            .getLogger(AuthorityFilter.class);
    private IpWhiteList ipWhiteList;

    public void setIpWhiteList(IpWhiteList ipWhiteList) {
        this.ipWhiteList = ipWhiteList;
    }

    public Result invoke(Invoker<?> invoker, Invocation invocation)
            throws RpcException {
        // TODO Auto-generated method stub
        if (this.ipWhiteList == null
                || this.ipWhiteList.getAllowedIps().size() == 0) {
            logger.info("请设定IP地址的白名单");
            return new RpcResult();
        }
        String clientIp = RpcContext.getContext().getRemoteHost();
        logger.info("访问ip为{}", clientIp);
        List<String> allowedIps = ipWhiteList.getAllowedIps();
        if (allowedIps.contains(clientIp)) {
            logger.info("地址处于白名单内:{}", clientIp);
            return invoker.invoke(invocation);
        } else {
            logger.info("地址不再白名单:{}", clientIp);
            return new RpcResult();
        }
    }
}
