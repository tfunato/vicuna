package jp.canetrash.vicuna.repository;

import java.util.List;

import jp.canetrash.vicuna.entity.DamagePortalEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DamagePortalRepository extends
		JpaRepository<DamagePortalEntity, String> {

	@Query(value = "select * from damage_portal p where p.latitude <= ?1 and p.latitude >= ?2 and p.longitude <= ?3 and p.longitude >= ?4 group by p.latitude, p.longitude having max(p.create_date) limit 1000", nativeQuery = true)
	List<DamagePortalEntity> findByGeoParameter(String neLat, String swLat,
			String neLng, String swLng);
}
