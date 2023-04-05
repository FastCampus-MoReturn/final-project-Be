package com.fastcampus.finalprojectbe.openapi.service.impl;

import com.fastcampus.finalprojectbe.global.exception.NoSearchAdressException;
import com.fastcampus.finalprojectbe.openapi.repository.AddressRepository;
import com.fastcampus.finalprojectbe.openapi.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public String searchsiGunGuCodeCode(String address) {

        String addressCode = findAddressCode(address);
        return addressCode.substring(0, 5);

    }

    public String findAddressCode(String address) {

        String[] addressList = address.split(" ");
        StringBuilder sb = new StringBuilder();
        int count = 0;
        if (addressList[2].endsWith("동")) {
            count = 3;
        } else {
            count = 4;
        }
        for (int i = 0; i < count; i++) {
            sb.append(addressList[i]);
            sb.append(" ");
        }
        address = sb.toString().trim();

        return addressRepository.findByLegdongName(address)
                .orElseThrow(IllegalArgumentException::new).getLegdongCode();
    }


    public String findJibun(String address) {

        String[] addressList = address.split(" ");
        int count = 0;
        if (addressList[2].endsWith("동")) {
            count = 3;
        } else {
            count = 4;
        }

        return addressList[count].trim();

    }


    public String findAptName(String address) {

        String[] addressList = address.split(" ");
        int count = 0;
        if (addressList[2].endsWith("동")) {
            count = 3;
        } else {
            count = 4;
        }

        return addressList[count + 1].trim();

    }

}
