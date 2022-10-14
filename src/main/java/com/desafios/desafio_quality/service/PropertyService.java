package com.desafios.desafio_quality.service;

import com.desafios.desafio_quality.controller.dto.PropertyTotalM2;
import com.desafios.desafio_quality.entity.District;
import com.desafios.desafio_quality.entity.Property;
import com.desafios.desafio_quality.entity.Room;
import com.desafios.desafio_quality.repository.DistrictRepository;
import com.desafios.desafio_quality.repository.PropertyRepository;
import com.desafios.desafio_quality.repository.RoomRepository;
import org.springframework.stereotype.Service;
import java.util.List;

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

    public Property findById(Long id) {
        Property property = propertyRepository.findById(id).orElseThrow();
        List<Room> roomList = roomRepository.findByProperty(property);
        roomList.forEach(r -> r.setProperty(null));
        property.setRoomList(roomList);
        return property;
    }

    public PropertyTotalM2 getTotalM2PropertyById(Long id){
        Property property = findById(id);

        Double sum = property.getRoomList().stream()
                .reduce(0.0, (partialArea, areaTotal) -> partialArea + areaTotal.getArea(),  Double::sum);

        PropertyTotalM2 propertyTotalM2 = new PropertyTotalM2(property.getPropName(), (double) Math.round(sum * 100)/100);

        return propertyTotalM2;
    }

}
