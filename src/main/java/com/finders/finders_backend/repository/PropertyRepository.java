package com.finders.finders_backend.repository;

import com.finders.finders_backend.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    // Every :param IS NULL check means "skip this filter if not provided" -
    // so someone can search with just a price range, or just bedrooms, or
    // all of them together, using the same one query.
    @Query("SELECT p FROM Property p WHERE " +
            "(:location IS NULL OR p.address LIKE %:location%) AND " +
            "(:minPrice IS NULL OR p.annualRent >= :minPrice) AND " +
            "(:maxPrice IS NULL OR p.annualRent <= :maxPrice) AND " +
            "(:propertyType IS NULL OR p.propertyType = :propertyType) AND " +
            "(:bedrooms IS NULL OR p.bedrooms = :bedrooms) AND " +
            "(:bathrooms IS NULL OR p.bathrooms = :bathrooms) AND " +
            "(:riskLevel IS NULL OR p.riskLevel = :riskLevel)")
    List<Property> searchProperties(
            @Param("location") String location,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("propertyType") Property.PropertyType propertyType,
            @Param("bedrooms") Integer bedrooms,
            @Param("bathrooms") Integer bathrooms,
            @Param("riskLevel") Property.RiskLevel riskLevel
    );
}