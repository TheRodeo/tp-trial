package rodeo.base.dao.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import rodeo.base.dao.pojos.Discipline;

@Repository
@Transactional(readOnly = true)
public class BaseInformationRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public Discipline findDisciplineByName(final String discipline) {
		try {
			return entityManager.createNamedQuery(Discipline.FIND_DISCIPLINE_BY_NAME, Discipline.class).setParameter("name", discipline)
					.getSingleResult();
		} catch (final PersistenceException e) {
			return null;
		}
	}

	public List<String> getAllDisciplineNames() {
		try {
			return entityManager.createNamedQuery(Discipline.GET_ALL_DISCIPLINE_NAMES, String.class).getResultList();
		} catch (final PersistenceException e) {
			return null;
		}
	}

	@Transactional
	public Object save(final Object obj) {
		entityManager.persist(obj);
		return obj;
	}

	@Transactional
	public List<Object> saveMany(final List<Object> objects) {
		for (final Object object : objects) {
			entityManager.persist(object);
		}
		return objects;
	}

}
