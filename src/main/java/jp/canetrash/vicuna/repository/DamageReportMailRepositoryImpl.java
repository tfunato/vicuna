package jp.canetrash.vicuna.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jp.canetrash.vicuna.dto.DamageReportMailSearchConditionDto;
import jp.canetrash.vicuna.entity.DamageReportMailEntity;

import org.springframework.stereotype.Repository;

@Repository
public class DamageReportMailRepositoryImpl implements
		DamageReportMailRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<DamageReportMailEntity> findByCondition(
			DamageReportMailSearchConditionDto condition) {
		// FIXME search condition
		return this.entityManager.createQuery(
				"select d from DAMAGE_REPORT_MAIL",
				DamageReportMailEntity.class).getResultList();
	}
}
