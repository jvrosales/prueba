package ec.gob.ambiente.enlisy.exportauthorization.services;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.exportauthorization.model.CommercialTermCites;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class CommercialTermCitesFacade extends AbstractFacade<CommercialTermCites, Integer> {

    public CommercialTermCitesFacade() {
        super(CommercialTermCites.class, Integer.class);
    }

    @SuppressWarnings("unchecked")
    public List<CommercialTermCites> findCommercialTermCitesAnimal() {
        try {
            Query query = super.getEntityManager().createQuery(
                    "SELECT o FROM CommercialTermCites o where o.cotcStatus = true and o.cotcAnimal=true");
            List<CommercialTermCites> result = (List<CommercialTermCites>) query.getResultList();
            if (!result.isEmpty())
                return result;

        } catch (NoResultException e) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public List<CommercialTermCites> findCommercialTermCitesPlantae() {
        try {
            Query query = super.getEntityManager().createQuery(
                    "SELECT o FROM CommercialTermCites o where o.cotcStatus = true and o.cotcPlantae=true");
            List<CommercialTermCites> result = (List<CommercialTermCites>) query.getResultList();
            if (!result.isEmpty())
                return result;

        } catch (NoResultException e) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public List<CommercialTermCites> findCommercialTermCitesFungi() {
        try {
            Query query = super.getEntityManager().createQuery(
                    "SELECT o FROM CommercialTermCites o where o.cotcStatus = true and o.cotcFungi=true");
            List<CommercialTermCites> result = (List<CommercialTermCites>) query.getResultList();
            if (!result.isEmpty())
                return result;

        } catch (NoResultException e) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public List<CommercialTermCites> findCommercialTermCitesEubacteria() {
        try {
            Query query = super.getEntityManager().createQuery(
                    "SELECT o FROM CommercialTermCites o where o.cotcStatus = true and o.cotcEubacteria=true");
            List<CommercialTermCites> result = (List<CommercialTermCites>) query.getResultList();
            if (!result.isEmpty())
                return result;

        } catch (NoResultException e) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public List<CommercialTermCites> findCommercialTermCitesArqueobacteria() {
        try {
            Query query = super.getEntityManager().createQuery(
                    "SELECT o FROM CommercialTermCites o where o.cotcStatus = true and o.cotcArqueobacteria=true");
            List<CommercialTermCites> result = (List<CommercialTermCites>) query.getResultList();
            if (!result.isEmpty())
                return result;

        } catch (NoResultException e) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public List<CommercialTermCites> findCommercialTermCitesProtista() {
        try {
            Query query = super.getEntityManager().createQuery(
                    "SELECT o FROM CommercialTermCites o where o.cotcStatus = true and o.cotcProtista=true");
            List<CommercialTermCites> result = (List<CommercialTermCites>) query.getResultList();
            if (!result.isEmpty())
                return result;

        } catch (NoResultException e) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public List<CommercialTermCites> findCommercialTermCitesChromista() {
        try {
            Query query = super.getEntityManager().createQuery(
                    "SELECT o FROM CommercialTermCites o where o.cotcStatus = true and o.cotcChromista=true");
            List<CommercialTermCites> result = (List<CommercialTermCites>) query.getResultList();
            if (!result.isEmpty())
                return result;

        } catch (NoResultException e) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public List<CommercialTermCites> findCommercialTermCitesViruses() {
        try {
            Query query = super.getEntityManager().createQuery(
                    "SELECT o FROM CommercialTermCites o where o.cotcStatus = true and o.cotcViruses=true");
            List<CommercialTermCites> result = (List<CommercialTermCites>) query.getResultList();
            if (!result.isEmpty())
                return result;

        } catch (NoResultException e) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
	public CommercialTermCites findCommercialTermCitesByCode(String code){
        try {
            Query query = super.getEntityManager().createQuery(
                    "SELECT o FROM CommercialTermCites o where o.cotcStatus = true and o.cotcCode='"+code.toUpperCase()+"'");
            List<CommercialTermCites> result = (List<CommercialTermCites>) query.getResultList();
            if (!result.isEmpty())
                return result.get(0);

        } catch (NoResultException e) {
            return new CommercialTermCites();
        }
        return new CommercialTermCites();
    }
}