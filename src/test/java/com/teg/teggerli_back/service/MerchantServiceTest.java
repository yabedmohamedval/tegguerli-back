package com.teg.teggerli_back.service;

import com.teg.teggerli_back.domain.enums.PaymentMethod;
import com.teg.teggerli_back.domain.enums.UserRole;
import com.teg.teggerli_back.domain.users.Merchant;
import com.teg.teggerli_back.dto.domain.CreateMerchantDTO;
import com.teg.teggerli_back.repository.MerchantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MerchantServiceTest {

    @Mock
    MerchantRepository merchantRepository;

    @InjectMocks
    MerchantService merchantService;

    @Test
    void createMerchant_shouldSaveAndReturnDto( ) {
        CreateMerchantDTO dto = new CreateMerchantDTO(
                "Ali", "Phones Shop", "31344555",
                PaymentMethod.MASRVI, UserRole.MERCHANT,
                "1234", "1234"
        );

        when(merchantRepository.save(any(Merchant.class)))
                .thenAnswer(inv -> {
                    Merchant m = inv.getArgument(0);
                    m.setId(1L);
                    return m;
                });

        merchantService.createMerchant(dto);


        ArgumentCaptor<Merchant> captor = ArgumentCaptor.forClass(Merchant.class);
        verify(merchantRepository).save(captor.capture());

        Merchant passed = captor.getValue();
        assertThat(passed.getName()).isEqualTo("Ali");
        assertThat(passed.getShopName()).isEqualTo("Phones Shop");
    }

}
