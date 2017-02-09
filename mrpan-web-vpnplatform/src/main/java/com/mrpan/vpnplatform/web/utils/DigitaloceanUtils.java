package com.mrpan.vpnplatform.web.utils;

import com.myjeeva.digitalocean.DigitalOcean;
import com.myjeeva.digitalocean.exception.DigitalOceanException;
import com.myjeeva.digitalocean.exception.RequestUnsuccessfulException;
import com.myjeeva.digitalocean.impl.DigitalOceanClient;
import com.myjeeva.digitalocean.pojo.*;

/**
 * Created by mrpan on 2017/2/9.
 */
public class DigitaloceanUtils {
    private static String authTokenRW = "fbca8932b6d87f36a8fc968a6e46434ee9cca0e4aa6692958bcb8aa49b835930";
    private Integer dropletIdForInfo = 10001; // to be placed before use
    private String volumeIdForInfo = "10001"; // to be placed before use
    private String volumeNameForInfo = "test-volume"; // to be placed before use, should have
    // different ID from volumeIdForInfo and created
    // in nyc1 region
    private Integer imageId = 3445812; // Debian 7.0 x64 image id
    private String imageSlug = "ubuntu-12-04-x64";
    private String domainName = "";
    private String domainIp = "127.0.0.1";

    private static DigitalOcean apiClient = new DigitalOceanClient(authTokenRW);

    public Droplets getAvailableDroplets()
            throws DigitalOceanException, RequestUnsuccessfulException {

        Droplets droplets = apiClient.getAvailableDroplets(1, null);

       return droplets;
    }

    public Kernels getDropletKernels() throws DigitalOceanException, RequestUnsuccessfulException {

        Kernels kernels = apiClient.getDropletKernels(dropletIdForInfo, 1, 20);
      return  kernels;
    }

    public Snapshots getDropletSnapshots() throws DigitalOceanException,
            RequestUnsuccessfulException {

        Snapshots snapshots = apiClient.getDropletSnapshots(dropletIdForInfo, 1, 20);

       return snapshots;
    }

    public Backups getDropletBackups() throws DigitalOceanException, RequestUnsuccessfulException {

        Backups backups = apiClient.getDropletBackups(dropletIdForInfo, 1, 10);

       return backups;
    }

    public Droplet getDropletInfo() throws DigitalOceanException, RequestUnsuccessfulException {

        Droplet droplet = apiClient.getDropletInfo(dropletIdForInfo);

       return droplet;
    }


    public Account getAccountInfo() throws DigitalOceanException, RequestUnsuccessfulException {
        Account account = apiClient.getAccountInfo();

       return account;
    }

    public Snapshots getAvailableSnapshots() throws DigitalOceanException,
            RequestUnsuccessfulException {

        Snapshots snapshots = apiClient.getAvailableSnapshots(1, 10);

       return snapshots;
    }


    public Snapshots getAllDropletSnapshots() throws DigitalOceanException,
            RequestUnsuccessfulException {

        Snapshots snapshots = apiClient.getAllDropletSnapshots(1, 10);

       return snapshots;
    }

}
