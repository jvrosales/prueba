package ec.gob.ambiente.enlisy.exportauthorization.services;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.exportauthorization.model.OriginCites;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class OriginCitesFacade extends AbstractFacade<OriginCites, Integer> {

    public OriginCitesFacade() {
        super(OriginCites.class, Integer.class);
    }

    @SuppressWarnings("unchecked")
    public List<OriginCites> findOriginCitesAnimal() {
        try {
            Query query = super.getEntityManager().createQuery(
                    "SELECT o FROM OriginCites o where o.orciStatus = true and o.orciAnimal=true");
            List<OriginCites> result = (List<OriginCites>) query.getResultList();
            if (!result.isEmpty())
                return result;

        } catch (NoResultException e) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public List<OriginCites> findOriginCitesPlantae() {
        try {
            Query query = super.getEntityManager().createQuery(
                    "SELECT o FROM OriginCites o where o.orciStatus = true and o.orciPlantae=true");
            List<OriginCites> result = (List<OriginCites>) query.getResultList();
            if (!result.isEmpty())
                return result;

        } catch (NoResultException e) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public List<OriginCites> findOriginCitesFungi() {
        try {
            Query query = super.getEntityManager().createQuery(
                    "SELECT o FROM OriginCites o where o.orciStatus = true and o.orciFungi=true");
            List<OriginCites> result = (List<OriginCites>) query.getResultList();
            if (!result.isEmpty())
                return result;

        } catch (NoResultException e) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public List<OriginCites> findOriginCitesEubacteria() {
        try {
            Query query = super.getEntityManager().createQuery(
                    "SELECT o FROM OriginCites o where o.orciStatus = true and o.orciEubacteria=true");
            List<OriginCites> result = (List<OriginCites>) query.getResultList();
            if (!result.isEmpty())
                return result;

        } catch (NoResultException e) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public List<OriginCites> findOriginCitesArqueobacteria() {
        try {
            Query query = super.getEntityManager().createQuery(
                    "SELECT o FROM OriginCites o where o.orciStatus = true and o.orciArqueobacteria=true");
            List<OriginCites> result = (List<OriginCites>) query.getResultList();
            if (!result.isEmpty())
                return result;

        } catch (NoResultException e) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public List<OriginCites> findOriginCitesProtista() {
        try {
            Query query = super.getEntityManager().createQuery(
                    "SELECT o FROM OriginCites o where o.orciStatus = true and o.orciProtista=true");
            List<OriginCites> result = (List<OriginCites>) query.getResultList();
            if (!result.isEmpty())
                return result;

        } catch (NoResultException e) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public List<OriginCites> findOriginCitesChromista() {
        try {
            Query query = super.getEntityManager().createQuery(
                    "SELECT o FROM OriginCites o where o.orciStatus = true and o.orciChromista=true");
            List<OriginCites> result = (List<OriginCites>) query.getResultList();
            if (!result.isEmpty())
                return result;

        } catch (NoResultException e) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public List<OriginCites> findOriginCitesViruses() {
        try {
            Query query = super.getEntityManager().createQuery(
                    "SELECT o FROM OriginCites o where o.orciStatus = true and o.orciViruses=true");
            List<OriginCites> result = (List<OriginCites>) query.getResultList();
            if (!result.isEmpty())
                return result;

        } catch (NoResultException e) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
	public OriginCites findOriginCitesByCode(String code){
        try {
            Query query = super.getEntityManager().createQuery(
                    "SELECT o FROM OriginCites o where o.orciStatus = true and o.orciCode='"+code.toUpperCase()+"'");
            List<OriginCites> result = (List<OriginCites>) query.getResultList();
            if (!result.isEmpty())
                return result.get(0);

        } catch (NoResultException e) {
            return new OriginCites();
        }
        return new OriginCites();
    }
}