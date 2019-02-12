package com.hzy.campus.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hzy.campus.entity.UserCourse;

/**
 * A data access object (DAO) providing persistence and search support for
 * UserCourse entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.hzy.campus.dao.UserCourse
 * @author MyEclipse Persistence Tools
 */

public class UserCourseDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(UserCourseDAO.class);
	// property constants
	public static final String USERID = "userid";
	public static final String COURSE = "course";
	public static final String CREDIT = "credit";
	public static final String STATE = "state";

	protected void initDao() {
		// do nothing
	}

	public void save(UserCourse transientInstance) {
		log.debug("saving UserCourse instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(UserCourse persistentInstance) {
		log.debug("deleting UserCourse instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UserCourse findById(java.lang.Integer id) {
		log.debug("getting UserCourse instance with id: " + id);
		try {
			UserCourse instance = (UserCourse) getHibernateTemplate().get(
					"com.hzy.campus.entity.UserCourse", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(UserCourse instance) {
		log.debug("finding UserCourse instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding UserCourse instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from UserCourse as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUserid(Object userid) {
		return findByProperty(USERID, userid);
	}

	public List findByCourse(Object course) {
		return findByProperty(COURSE, course);
	}

	public List findByCredit(Object credit) {
		return findByProperty(CREDIT, credit);
	}

	public List findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List findAll() {
		log.debug("finding all UserCourse instances");
		try {
			String queryString = "from UserCourse";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public UserCourse merge(UserCourse detachedInstance) {
		log.debug("merging UserCourse instance");
		try {
			UserCourse result = (UserCourse) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UserCourse instance) {
		log.debug("attaching dirty UserCourse instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserCourse instance) {
		log.debug("attaching clean UserCourse instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UserCourseDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UserCourseDAO) ctx.getBean("UserCourseDAO");
	}
}