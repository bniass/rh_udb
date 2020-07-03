package service;

import model.Service;
import model.Specialite;
import org.hibernate.Session;
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
    public List<Service> findAllServices() {
        return session.createQuery("SELECT s FROM Service s", Service.class).list();
    }

    @Override
    public Specialite saveSpecialite(Specialite specialite) {
        session.save(specialite);
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
}
