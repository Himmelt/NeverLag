package cn.jiongjionger.neverlag.common;

import com.sun.management.OperatingSystemMXBean;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.StringTokenizer;

public class MonitorServiceImpl implements IMonitorService {

    private static final int CPUTIME = 30;
    private static final int PERCENT = 100;
    private static final int FAULTLENGTH = 10;

    public MonitorInfoBean getMonitorInfoBean() {
        short kb = 1024;
        long totalMemory = Runtime.getRuntime().totalMemory() / (long) kb;
        long freeMemory = Runtime.getRuntime().freeMemory() / (long) kb;
        long maxMemory = Runtime.getRuntime().maxMemory() / (long) kb;
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        String osName = System.getProperty("os.name");
        long totalMemorySize = osmxb.getTotalPhysicalMemorySize() / (long) kb;
        long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize() / (long) kb;
        long usedMemory = (osmxb.getTotalPhysicalMemorySize() - osmxb.getFreePhysicalMemorySize()) / (long) kb;

        ThreadGroup parentThread;
        for (parentThread = Thread.currentThread().getThreadGroup(); parentThread.getParent() != null; parentThread = parentThread.getParent()) {
        }

        int totalThread = parentThread.activeCount();
        double cpuRatio = 0.0D;
        if (osName.toLowerCase().startsWith("windows")) {
            cpuRatio = this.getCpuRatioForWindows();
        } else if (osName.toLowerCase().startsWith("mac")) {
            cpuRatio = 0.0D;
        } else {
            cpuRatio = getCpuRateForLinux();
        }

        MonitorInfoBean infoBean = new MonitorInfoBean();
        infoBean.setFreeMemory(freeMemory / 1024L + "MB");
        infoBean.setFreePhysicalMemorySize(freePhysicalMemorySize / 1024L + "MB");
        infoBean.setMaxMemory(maxMemory / 1024L + "MB");
        infoBean.setOsName(osName);
        infoBean.setTotalMemory(totalMemory / 1024L + "MB");
        infoBean.setTotalMemorySize(totalMemorySize / 1024L + "MB");
        infoBean.setTotalThread(totalThread);
        infoBean.setUsedMemory(usedMemory / 1024L + "MB");
        infoBean.setCpuRatio(cpuRatio + "%");
        return infoBean;
    }

    public int getPid() {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        String name = runtime.getName();

        try {
            return Integer.parseInt(name.substring(0, name.indexOf(64)));
        } catch (Exception e) {
            return -1;
        }
    }

    public String getPidHostName() {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        return runtime.getName();
    }

    public long getUsedMemoryMB() {
        return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576L;
    }

    public long getFreeMemoryMB() {
        return Runtime.getRuntime().freeMemory() / 1048576L;
    }

    private static double getCpuRateForLinux() {
        Object is = null;
        Object isr = null;
        BufferedReader br = new BufferedReader((Reader) isr);
        StringTokenizer tokenStat = null;

        try {
            br.readLine();
            br.readLine();
            String ioe = br.readLine();
            tokenStat = new StringTokenizer(ioe, ",");

            String nextToken;
            for (nextToken = tokenStat.nextToken(); !nextToken.contains("id") && tokenStat.hasMoreTokens(); nextToken = tokenStat.nextToken()) {
            }

            int index = nextToken.indexOf("%");
            if (index != -1) {
                nextToken = nextToken.substring(0, index);
            } else {
                index = nextToken.indexOf("id");
                if (index != -1) {
                    nextToken = nextToken.substring(0, index);
                }
            }

            Float usage = Float.parseFloat(nextToken.trim());
            double var9 = (double) (100.0F - usage);
            return var9;
        } catch (IOException var13) {
            System.out.println(var13.getMessage());
            freeResource((InputStream) is, (InputStreamReader) isr, br);
        } finally {
            freeResource((InputStream) is, (InputStreamReader) isr, br);
        }

        return 1.0D;
    }

    private static void freeResource(InputStream is, InputStreamReader isr, BufferedReader br) {
        try {
            if (is != null) {
                is.close();
            }

            if (isr != null) {
                isr.close();
            }

            if (br != null) {
                br.close();
            }
        } catch (IOException var4) {
            System.out.println(var4.getMessage());
        }

    }

    private double getCpuRatioForWindows() {
        try {
            String ex = System.getenv("windir") + "\\system32\\wbem\\wmic.exe process get Caption,CommandLine,KernelModeTime,ReadOperationCount,ThreadCount,UserModeTime,WriteOperationCount";
            long[] c0 = this.readCpu(Runtime.getRuntime().exec(ex));
            Thread.sleep(30L);
            long[] c1 = this.readCpu(Runtime.getRuntime().exec(ex));
            if (c0 != null && c1 != null) {
                long idletime = c1[0] - c0[0];
                long busytime = c1[1] - c0[1];
                return (double) (100L * busytime / (busytime + idletime));
            } else {
                return 0.0D;
            }
        } catch (Exception var8) {
            var8.printStackTrace();
            return 0.0D;
        }
    }

    private long[] readCpu(Process proc) {
        long[] retn = new long[2];

        try {
            proc.getOutputStream().close();
            InputStreamReader ex = new InputStreamReader(proc.getInputStream());
            LineNumberReader input = new LineNumberReader(ex);
            String line = input.readLine();
            if (line == null || line.length() < 10) {
                return null;
            }

            int capidx = line.indexOf("Caption");
            int cmdidx = line.indexOf("CommandLine");
            int rocidx = line.indexOf("ReadOperationCount");
            int umtidx = line.indexOf("UserModeTime");
            int kmtidx = line.indexOf("KernelModeTime");
            int wocidx = line.indexOf("WriteOperationCount");
            long idletime = 0L;
            long kneltime = 0L;
            long usertime = 0L;

            while ((line = input.readLine()) != null) {
                if (line.length() >= wocidx) {
                    String caption = line.substring(capidx, cmdidx - 1).trim();
                    String cmd = line.substring(cmdidx, kmtidx - 1).trim();
                    if (!cmd.contains("wmic.exe")) {
                        String s1 = line.substring(kmtidx, rocidx - 1).trim();
                        String s2 = line.substring(umtidx, wocidx - 1).trim();
                        if (!caption.equals("System Idle Process") && !caption.equals("System")) {
                            if (s1.length() > 0) {
                                kneltime += Long.valueOf(s1);
                            }

                            if (s2.length() > 0) {
                                usertime += Long.valueOf(s2);
                            }
                        } else {
                            if (s1.length() > 0) {
                                idletime += Long.valueOf(s1);
                            }

                            if (s2.length() > 0) {
                                idletime += Long.valueOf(s2);
                            }
                        }
                    }
                }
            }

            retn[0] = idletime;
            retn[1] = kneltime + usertime;
            long[] var23 = retn;
            return var23;
        } catch (Exception var32) {
            var32.printStackTrace();
        } finally {
            try {
                proc.getInputStream().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
