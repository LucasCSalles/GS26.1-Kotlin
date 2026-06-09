package br.com.fiap.orbitagrid.data.model

data class PredictiveFactor(
    val name: String,
    val value: Int,
    val riskLevel: RiskLevel
)

data class RouteOption(
    val id: String,
    val name: String,
    val origin: String,
    val destination: String,
    val estimatedMinutes: Int,
    val congestionProbability: Int,
    val stabilityScore: Int,
    val isRecommended: Boolean,
    val routeType: RouteType,
    val orbitalRecommendation: String,
    val predictiveFactors: List<PredictiveFactor>,
    val satelliteSource: String,
    val updatedAt: String
)

data class UrbanZone(
    val id: String,
    val name: String,
    val city: String,
    val collapseChance: Int,
    val collapseInMinutes: Int,
    val riskLevel: RiskLevel,
    val factors: List<String>,
    val affectedRoutes: Int,
    val satelliteSource: String
)
