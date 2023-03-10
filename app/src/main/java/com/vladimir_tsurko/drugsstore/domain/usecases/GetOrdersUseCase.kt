package com.vladimir_tsurko.drugsstore.domain.usecases

import com.vladimir_tsurko.drugsstore.domain.Repository
import com.vladimir_tsurko.drugsstore.domain.models.OrdersModel
import javax.inject.Inject

class GetOrdersUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(): List<OrdersModel> = repository.getOrders()

}