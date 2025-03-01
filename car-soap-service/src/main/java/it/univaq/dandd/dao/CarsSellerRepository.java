package it.univaq.dandd.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import it.univaq.dandd.model.CarSellersModel;

public interface CarsSellerRepository extends JpaRepository<CarSellersModel, Long> {

	List<CarSellersModel> findByLocation(String location);
	List<CarSellersModel> findByLocationAndCarSeller(String location,String carSeller);
}
