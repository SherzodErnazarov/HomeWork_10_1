package uz.pdp.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.Hotel;
import uz.pdp.repository.HotelRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    HotelRepository hotelRepository;

    @PostMapping
    public String addHotel(@RequestBody Hotel hotel){
        hotelRepository.save(hotel);
        return "Successfully added";
    }


    @PutMapping("/{id}")
    public String editRoom(@PathVariable Integer id,@RequestBody Hotel hotel){
        Optional<Hotel> hotelRepositoryById = hotelRepository.findById(id);
        if (hotelRepositoryById.isPresent()){
            Hotel hotel1 = hotelRepositoryById.get();
            hotel1.setName(hotel.getName());
            hotelRepository.save(hotel1);
            return "Successfully edited";
        }
        return "This id does not exist";
    }

    @GetMapping("/{id}")
    public Hotel getHotel(@PathVariable Integer id){
        Optional<Hotel> byId = hotelRepository.findById(id);
        if (byId.isPresent()){
            return byId.get();
        }
        return new Hotel();
    }

    @GetMapping
    public List<Hotel> getHotels(){
        return hotelRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public String deleteHotel(@PathVariable Integer id){
        hotelRepository.deleteById(id);
        return "Successfully deleted";
    }
}
