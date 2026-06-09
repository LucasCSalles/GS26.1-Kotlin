package br.com.fiap.orbitagrid.data.repository

import br.com.fiap.orbitagrid.data.model.*

object OrbitaGridRepository {

    val urbanZones = listOf(
        UrbanZone(
            id = "1",
            name = "Zona Oeste",
            city = "São Paulo",
            collapseChance = 74,
            collapseInMinutes = 28,
            riskLevel = RiskLevel.CRITICAL,
            factors = listOf("Chuva forte", "Horário de pico", "Alto fluxo de caminhões"),
            affectedRoutes = 8,
            satelliteSource = "GOES-16"
        ),
        UrbanZone(
            id = "2",
            name = "Centro Histórico",
            city = "São Paulo",
            collapseChance = 61,
            collapseInMinutes = 45,
            riskLevel = RiskLevel.HIGH,
            factors = listOf("Evento esportivo", "Obras na Radial Leste"),
            affectedRoutes = 5,
            satelliteSource = "Sentinel-2"
        ),
        UrbanZone(
            id = "3",
            name = "Barra Funda",
            city = "São Paulo",
            collapseChance = 43,
            collapseInMinutes = 67,
            riskLevel = RiskLevel.MEDIUM,
            factors = listOf("Concentração de caminhões", "Terminal rodoviário lotado"),
            affectedRoutes = 3,
            satelliteSource = "GOES-16"
        ),
        UrbanZone(
            id = "4",
            name = "Marginal Pinheiros",
            city = "São Paulo",
            collapseChance = 31,
            collapseInMinutes = 90,
            riskLevel = RiskLevel.MEDIUM,
            factors = listOf("Chuva leve", "Obras no viaduto"),
            affectedRoutes = 4,
            satelliteSource = "Sentinel-1"
        ),
        UrbanZone(
            id = "5",
            name = "Zona Sul",
            city = "São Paulo",
            collapseChance = 18,
            collapseInMinutes = 120,
            riskLevel = RiskLevel.LOW,
            factors = listOf("Fluxo normal de pico"),
            affectedRoutes = 2,
            satelliteSource = "MODIS/Terra"
        ),
        UrbanZone(
            id = "6",
            name = "Via Dutra / Zona Norte",
            city = "Rio de Janeiro",
            collapseChance = 82,
            collapseInMinutes = 15,
            riskLevel = RiskLevel.CRITICAL,
            factors = listOf("Acidente na Via Dutra", "Chuva intensa", "Desvio de tráfego"),
            affectedRoutes = 11,
            satelliteSource = "GOES-16"
        ),
        UrbanZone(
            id = "7",
            name = "Centro / Linha Vermelha",
            city = "Rio de Janeiro",
            collapseChance = 49,
            collapseInMinutes = 55,
            riskLevel = RiskLevel.MEDIUM,
            factors = listOf("Congestionamento histórico", "Evento na Zona Sul"),
            affectedRoutes = 6,
            satelliteSource = "Sentinel-2"
        ),
        UrbanZone(
            id = "8",
            name = "Rebouças",
            city = "Curitiba",
            collapseChance = 22,
            collapseInMinutes = 95,
            riskLevel = RiskLevel.LOW,
            factors = listOf("Trânsito normal para o horário"),
            affectedRoutes = 2,
            satelliteSource = "Sentinel-2"
        )
    )

