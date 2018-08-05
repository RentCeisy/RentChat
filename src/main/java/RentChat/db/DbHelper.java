package RentChat.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.sql.ResultSet;

public class DbHelper {

    private SessionFactory sessionFactory;

    public DbHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public User getLogin(String login, String password) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        Selection[] selections = {root.get("login"), root.get("password")};
        criteriaQuery.select(criteriaBuilder.construct(User.class,selections)).where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.<String>get("login"), login),
                        criteriaBuilder.equal(root.<String>get("password"), password)
                )
        );
        Query query = session.createQuery(criteriaQuery);
        User user = null;
        try {
            user = (User)query.getSingleResult();
        } catch (NoResultException e) {

        }
        session.close();
        return user;
    }
}
