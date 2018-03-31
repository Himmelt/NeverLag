package cn.jiongjionger.neverlag.common;

public interface IMonitorService {

    MonitorInfoBean getMonitorInfoBean() throws Exception;

    int getPid();

    String getPidHostName();

    long getUsedMemoryMB();

    long getFreeMemoryMB();
}
