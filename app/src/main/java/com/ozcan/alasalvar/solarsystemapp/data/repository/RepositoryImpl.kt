package com.ozcan.alasalvar.solarsystemapp.data.repository

import com.ozcan.alasalvar.solarsystemapp.data.Planets
import com.ozcan.alasalvar.solarsystemapp.domain.model.Planet
import com.ozcan.alasalvar.solarsystemapp.domain.repository.Repository
import com.ozcan.alasalvar.solarsystemapp.domain.util.Resource
import kotlinx.coroutines.delay
import javax.inject.Inject

class RepositoryImpl @Inject constructor() : Repository {

    private val planets = Planets.planets

    override suspend fun getPlanets(): Resource<List<Planet>> {
        delay(500)
        return Resource.Success(planets)
    }

    override suspend fun getPlanet(position: Int): Resource<Planet> {
        delay(200)
        return Resource.Success(planets.filter { planet -> planet.position == position }[0])
    }

}