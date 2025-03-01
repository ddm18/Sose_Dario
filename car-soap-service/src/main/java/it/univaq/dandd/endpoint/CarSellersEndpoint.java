package it.univaq.dandd.endpoint;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import it.univaq.dandd.model.CarSellerTransportDTO;
import it.univaq.dandd.model.CarSellersModel;
import it.univaq.dandd.service.CarSellerServiceImpl;
import it.univaq.dandd.wsdltypes.*;

@Endpoint
public class CarSellersEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarSellersEndpoint.class);

    @Autowired
    private CarSellerServiceImpl carSellerService;

    @PayloadRoot(namespace = "http://univaq.it/dandd/wsdltypes", localPart = "getAllCarSellersRequest")
    @ResponsePayload
    public GetAllCarSellersResponse getAllCarSellers(@RequestPayload GetAllCarSellersRequest request) {
        LOGGER.info("**** 'CarSellersEndpoint' RECEIVED A REQUEST FOR 'getAllCarSellers()'");

        List<CarSellersModel> carSellers = carSellerService.getAllCarSellers();

        ObjectFactory factory = new ObjectFactory();
        GetAllCarSellersResponse response = factory.createGetAllCarSellersResponse();

        for (CarSellersModel seller : carSellers) {
            CarSeller carSeller = factory.createCarSeller();
            carSeller.setId(seller.getId());
            carSeller.setCarSeller(seller.getCarSeller());
            carSeller.setLatitude(seller.getLatitude());
            carSeller.setLongitude(seller.getLongitude());
            carSeller.setLocation(seller.getLocation());
            response.getCarSellers().add(carSeller);
        }

        LOGGER.info("**** 'CarSellersEndpoint' IS GOING TO SEND A RESPONSE WITH '{}' CAR SELLERS",
                response.getCarSellers().size());
        return response;
    }

    @PayloadRoot(namespace = "http://univaq.it/dandd/wsdltypes", localPart = "getCommonCarSellersWithDetailsRequest")
    @ResponsePayload
    public GetCommonCarSellersWithDetailsResponse getCommonCarSellersWithDetails(
            @RequestPayload GetCommonCarSellersWithDetailsRequest request) {
        LOGGER.info("**** 'CarSellersEndpoint' RECEIVED A REQUEST FOR 'getCommonCarSellersWithDetails()'");

        List<CarSellerTransportDTO> commonSellers = carSellerService.getCommonCarSellersWithDetails(
                request.getDeparture(), request.getArrival());

        ObjectFactory factory = new ObjectFactory();
        GetCommonCarSellersWithDetailsResponse response = factory.createGetCommonCarSellersWithDetailsResponse();

        for (CarSellerTransportDTO dto : commonSellers) {
            CarSellerTransportWsDTO transport = factory.createCarSellerTransportWsDTO();
            transport.setDepartureName(dto.getDepartureName());
            transport.setDepartureLatitude(dto.getDepartureLatitude());
            transport.setDepartureLongitude(dto.getDepartureLongitude());
            transport.setArrivalName(dto.getArrivalName());
            transport.setArrivalLatitude(dto.getArrivalLatitude());
            transport.setArrivalLongitude(dto.getArrivalLongitude());
            transport.setArrivalId(dto.getArrivalId());
            transport.setDepartureId(dto.getDepartureId());
            transport.setCarSeller(dto.getCarSeller());
            response.getCarSellerTransportWsDTOs().add(transport);
        }

        LOGGER.info("**** 'CarSellersEndpoint' IS GOING TO SEND A RESPONSE WITH '{}' COMMON CAR SELLERS",
                response.getCarSellerTransportWsDTOs().size());
        return response;
    }

    @PayloadRoot(namespace = "http://univaq.it/dandd/wsdltypes", localPart = "getCarSellerByIdsRequest")
    @ResponsePayload
    public GetCarSellerByIdsResponse getCarSellerByIds(@RequestPayload GetCarSellerByIdsRequest request) {
        LOGGER.info("**** 'CarSellersEndpoint' RECEIVED A REQUEST FOR 'getCarSellerByIds()'");

        Optional<CarSellerTransportDTO> transportDTO = carSellerService.getTransportDTOForIds(
                request.getDepartureId(), request.getArrivalId());

        ObjectFactory factory = new ObjectFactory();
        GetCarSellerByIdsResponse response = factory.createGetCarSellerByIdsResponse();

        if (transportDTO.isPresent()) {
            CarSellerTransportDTO dto = transportDTO.get();
            CarSellerTransportWsDTO transport = factory.createCarSellerTransportWsDTO();
            transport.setDepartureName(dto.getDepartureName());
            transport.setDepartureLatitude(dto.getDepartureLatitude());
            transport.setDepartureLongitude(dto.getDepartureLongitude());
            transport.setArrivalName(dto.getArrivalName());
            transport.setArrivalLatitude(dto.getArrivalLatitude());
            transport.setArrivalLongitude(dto.getArrivalLongitude());
            transport.setArrivalId(dto.getArrivalId());
            transport.setDepartureId(dto.getDepartureId());
            transport.setCarSeller(dto.getCarSeller());
            response.setCarSellerTransportWsDTO(transport);

            LOGGER.info("**** 'CarSellersEndpoint' FOUND TRANSPORT DTO FOR IDS");
        } else {
            LOGGER.warn("**** 'CarSellersEndpoint' DID NOT FIND TRANSPORT DTO FOR IDS");
        }

        return response;
    }
}
