package jp.canetrash.vicuna.repository;

import jp.canetrash.vicuna.entity.DamagePortalEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DamagePortalRepository extends
		JpaRepository<DamagePortalEntity, String> {
}
