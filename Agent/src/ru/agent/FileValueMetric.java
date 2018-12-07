package ru.agent;

public class FileValueMetric extends Metric {
    int value;

    public FileValueMetric(String value) {
        this.value = Integer.parseInt(value);
    }

    public FileValueMetric(String serverUrl, int interval) {
        super(serverUrl, interval);
    }

    @Override
    public boolean alert() {
        return true;
    }

    @Override
    public String getMetric() {
        String result = executeCommand("cmd /c echo cmd.exe && dir ").replaceAll("\n", " ");
        System.out.println(result);
        return result;
    }

    @Override
    public String getNotificationText() {
        return String.format("disk usage is %d%%", value);
    }

}
