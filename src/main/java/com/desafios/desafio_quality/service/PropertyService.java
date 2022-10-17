package com.desafios.desafio_quality.service;

import com.desafios.desafio_quality.controller.dto.RoomAreaResponse;
import com.desafios.desafio_quality.entity.District;
import com.desafios.desafio_quality.entity.Property;
import com.desafios.desafio_quality.entity.Room;
import com.desafios.desafio_quality.exception.NoRoomFoundInPropertyException;
import com.desafios.desafio_quality.exception.PropertyNotFoundException;
import com.desafios.desafio_quality.repository.DistrictRepository;
import com.desafios.desafio_quality.repository.PropertyRepository;
import com.desafios.desafio_quality.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * This class contains all Property related functions
 * It is a Spring @Service
 */
@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final RoomRepository roomRepository;
    private final DistrictRepository districtRepository;

    public PropertyService(PropertyRepository propertyRepository, RoomRepository roomRepository, DistrictRepository districtRepository) {
        this.propertyRepository = propertyRepository;
        this.roomRepository = roomRepository;
        this.districtRepository = districtRepository;
    }

    /**
     * Saves a new Property in dataset and returns the new instance.
     * This will also save a new District and Room related to the Property
     * @param property the new object to create
     * @return the new Property created
     */
    public Property save(Property property) {
        District savingDistrict = new District();
        savingDistrict.setPropDistrict(property.getDistrict().getPropDistrict());
        savingDistrict.setValueDistrictM2(property.getDistrict().getValueDistrictM2());
        District savedDistrict = districtRepository.save(savingDistrict);


        Property savingProperty = new Property();
        savingProperty.setDistrict(savedDistrict);
        savingProperty.setPropName(property.getPropName());
        Property savedProperty = propertyRepository.save(savingProperty);

        List<Room> savingRoomList = property.getRoomList();
        savingRoomList.forEach(pr -> pr.setProperty(savedProperty));
        List<Room> savedRoomList = roomRepository.saveAll(savingRoomList);

        property.setId(savedProperty.getId());
        property.setPropName(savedProperty.getPropName());
        property.setDistrict(savedDistrict);
        property.setRoomList(savedRoomList);

        return property;
    }

    /**
     * Returns the Property by its ID
     * @param id the property ID
     * @return the Property
     * @throws PropertyNotFoundException in case the Property does not exist
     */
    public Property findById(Long id) {
        Property property = propertyRepository.findById(id).orElseThrow(() -> new PropertyNotFoundException(id));
        List<Room> roomList = roomRepository.findByProperty(property);
//        roomList.forEach(r -> r.setProperty(null));
        property.setRoomList(roomList);
        return property;
    }

    /**
     * Returns the Property price given its id.
     * Its calculate by using the Room area multiplied by the district value
     * @param id the property ID
     * @return the property price
     */
    public BigDecimal pricePropertyById(Long id) {
        Property propertyById = this.findById(id);
        Double sumRooms = propertyById.getRoomList().stream().map(Room::getArea).reduce(0.0, Double::sum);
        BigDecimal result = new BigDecimal(sumRooms).multiply(propertyById.getDistrict().getValueDistrictM2());
        return result;
    }

    /**
     * Returns the square area for a Property given its id.
     * It calculates by using its Rooms area
     * @param id the property iD
     * @return the square area of a property
     */
    public Double getTotalM2PropertyById(Long id) {
        Property property = findById(id);

        Double sum = property.getRoomList().stream()
                .reduce(0.0, (partialArea, areaTotal) -> partialArea + areaTotal.getArea(), Double::sum);

        return (double) Math.round(sum * 100) / 100;
    }

}
