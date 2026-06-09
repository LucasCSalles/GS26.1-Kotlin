package br.com.fiap.orbitagrid.data.model

enum class RiskLevel(val displayName: String, val level: Int) {
    SAFE("Seguro", 0),
    LOW("Baixo", 1),
    MEDIUM("Médio", 2),
    HIGH("Alto", 3),
    CRITICAL("Crítico", 4)
}
