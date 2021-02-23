package pl.mprzymus.apidemo.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.mprzymus.apidemo.api.model.VendorDTO;
import pl.mprzymus.apidemo.domain.Vendor;

@Mapper
public interface VendorMapper {
    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    @Mapping(target = "vendorUrl", ignore = true)
    VendorDTO vendorToVendorDTO(Vendor vendor);

    @Mapping(target = "id", ignore = true)
    Vendor vendorDtoToVendor(VendorDTO vendorDTO);
}
