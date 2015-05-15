package jp.canetrash.vicuna.repository;

import java.util.List;

import jp.canetrash.vicuna.dto.DamagePortalSearchCondition;
import jp.canetrash.vicuna.entity.DamagePortalEntity;

public interface DamagePortalRepository {

	List<DamagePortalEntity> findByCondition(DamagePortalSearchCondition condition);
}
