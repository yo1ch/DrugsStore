package com.vladimir_tsurko.drugsstore.domain.usecases

import com.vladimir_tsurko.drugsstore.domain.Repository
import com.vladimir_tsurko.drugsstore.domain.models.PurchaseItemModel
import javax.inject.Inject

class MakeOrderUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(purchaseModels: List<PurchaseItemModel>, address: String) = repository.makeOrder(purchaseModels, address)

}