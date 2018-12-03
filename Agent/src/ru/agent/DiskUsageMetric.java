package ru.agent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DiskUsageMetric extends Metric {
    int value;

    public DiskUsageMetric(int value) {
        this.value = value;
    }

    public DiskUsageMetric(String serverUrl, long interval) {
        super(serverUrl, interval);
    }

    @Override
    public boolean alert() {
        return value > 70;
    }

    @Override
    public String getMetric() {
        String result = executeCommand("df");
        Pattern pattern = Pattern.compile("(\\d+)%");
        Matcher m = pattern.matcher(result);
        while (m.find()) {
            return m.group(1);
        }
        return "-----";
    }

    @Override
    public String getNotificationText() {
        return String.format("disk usage is %d%%", value);
    }

}
