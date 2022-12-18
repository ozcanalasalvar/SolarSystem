package com.ozcan.alasalvar.solarsystemapp.domain.repository

import com.ozcan.alasalvar.solarsystemapp.domain.model.Planet
import com.ozcan.alasalvar.solarsystemapp.domain.util.Resource

interface Repository {

    suspend fun getPlanets(): Resource<List<Planet>>

    suspend fun getPlanet(position: Int): Resource<Planet>
}