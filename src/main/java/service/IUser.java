package service;

import model.Utilisateur;

public interface IUser {
    public Utilisateur findUser(String login, String pwd);
}
