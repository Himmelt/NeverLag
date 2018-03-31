package cn.jiongjionger.neverlag.common;


public class MonitorInfoBean {

    private String totalMemory = "";
    private String freeMemory = "";
    private String maxMemory = "";
    private String osName = "Unknown";
    private String totalMemorySize = "";
    private String freePhysicalMemorySize = "";
    private String usedMemory = "";
    private int totalThread = 0;
    private String cpuRatio = "";

    public String getUsedMemory() {
        return this.usedMemory;
    }

    public void setUsedMemory(String usedMemory) {
        this.usedMemory = usedMemory;
    }

    public String getFreePhysicalMemorySize() {
        return this.freePhysicalMemorySize;
    }

    public void setFreePhysicalMemorySize(String freePhysicalMemorySize) {
        this.freePhysicalMemorySize = freePhysicalMemorySize;
    }

    public String getTotalMemorySize() {
        return this.totalMemorySize;
    }

    public void setTotalMemorySize(String totalMemorySize) {
        this.totalMemorySize = totalMemorySize;
    }

    public String getFreeMemory() {
        return this.freeMemory;
    }

    public void setFreeMemory(String freeMemory) {
        this.freeMemory = freeMemory;
    }

    public String getMaxMemory() {
        return this.maxMemory;
    }

    public void setMaxMemory(String maxMemory) {
        this.maxMemory = maxMemory;
    }

    public String getTotalMemory() {
        return this.totalMemory;
    }

    public void setTotalMemory(String totalMemory) {
        this.totalMemory = totalMemory;
    }

    public String getCpuRatio() {
        return this.cpuRatio;
    }

    public void setCpuRatio(String cpuRatio) {
        this.cpuRatio = cpuRatio;
    }

    public String getOsName() {
        return this.osName;
    }

    public void setOsName(String osName) {
        if (osName != null && !osName.isEmpty()) {
            this.osName = osName;
        }
    }

    public int getTotalThread() {
        return this.totalThread;
    }

    public void setTotalThread(int totalThread) {
        this.totalThread = totalThread;
    }

}
