package com.flagos.domain.interactor

interface GetMarkItemAsRemovedUseCase {

    suspend operator fun invoke(id: String)
}