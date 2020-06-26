package service;

import model.Utilisateur;
import org.hibernate.Session;
import utils.HibernateUtil;

public class UserDAO implements IUser {
    Session session;
    public  UserDAO(){
        session = HibernateUtil.getSession();
    }
    @Override
    public Utilisateur findUser(String login, String pwd) {
        String hql = "SELECT u FROM Utilisateur u WHERE u.login = :log AND u.pwd = :pwd";
        try {
            return session.createQuery(hql, Utilisateur.class)
                    .setParameter("log",login)
                    .setParameter("pwd", pwd)
                    .getSingleResult();
        }
        catch (Exception e){
            return null;
        }

    }
}
