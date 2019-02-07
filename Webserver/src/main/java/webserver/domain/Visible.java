package webserver.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Visible implements GrantedAuthority {
    PUBLIC, PRIVATE;

    // TODO: 07.02.2019 Редактирование атрибута видимости для владельцев групп

    @Override
    public String getAuthority() {
        return name();
    }
}
