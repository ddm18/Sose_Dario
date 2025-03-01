package it.univaq.dandd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.dandd.dao.CarsSellerRepository;
import it.univaq.dandd.model.CarSellerTransportDTO;
import it.univaq.dandd.model.CarSellersModel;

@Service
public class CarSellerServiceImpl implements CarSellerService {

    @Autowired
    private CarsSellerRepository carSellerRepo;

    // Method to retrieve all car sellers
    @Override
    public List<CarSellersModel> getAllCarSellers() {
        return carSellerRepo.findAll();
    }
    @Override
    public List<CarSellerTransportDTO> getCommonCarSellersWithDetails(String departure, String arrival) {
        // Get all car sellers for the departure location
        List<CarSellersModel> departureSellers = carSellerRepo.findByLocation(departure);

        // Prepare the result list
        List<CarSellerTransportDTO> result = new ArrayList<>();

        // Loop through each seller at the departure location
        for (CarSellersModel departureSeller : departureSellers) {
            // Find a matching seller at the arrival location with the same carSeller name
            List<CarSellersModel> arrivalSellers = carSellerRepo.findByLocationAndCarSeller(arrival, departureSeller.getCarSeller());

            for (CarSellersModel arrivalSeller : arrivalSellers) {
                // Compose the DTO and add it to the result list
                CarSellerTransportDTO dto = new CarSellerTransportDTO(
                        departureSeller.getLocation(),
                        departureSeller.getLatitude(),
                        departureSeller.getLongitude(),
                        arrivalSeller.getLocation(),
                        arrivalSeller.getLatitude(),
                        arrivalSeller.getLongitude(),
                        departureSeller.getId(),  
                        arrivalSeller.getId(),
                        departureSeller.getCarSeller()
                );
                result.add(dto);
            }
        }

        return result;
    }
    
    @Override
    public Optional<CarSellerTransportDTO> getTransportDTOForIds(long departureId, long arrivalId) {
        // Fetch car sellers by their IDs
        Optional<CarSellersModel> departureOptional = carSellerRepo.findById(departureId);
        Optional<CarSellersModel> arrivalOptional = carSellerRepo.findById(arrivalId);

        // If both exist, create and return the DTO
        if (departureOptional.isPresent() && arrivalOptional.isPresent()) {
            CarSellersModel departureSeller = departureOptional.get();
            CarSellersModel arrivalSeller = arrivalOptional.get();

            CarSellerTransportDTO dto = new CarSellerTransportDTO(
                    departureSeller.getLocation(),
                    departureSeller.getLatitude(),
                    departureSeller.getLongitude(),
                    arrivalSeller.getLocation(),
                    arrivalSeller.getLatitude(),
                    arrivalSeller.getLongitude(),
                    departureId,  
                    arrivalId,
                    departureSeller.getCarSeller()
            );
            return Optional.of(dto);
        }

        // Return empty if one or both sellers don't exist
        return Optional.empty();
    }
}