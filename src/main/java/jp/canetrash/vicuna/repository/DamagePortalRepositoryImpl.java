package jp.canetrash.vicuna.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jp.canetrash.vicuna.dto.DamagePortalSearchCondition;
import jp.canetrash.vicuna.entity.DamagePortalEntity;

import org.springframework.stereotype.Repository;

@Repository
public class DamagePortalRepositoryImpl implements DamagePortalRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<DamagePortalEntity> findByCondition(
			DamagePortalSearchCondition condition) {
		// FIXME search condition
		return this.entityManager.createQuery("select p from damage_portal",
				DamagePortalEntity.class).getResultList();
	}

}
