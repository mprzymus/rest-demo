package pl.mprzymus.apidemo.api.mapper;

import org.junit.jupiter.api.Test;
import pl.mprzymus.apidemo.api.model.VendorDTO;
import pl.mprzymus.apidemo.domain.Vendor;

import static org.junit.jupiter.api.Assertions.*;

class VendorMapperTest {
    private final VendorMapper vendorMapper = VendorMapper.INSTANCE;
    private final String NAME = "Name";
    @Test
    void vendorToVendorDTO() {
        var vendor = new Vendor();
        vendor.setName(NAME);

        var dto = vendorMapper.vendorToVendorDTO(vendor);

        assertEquals(vendor.getName(), dto.getName());
    }

    @Test
    void vendorDtoToVendor() {
        var vendor = new VendorDTO();
        vendor.setName(NAME);

        var dto = vendorMapper.vendorDtoToVendor(vendor);

        assertEquals(vendor.getName(), dto.getName());
    }
}