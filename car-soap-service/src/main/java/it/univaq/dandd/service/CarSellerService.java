package it.univaq.dandd.service;

import java.util.List;
import java.util.Optional;

import it.univaq.dandd.model.CarSellerTransportDTO;
import it.univaq.dandd.model.CarSellersModel;

public interface CarSellerService {

	List<CarSellersModel> getAllCarSellers();

	List<CarSellerTransportDTO> getCommonCarSellersWithDetails(String departure, String arrival);

	Optional<CarSellerTransportDTO> getTransportDTOForIds(long departureId, long arrivalId);

}
