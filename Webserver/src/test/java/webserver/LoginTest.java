package webserver;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
//Добавляет заглушки для исключения взаимодействия с базой, создания некоторых сущностей
@AutoConfigureMockMvc
//Указывает файл с пропертями для теста
@TestPropertySource("/application-test.properties")
public class LoginTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void mainPageLoad() throws Exception {
        this.mockMvc.perform(get("/"))//Отправить get на "/"
                .andDo(print())//Напечатать результат
                .andExpect(content().string(containsString("Приветствую, Гость!")));//В теле ожидается строка
    }

    @Test
    public void redirectPageTest() throws Exception {
        this.mockMvc.perform(get("/main"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())//Ожидаем код 3хх (редирект)
                .andExpect(redirectedUrl("http://localhost/login"));//Ожидаемая страница
    }

    @Test
    //Перед тестом пользователь должен быть добавлен в базу
    @Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void trueLoginTest() throws Exception {
        this.mockMvc.perform(formLogin().user("user1").password("1"))//Передаем в форму УЗ
                .andExpect(status().is3xxRedirection())//Ожидаем редирект
                .andExpect(redirectedUrl("/"));//На основную страницу
    }

    @Test
    public void badLoginTest() throws Exception {
        this.mockMvc.perform(post("/login").param("username", "user2"))//Передаем в форму логина имя пользователя user3
                .andDo(print())
                .andExpect(status().isForbidden());//Ожидаем "доступ заперещен"
    }
}
