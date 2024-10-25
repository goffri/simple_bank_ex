package hu.progmasters.config;

import hu.progmasters.dwarfminingapp2.domain.Item;
import hu.progmasters.dwarfminingapp2.domain.Rune;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STANDARD);

        //these configs ensure that when making a copy of an existing entity the
        // selected fields are not copied over, in this case the Id
//        modelMapper.typeMap(Item.class, Item.class)
//                .addMappings(mapper -> mapper.skip(Item::setId));
//        modelMapper.typeMap(Rune.class, Rune.class)
//                .addMappings(mapper -> mapper.skip(Rune::setId));

//        modelMapper.createTypeMap(Dwarf.class, DwarfDetailsWithItems.class)
//                .addMapping(dwarf -> dwarf.getName(), DwarfDetailsWithItems::setName);

        return modelMapper;
    }

}
