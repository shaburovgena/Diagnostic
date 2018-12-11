package ru.agent;

/**
Для тестирования работы сервера
 */

public class TestMetric extends Metric {
    private int value;

    public TestMetric(String value) {
        this.value = Integer.parseInt(value);
    }

     TestMetric(String serverUrl, int interval) {
        super(serverUrl, interval);
    }

    @Override
    public boolean alert() {
        return value > 700;
    }

    @Override
    public String getMetric() {

        return "333";
    }

    @Override
    public String getNotificationText() {
        return String.format("Test value %d%%", value);
    }

}
