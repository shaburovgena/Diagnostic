package ru.agent;

import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        return value > 70;
    }

    @Override
    public String getMetric() {
        return "77";
//        String result = "";
//        try {
//            FileReader fin = new FileReader("value");
//            result = String.valueOf(fin.read());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Pattern pattern = Pattern.compile("(\\d+)%");
//        Matcher m = pattern.matcher(result);
//        while (m.find()) {
//            return m.group(1);
//        }
//        return "-----";
    }

    @Override
    public String getNotificationText() {
        return String.format("disk usage is %d%%", value);
    }

}
