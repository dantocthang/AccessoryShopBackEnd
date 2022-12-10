package com.nhom9.springjwt.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nhom9.springjwt.models.Address;
import com.nhom9.springjwt.models.City;
import com.nhom9.springjwt.models.District;
import com.nhom9.springjwt.models.User;
import com.nhom9.springjwt.models.Ward;
import com.nhom9.springjwt.payload.request.AddressRequest;
import com.nhom9.springjwt.payload.response.MessageResponse;
import com.nhom9.springjwt.repository.AddressRepository;
import com.nhom9.springjwt.repository.CityRepository;
import com.nhom9.springjwt.repository.DistrictRepository;
import com.nhom9.springjwt.repository.UserRepository;
import com.nhom9.springjwt.repository.WardRepository;

@CrossOrigin(origins = "http://127.0.0.1:5173", maxAge = 3600)
@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    AddressRepository addressRepo;
    @Autowired
    CityRepository cityRepo;
    @Autowired
    DistrictRepository districtRepo;
    @Autowired
    WardRepository wardRepo;
    @Autowired
    UserRepository userRepo;

    @GetMapping("/city")
    public ResponseEntity<List<City>> getCities() {
        try {
            List<City> cities = cityRepo.findAll();
            return new ResponseEntity<>(cities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/district")
    public ResponseEntity<List<District>> getDistricts(@RequestParam Optional<Long> city_id) {
        try {
            List<District> districts;
            if (city_id.isPresent()) {
                districts = districtRepo.findAllByCity_Id(city_id.get());
            } else
                districts = districtRepo.findAll();

            return new ResponseEntity<>(districts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ward")
    public ResponseEntity<List<Ward>> getWards(@RequestParam Optional<Long> district_id) {
        try {
            List<Ward> wards;
            if (district_id.isPresent()) {
                wards = wardRepo.findAllByDistrict_Id(district_id.get());
            } else
                wards = wardRepo.findAll();

            return new ResponseEntity<>(wards, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Address>> getAddresses(@PathVariable("id") Long user_id) {
        try {
            List<Address> addresses = addressRepo.findByUser_Id(user_id);
            return new ResponseEntity<>(addresses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/create", consumes = { "*/*" })
    public ResponseEntity<Address> createAddress(@Valid @RequestBody AddressRequest address) {
        try {
            Ward w = wardRepo.findById(address.getWard_id()).get();
            User u = userRepo.findById(address.getUser_id()).get();
            Address a = new Address(address.getAddress(), address.getPhone(), w, u);
            return new ResponseEntity<>(addressRepo.save(a), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = { "*/*" })
    public ResponseEntity<Address> createAddress(@Valid @RequestBody AddressRequest address,
            @PathVariable("id") Long id) {
        try {
            Optional<Address> a = addressRepo.findById(id);

            if (a.isPresent()) {
                Ward w = wardRepo.findById(address.getWard_id()).get();
                a.get().setWard(w);
                a.get().setPhone(address.getPhone());
                a.get().setAddress(address.getAddress());

                return new ResponseEntity<>(addressRepo.save(a.get()), HttpStatus.OK);

            } else {
                throw new InvalidConfigurationPropertyValueException("addressId", id, "Not found");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", consumes = { "*/*" })
    public ResponseEntity<String> deleteAddress(@PathVariable("id") Long id) {
        try {
            if (addressRepo.findById(id).get().getId().equals(id)) {
                addressRepo.deleteById(id);
                return new ResponseEntity<>("Deleted", HttpStatus.OK);
            } else
                throw new InvalidConfigurationPropertyValueException("id", id, "Not found");
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
