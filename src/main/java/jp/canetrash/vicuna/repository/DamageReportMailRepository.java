package jp.canetrash.vicuna.repository;

import java.util.List;

import jp.canetrash.vicuna.dto.DamageReportMailSearchConditionDto;
import jp.canetrash.vicuna.entity.DamageReportMailEntity;

public interface DamageReportMailRepository {

	List<DamageReportMailEntity> findByCondition(
			DamageReportMailSearchConditionDto condition);

}
