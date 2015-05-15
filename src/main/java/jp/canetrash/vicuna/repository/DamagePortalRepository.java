package jp.canetrash.vicuna.repository;

import jp.canetrash.vicuna.entity.DamagePortalEntity;
import jp.canetrash.vicuna.entity.DamagePortalEntity.DamagePortalPk;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DamagePortalRepository extends
		JpaRepository<DamagePortalEntity, DamagePortalPk> {
}
