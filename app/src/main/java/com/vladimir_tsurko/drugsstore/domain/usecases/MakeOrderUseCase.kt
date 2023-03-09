package com.vladimir_tsurko.drugsstore.domain.usecases

import com.vladimir_tsurko.drugsstore.data.remote.dto.authDto.AuthResponseDto
import com.vladimir_tsurko.drugsstore.domain.Repository
import com.vladimir_tsurko.drugsstore.domain.models.PurchaseModel
import com.vladimir_tsurko.drugsstore.utils.Resource
import javax.inject.Inject

class MakeOrderUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(purchaseModels: List<PurchaseModel>, address: String) = repository.makeOrder(purchaseModels, address)

}