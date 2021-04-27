package service;

import model.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeImpl {
    private static List<Employee> employees = new ArrayList<>();
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.conf.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    static {
        employees.add(new Employee(1, "sdasd", "asdasda", "z2263315325275_586f04dfedf5756282a4317f27e2f4ac.jpg", true));
    }

    public List<Employee> findAll() {
        String queryStr = "SELECT c FROM Employee AS c";
        TypedQuery<Employee> query = entityManager.createQuery(queryStr, Employee.class);
        return query.getResultList();
    }

    public static Employee findById(Long id) {
        String queryStr = "SELECT c FROM Employee AS c WHERE c.id =:id";
        Employee employee = entityManager.createQuery(queryStr, Employee.class).setParameter("id", id).getSingleResult();
        return employee;
    }

    public Employee update(Employee employee) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Employee employee1 = findById(employee.getId());
            employee1.setName(employee.getName());
            employee1.setDate(employee.getDate());
            employee1.setAvatar(employee.getAvatar());
            employee1.setGender(employee.isGender());
            session.saveOrUpdate(employee);
            transaction.commit();
            return employee1;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    public void save(Employee employee) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(employee);
        transaction.commit();

    }

    public void delete(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        Employee employeedelete = EmployeeImpl.findById(id);
        transaction.begin();
        entityManager.remove(employeedelete);
        transaction.commit();

    }
}
