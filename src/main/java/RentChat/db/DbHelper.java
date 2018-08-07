package RentChat.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

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

    public boolean checkLogin(String login) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.<String>get("login"), login));
        Query query = session.createQuery(criteriaQuery);
        User user = null;
        try {
            user = (User)query.getSingleResult();

        } catch (NoResultException e) {
        } finally {
            session.close();
        }

        if (user == null)  {
            System.out.println("юзер отсутствует");
            return true;
        } else {
            System.out.println("есть юзер");
            return false;
        }
    }

    public void registarionUser(String login, String password) {
        User user = new User(login, password);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }
}
