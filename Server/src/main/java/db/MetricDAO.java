package db;

import org.hibernate.HibernateException;
import org.hibernate.Session;

public class MetricDAO {
    private Session session;

    public MetricDAO(Session session) {
        this.session = session;
    }

    public MetricsDataSet get(long id) throws HibernateException {
        return (MetricsDataSet) session.get(MetricsDataSet.class, id);
    }


    public long insertMetric(long time, String title, String value)throws HibernateException {
        return (Long) session.save(new MetricsDataSet(time, title, value));
    }
}