    private val routeOptions = listOf(
        RouteOption(
            id = "1",
            name = "Marginal Tietê",
            origin = "Osasco",
            destination = "Guarulhos",
            estimatedMinutes = 18,
            congestionProbability = 68,
            stabilityScore = 32,
            isRecommended = false,
            routeType = RouteType.ALL,
            orbitalRecommendation = "EVITAR. Alta probabilidade de colapso em 20 minutos. Combinação crítica de chuva, horário de pico e concentração de caminhões detectada pelo satélite GOES-16.",
            predictiveFactors = listOf(
                PredictiveFactor("Tráfego", 72, RiskLevel.HIGH),
                PredictiveFactor("Clima", 65, RiskLevel.HIGH),
                PredictiveFactor("Caminhões", 58, RiskLevel.MEDIUM),
                PredictiveFactor("Alagamento", 40, RiskLevel.MEDIUM)
            ),
            satelliteSource = "GOES-16",
            updatedAt = "3 min atrás"
        ),
        RouteOption(
            id = "2",
            name = "Rodovia Anhanguera",
            origin = "Osasco",
            destination = "Guarulhos",
            estimatedMinutes = 24,
            congestionProbability = 12,
            stabilityScore = 88,
            isRecommended = true,
            routeType = RouteType.RECOMMENDED,
            orbitalRecommendation = "RECOMENDADA. Rota estável para os próximos 40 minutos. Baixo risco climático e fluxo de tráfego regular. +6 minutos com muito mais previsibilidade.",
            predictiveFactors = listOf(
                PredictiveFactor("Tráfego", 18, RiskLevel.LOW),
                PredictiveFactor("Clima", 22, RiskLevel.LOW),
                PredictiveFactor("Caminhões", 15, RiskLevel.SAFE),
                PredictiveFactor("Alagamento", 8, RiskLevel.SAFE)
            ),
            satelliteSource = "Sentinel-2",
            updatedAt = "2 min atrás"
        ),
        RouteOption(
            id = "3",
            name = "Av. Paulista + Centro",
            origin = "Osasco",
            destination = "Guarulhos",
            estimatedMinutes = 31,
            congestionProbability = 45,
            stabilityScore = 55,
            isRecommended = false,
            routeType = RouteType.ALL,
            orbitalRecommendation = "RISCO MODERADO. Evento no Centro pode elevar o congestionamento nas próximas 2 horas. Monitore a situação antes de partir.",
            predictiveFactors = listOf(
                PredictiveFactor("Tráfego", 48, RiskLevel.MEDIUM),
                PredictiveFactor("Clima", 30, RiskLevel.LOW),
                PredictiveFactor("Caminhões", 25, RiskLevel.LOW),
                PredictiveFactor("Alagamento", 35, RiskLevel.MEDIUM)
            ),
            satelliteSource = "MODIS/Terra",
            updatedAt = "5 min atrás"
        ),
        RouteOption(
            id = "4",
            name = "Rodoanel Mário Covas",
            origin = "Osasco",
            destination = "Guarulhos",
            estimatedMinutes = 38,
            congestionProbability = 15,
            stabilityScore = 82,
            isRecommended = true,
            routeType = RouteType.LOGISTICS,
            orbitalRecommendation = "RECOMENDADA PARA LOGÍSTICA. Alta estabilidade para veículos pesados. Menor interferência urbana e baixo risco nos próximos 60 minutos segundo dados orbitais.",
            predictiveFactors = listOf(
                PredictiveFactor("Tráfego", 20, RiskLevel.LOW),
                PredictiveFactor("Clima", 28, RiskLevel.LOW),
                PredictiveFactor("Caminhões", 35, RiskLevel.MEDIUM),
                PredictiveFactor("Alagamento", 12, RiskLevel.SAFE)
            ),
            satelliteSource = "Sentinel-1",
            updatedAt = "1 min atrás"
        ),
        RouteOption(
            id = "5",
            name = "Av. Brasil — Zona Leste",
            origin = "Osasco",
            destination = "Guarulhos",
            estimatedMinutes = 27,
            congestionProbability = 71,
            stabilityScore = 28,
            isRecommended = false,
            routeType = RouteType.HIGH_RISK,
            orbitalRecommendation = "CRÍTICO. Satélite identificou bloqueio por acidente e risco de alagamento na Av. Brasil. Colapso previsto em 15 minutos. Não utilizar.",
            predictiveFactors = listOf(
                PredictiveFactor("Tráfego", 78, RiskLevel.CRITICAL),
                PredictiveFactor("Clima", 70, RiskLevel.HIGH),
                PredictiveFactor("Caminhões", 62, RiskLevel.HIGH),
                PredictiveFactor("Alagamento", 75, RiskLevel.CRITICAL)
            ),
            satelliteSource = "GOES-16",
            updatedAt = "1 min atrás"
        ),
        RouteOption(
            id = "6",
            name = "Corredor ABC Paulista",
            origin = "Osasco",
            destination = "Guarulhos",
            estimatedMinutes = 33,
            congestionProbability = 38,
            stabilityScore = 62,
            isRecommended = false,
            routeType = RouteType.ALL,
            orbitalRecommendation = "RISCO MODERADO. Fluxo elevado de caminhões industriais pode afetar a estabilidade em horário de pico. Monitoramento ativo pelo ORBITA GRID.",
            predictiveFactors = listOf(
                PredictiveFactor("Tráfego", 42, RiskLevel.MEDIUM),
                PredictiveFactor("Clima", 25, RiskLevel.LOW),
                PredictiveFactor("Caminhões", 55, RiskLevel.MEDIUM),
                PredictiveFactor("Alagamento", 20, RiskLevel.LOW)
            ),
            satelliteSource = "Sentinel-2",
            updatedAt = "4 min atrás"
        ),
        RouteOption(
            id = "7",
            name = "Via Anhembi — Frota",
            origin = "Osasco",
            destination = "Guarulhos",
            estimatedMinutes = 45,
            congestionProbability = 19,
            stabilityScore = 80,
            isRecommended = true,
            routeType = RouteType.LOGISTICS,
            orbitalRecommendation = "RECOMENDADA PARA FROTA. Menor interferência urbana e alta previsibilidade para os próximos 90 minutos. Indicada para caminhões e entregas críticas.",
            predictiveFactors = listOf(
                PredictiveFactor("Tráfego", 22, RiskLevel.LOW),
                PredictiveFactor("Clima", 30, RiskLevel.LOW),
                PredictiveFactor("Caminhões", 40, RiskLevel.MEDIUM),
                PredictiveFactor("Alagamento", 10, RiskLevel.SAFE)
            ),
            satelliteSource = "GOES-16",
            updatedAt = "6 min atrás"
        ),
        RouteOption(
            id = "8",
            name = "Marginal Pinheiros",
            origin = "Osasco",
            destination = "Guarulhos",
            estimatedMinutes = 22,
            congestionProbability = 55,
            stabilityScore = 45,
            isRecommended = false,
            routeType = RouteType.ALL,
            orbitalRecommendation = "ATENÇÃO. Obras em andamento reduzem a capacidade da via. Probabilidade moderada de congestionamento. Considere alternativa mais estável.",
            predictiveFactors = listOf(
                PredictiveFactor("Tráfego", 58, RiskLevel.MEDIUM),
                PredictiveFactor("Clima", 35, RiskLevel.MEDIUM),
                PredictiveFactor("Caminhões", 30, RiskLevel.LOW),
                PredictiveFactor("Alagamento", 50, RiskLevel.MEDIUM)
            ),
            satelliteSource = "Sentinel-1",
            updatedAt = "2 min atrás"
        )
    )

    fun getAllZones(): List<UrbanZone> = urbanZones
    fun getZonesByCity(city: String): List<UrbanZone> = urbanZones.filter { it.city == city }
    fun getCities(): List<String> = urbanZones.map { it.city }.distinct()
    fun getCriticalZonesCount(): Int = urbanZones.count { it.riskLevel == RiskLevel.CRITICAL }
    fun getActiveSatellitesCount(): Int = (urbanZones.map { it.satelliteSource } + routeOptions.map { it.satelliteSource }).distinct().size

    fun getAllRoutes(): List<RouteOption> = routeOptions
    fun getRouteById(id: String): RouteOption? = routeOptions.find { it.id == id }
    fun getRoutesByType(type: RouteType): List<RouteOption> = when (type) {
        RouteType.ALL -> routeOptions
        RouteType.HIGH_RISK -> routeOptions.filter { it.congestionProbability >= 50 }
        else -> routeOptions.filter { it.routeType == type }
    }
}
