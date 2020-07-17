package service;

import model.Service;
import model.Specialite;

import java.util.List;

public interface IParametrage {
    public List<Specialite> findAllSpecialites();
    public List<Specialite> findSpecialitesByServiceId(long id);
    public List<Service> findAllServices();
    public Specialite saveSpecialite(Specialite specialite);
    public Specialite findSpecialiteByLibelle(String libelle);
    public void deleteSpecialite(Specialite specialite);
}
