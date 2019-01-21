package db;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Created by Gena on 17.07.2018.
 */
public class UsersDAO {

    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UsersDataSet get(long id) throws HibernateException {
        return (UsersDataSet) session.get(UsersDataSet.class, id);
    }

    public long getUserId(String login) throws HibernateException {
        Criteria criteria = session.createCriteria(UsersDataSet.class);
        return ((UsersDataSet) criteria.add(Restrictions.eq("login", login)).uniqueResult()).getId();
    }

    public long insertUser(String login, String password,String name, String phone, String mail) throws HibernateException {
        return (Long) session.save(new UsersDataSet(login, password, name, phone, mail));
    }
    public void deleteUser(UsersDataSet usersDataSet) throws HibernateException {
        session.delete(usersDataSet);
    }
    public void updateUser(UsersDataSet usersDataSet) throws HibernateException {
        session.update(usersDataSet);
    }
}
