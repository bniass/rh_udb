package service;

import model.Service;
import model.Specialite;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

public class ParametrageDAO implements IParametrage {
    Session session;
    public  ParametrageDAO(){
        session = HibernateUtil.getSession();
    }
    @Override
    public List<Specialite> findAllSpecialites() {
        return session.createQuery("SELECT sp FROM Specialite sp", Specialite.class).list();
    }

    @Override
    public List<Specialite> findSpecialitesByServiceId(long id) {
        return session.createQuery("SELECT sp FROM Specialite sp JOIN sp.service s WHERE s.id = :serviceId",
                Specialite.class).setParameter("serviceId", id).list();
    }

    @Override
    public List<Service> findAllServices() {
        return session.createQuery("SELECT s FROM Service s", Service.class).list();
    }

    @Override
    public Specialite saveSpecialite(Specialite specialite) {
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.saveOrUpdate(specialite);
            transaction.commit();
        }
        catch(Exception e){
            transaction.rollback();
            e.printStackTrace();
        }

            return specialite;
    }

    @Override
    public Specialite findSpecialiteByLibelle(String libelle) {
        try {
            return session.createQuery("SELECT s FROM Specialite s WHERE s.libelle = :lib",
                    Specialite.class)
                    .setParameter("lib", libelle)
                    .getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void deleteSpecialite(Specialite specialite) {
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.delete(specialite);
            transaction.commit();
        }
        catch(Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
    }
}
