package ru.agent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DiskUsageMetric extends Metric {
    int value;

    public DiskUsageMetric(String value) {
        this.value = Integer.parseInt(value);
    }

    public DiskUsageMetric(int interval) {
        super(interval);
    }

    @Override
    public boolean alert() {
        return value > 70;
    }

    @Override
    public String getMetric() {

        //Выполнение консольной команды

        String result = executeCommand("df");

        //Получение результата в виде чисел

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
