package webserver.domain;

/**
 * Класс для сокрытия некоторых полей от клиента
 */
public final class Views {
    //Показывает только id клиенту
    public interface Id {
    }

    //Показывает и id и name
    public interface IdTitle extends Id {
    }

    public interface IdTitleValue extends IdTitle {

    }

    public interface IdTitleValueGroup extends IdTitleValue {

    }

    //Показывает сообщение (и id и name)
    public interface FullMetric extends IdTitle {
    }

    //Показывает сообщение (и id и name)
    public interface FullSensor extends IdTitle {
    }


}
