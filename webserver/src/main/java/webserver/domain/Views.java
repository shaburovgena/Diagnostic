package webserver.domain;

/**
 * Класс для сокрытия некоторых полей от клиента
 */
public final class Views {
    //Показывает только id клиенту
    public interface Id {
    }

    //Показывает и id и title
    public interface IdName extends Id {
    }

    public interface IdNameValue extends IdName {

    }

    public interface IdNameValueAttribute extends IdNameValue {

    }

    public interface IdNameRoles extends IdName {

    }

    public interface IdNameValueGroup extends IdNameValue {


    }

    public interface IdNameValueGroupPort extends IdNameValueGroup {

    }

    //Показывает сообщение (и id и name)
    public interface FullMetric extends IdName {
    }

}
