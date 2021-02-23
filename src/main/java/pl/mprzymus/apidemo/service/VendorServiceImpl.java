package pl.mprzymus.apidemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mprzymus.apidemo.api.mapper.VendorMapper;
import pl.mprzymus.apidemo.api.model.VendorDTO;
import pl.mprzymus.apidemo.api.model.VendorListDTO;
import pl.mprzymus.apidemo.controllers.VendorController;
import pl.mprzymus.apidemo.domain.Vendor;
import pl.mprzymus.apidemo.repositories.VendorRepository;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {

    private final VendorMapper vendorMapper;
    private final VendorRepository vendorRepository;

    @Override
    public VendorListDTO getAllVendors() {
        var list = vendorRepository.findAll().stream()
                .map(vendorMapper::vendorToVendorDTO)
                .collect(Collectors.toList());
        return new VendorListDTO(list);
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        var vendor = vendorRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return convertToDto(vendor);
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        var toSave = vendorMapper.vendorDtoToVendor(vendorDTO);
        return saveVendor(toSave);
    }

    private VendorDTO saveVendor(Vendor toSave) {
        var saved = vendorRepository.save(toSave);
        return convertToDto(saved);
    }

    @Override
    public VendorDTO saveOrUpdateVendorByDTO(Long id, VendorDTO vendorDTO) {
        var toSave = vendorMapper.vendorDtoToVendor(vendorDTO);
        toSave.setId(id);
        return saveVendor(toSave);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        if (vendorRepository.existsById(id)) {
            return saveOrUpdateVendorByDTO(id, vendorDTO);
        }
        throw new ResourceNotFoundException();
    }

    @Override
    public void deleteVendorById(Long id) {
        try {
            vendorRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }
    }

    private VendorDTO convertToDto(Vendor saved) {
        var toReturn = vendorMapper.vendorToVendorDTO(saved);
        toReturn.setVendorUrl(VendorController.URL + "/" + saved.getId());
        return toReturn;
    }
}
