package hu.progmasters.config;

import hu.progmasters.domain.Account;
import hu.progmasters.domain.Transaction;
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
                .setMatchingStrategy(MatchingStrategies.STRICT);

        //these configs ensure that when making a copy of an existing entity the
        // selected fields are not copied over, in this case the Id
        modelMapper.typeMap(Account.class, Account.class)
                .addMappings(mapper -> mapper.skip(Account::setId));
        modelMapper.typeMap(Transaction.class, Transaction.class)
                .addMappings(mapper -> mapper.skip(Transaction::setId));

//        modelMapper.createTypeMap(Dwarf.class, DwarfDetailsWithItems.class)
//                .addMapping(dwarf -> dwarf.getName(), DwarfDetailsWithItems::setName);

        return modelMapper;
    }

}
