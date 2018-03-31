package cn.jiongjionger.common;

public interface IMonitorService {

    MonitorInfoBean getMonitorInfoBean() throws Exception;

    int getPid();

    String getPidHostName();

    long getUsedMemoryMB();

    long getFreeMemoryMB();
}
