package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.speciescatalog.model.Habit;


/**
 * Servicios para la administracion de habitos
 * @author EXCO
 *
 */
@Stateless
public class HabitFacade extends AbstractFacade<Habit, Integer>{

	public HabitFacade() {
		super(Habit.class, Integer.class);		
	}
	
		
	/**
	 * Recuperar lista de habitos reino animal
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<Habit> findHabitsAnimal()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM Habit o where o.habiStatus = true and o.habiAnimal=true");
			List<Habit> result=(List<Habit>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<Habit>();
		}
		return new ArrayList<Habit>();
	}
	
	/**
	 * Recuperar lista de habitos reino plantae
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<Habit> findHabitsPlantae()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM Habit o where o.habiStatus = true and o.habiPlantae=true");
			List<Habit> result=(List<Habit>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<Habit>();
		}
		return new ArrayList<Habit>();
	}
	
	/**
	 * Recuperar lista de grupos reino fungi
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<Habit> findHabitsFungi()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM Habit o where o.habiStatus = true and o.habiFungi=true");
			List<Habit> result=(List<Habit>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<Habit>();
		}
		return new ArrayList<Habit>();
	}
	
	/**
	 * Recuperar lista de grupos reino eubacteria
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<Habit> findHabitsEubacteria()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM Habit o where o.habiStatus = true and o.habiEubacteria=true");
			List<Habit> result=(List<Habit>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<Habit>();
		}
		return new ArrayList<Habit>();
	}
	
	/**
	 * Recuperar lista de grupos reino arqueobacteria
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<Habit> findHabitsArqueobacteria()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM Habit o where o.habiStatus = true and o.habiArcheobacteria=true");
			List<Habit> result=(List<Habit>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<Habit>();
		}
		return new ArrayList<Habit>();
	}
	
	/**
	 * Recuperar lista de grupos reino protista
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<Habit> findHabitsProtista()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM Habit o where o.habiStatus = true and o.habiProtista=true");
			List<Habit> result=(List<Habit>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<Habit>();
		}
		return new ArrayList<Habit>();
	}
	
	/**
	 * Recuperar lista de grupos reino chromista
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<Habit> findHabitsChromista()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM Habit o where o.habiStatus = true and o.habiChromista=true");
			List<Habit> result=(List<Habit>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<Habit>();
		}
		return new ArrayList<Habit>();
	}
	
	/**
	 * Recuperar lista de grupos reino viruses
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<Habit> findHabitsViruses()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM Habit o where o.habiStatus = true and o.habiViruses=true");
			List<Habit> result=(List<Habit>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<Habit>();
		}
		return new ArrayList<Habit>();
	}
}
