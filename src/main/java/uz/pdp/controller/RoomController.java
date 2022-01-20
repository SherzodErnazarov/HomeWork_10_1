package uz.pdp.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dto.RoomDto;
import uz.pdp.entity.Hotel;
import uz.pdp.entity.Room;
import uz.pdp.repository.HotelRepository;
import uz.pdp.repository.RoomRepository;

import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {
    RoomRepository roomRepository;
    HotelRepository hotelRepository;
    @PostMapping
    public String addRoom(@RequestBody RoomDto roomDto){
       Room room = new Room();
       room.setNumber(roomDto.getNumber());
       room.setFloor(roomDto.getFloor());
       room.setSize(roomDto.getSize());
        Optional<Hotel> byId = hotelRepository.findById(roomDto.getHotelId());
        if (byId.isPresent()){
            room.setHotel(byId.get());
            roomRepository.save(room);
            return "Successfully added";
        }
        return "Hotel does not exist";
    }

    @PutMapping("/{id}")
    public String editRoom(@PathVariable Integer id,@RequestBody RoomDto roomDto){
        Optional<Room> byId = roomRepository.findById(id);
        if (byId.isPresent()){
            Room room = byId.get();
            room.setNumber(roomDto.getNumber());
            room.setSize(room.getSize());
            room.setFloor(roomDto.getFloor());
            Optional<Hotel> byId1 = hotelRepository.findById(roomDto.getHotelId());
            if (byId1.isPresent()){
                room.setHotel(byId1.get());
                return "Successfully edited";
            }
        }
        return "This room does not exist";
    }

    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable Integer id){
        roomRepository.deleteById(id);
        return "Successfully deleted";
    }

    @GetMapping("/forMinistry")
    public Page<Room> getRoomMinistry(@RequestParam int page){
        Pageable pageable = PageRequest.of(page,10);
        Page<Room> all = roomRepository.findAll(pageable);
        return all;
    }

    @GetMapping("/forHotel/{id}")
    public Page<Room> getRoomForHotel(@PathVariable Integer id,@RequestParam Integer page){
        Page<Room> roomPage = roomRepository.findAllByHotelId(id,page);
        return roomPage;
    }

    @GetMapping("/{id}")
    public Room getRoom(@PathVariable Integer id){
        Optional<Room> byId = roomRepository.findById(id);
        if (byId.isPresent())
        return byId.get();
        return new Room();
    }
}
