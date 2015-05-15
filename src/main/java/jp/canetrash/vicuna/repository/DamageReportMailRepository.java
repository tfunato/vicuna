package jp.canetrash.vicuna.repository;

import jp.canetrash.vicuna.entity.DamageReportMailEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DamageReportMailRepository extends
		JpaRepository<DamageReportMailEntity, String> {

}
