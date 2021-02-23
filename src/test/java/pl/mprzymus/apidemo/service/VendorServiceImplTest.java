package pl.mprzymus.apidemo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.mprzymus.apidemo.api.mapper.VendorMapper;
import pl.mprzymus.apidemo.api.model.VendorDTO;
import pl.mprzymus.apidemo.domain.Vendor;
import pl.mprzymus.apidemo.repositories.VendorRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VendorServiceImplTest {

    @Mock
    private VendorRepository vendorRepository;

    private VendorService vendorService;
    private Vendor vendor1;

    private static final String NAME_1 = "Vendor1";
    private static final Long ID_1 = 1L;
    private static final String NAME_2 = "Vendor2";
    private static final Long ID_2 = 1L;

    @BeforeEach
    void setUp() {
        vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);

        vendor1 = new Vendor();
        vendor1.setName(NAME_1);
        vendor1.setId(ID_1);
    }

    @Test
    void getAllVendors() {
        var list = List.of(new Vendor(), new Vendor());

        when(vendorRepository.findAll()).thenReturn(list);

        var result = vendorService.getAllVendors();

        assertEquals(list.size(), result.getVendors().size());
    }

    @Test
    public void getVendorById() {

        //mockito BDD syntax
        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor1));

        //when
        VendorDTO vendorDTO = vendorService.getVendorById(1L);

        //then
        then(vendorRepository).should(times(1)).findById(anyLong());

        //JUnit Assert that with matchers
        assertEquals(vendorDTO.getName(), NAME_1);
    }

    @Test
    void getVendorUnknownId() {
        given(vendorRepository.findById(any())).willReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> vendorService.getVendorById(ID_2 + 1L));
    }

    @Test
    void createNewVendor() {
        var vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME_1);

        given(vendorRepository.save(any())).willReturn(vendor1);

        VendorDTO savedVendorDTO = vendorService.createNewVendor(vendorDTO);

        then(vendorRepository).should().save(any());
        assertTrue(savedVendorDTO.getVendorUrl().contains(vendor1.getId().toString()));
    }

    @Test
    public void saveVendorByDTO() {
        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME_1);

        given(vendorRepository.save(any())).willReturn(vendor1);

        //when
        var savedVendorDTO = vendorService.saveOrUpdateVendorByDTO(ID_1, vendorDTO);

        then(vendorRepository).should().save(any(Vendor.class));
        assertTrue(savedVendorDTO.getVendorUrl().contains(vendor1.getId().toString()));
    }

    @Test
    public void patchVendor() {
        //given
        var vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME_1);

        given(vendorRepository.existsById(anyLong())).willReturn(true);
        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor1);

        //when

        var savedVendorDTO = vendorService.patchVendor(ID_1, vendorDTO);

        //then
        then(vendorRepository).should().save(any(Vendor.class));
        then(vendorRepository).should(times(1)).existsById(anyLong());
        assertTrue(savedVendorDTO.getVendorUrl().contains(vendor1.getId().toString()));
    }

    @Test
    public void deleteVendorById() {

        //when
        vendorService.deleteVendorById(1L);

        //then
        then(vendorRepository).should().deleteById(anyLong());
    }
}