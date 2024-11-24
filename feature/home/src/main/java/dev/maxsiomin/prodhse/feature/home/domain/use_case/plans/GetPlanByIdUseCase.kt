package dev.maxsiomin.prodhse.feature.home.domain.use_case.plans

import dev.maxsiomin.common.domain.resource.LocalError
import dev.maxsiomin.common.domain.resource.Resource
import dev.maxsiomin.prodhse.core.util.DispatcherProvider
import dev.maxsiomin.prodhse.feature.home.domain.model.Plan
import dev.maxsiomin.prodhse.feature.home.domain.repository.PlansRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class GetPlanByIdUseCase @Inject constructor(
    private val plansRepo: PlansRepository,
    private val dispatchers: DispatcherProvider,
) {

    suspend operator fun invoke(
        planId: Long
    ): Resource<Plan, LocalError> = withContext(dispatchers.io) {
        return@withContext plansRepo.getPlanById(id = planId)
    }

}