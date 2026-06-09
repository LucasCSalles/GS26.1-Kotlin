# ViaOrbit

**Global Solution 2026 — FIAP**  

---

## Sobre o projeto

A ideia veio de um problema que qualquer pessoa que mora em São Paulo já viveu: você sai de casa na hora certa, escolhe o que parece ser o melhor caminho, e mesmo assim chega tarde. O trânsito travou do nada. Ou choveu. Ou um caminhão quebrou no meio da via.

Apps de navegação mostram onde o trânsito está ruim agora. O problema é que quando essa informação chega, já é tarde demais.

O **ViaOrbit** é um app de navegação preditiva que usa dados de satélites orbitais para antecipar colapsos de tráfego antes que eles aconteçam. A inteligência por trás disso é o **ORBITA GRID**, uma infraestrutura que combina imagens de satélites como GOES-16, Sentinel-1 e Sentinel-2 com histórico de tráfego e dados climáticos para gerar previsões com 30 a 90 minutos de antecedência.

O app foi pensado tanto para motoristas comuns quanto para caminhoneiros e gestores de frota, que têm ainda mais prejuízo quando pegam uma rota errada.

---

## Como o app funciona

O fluxo é simples e direto:

```
Home
 └─ "Acessar Monitoramento"
     └─ Monitor Urbano  (filtro por cidade)
         └─ "Analisar Rotas com IA"
             └─ Análise de Rotas  (filtro por tipo)
                 └─ tap em uma rota
                     └─ Análise Preditiva da Rota
```

---

## Telas

### Home

Tela de entrada do app. Tem o nome da solução, a proposta em uma frase, três features principais e um botão pra entrar no monitoramento. Fundo com gradiente escuro e identidade visual de tema espacial.

---

### Monitor Urbano

Essa tela mostra as zonas urbanas sendo monitoradas pelos satélites. Tem três cards no topo com números gerais (satélites ativos, total de zonas, zonas críticas) e um filtro por cidade logo abaixo — São Paulo, Rio de Janeiro e Curitiba.

Pra cada zona aparece um card com:
- o nome da zona e a cidade
- a porcentagem de chance de colapso, bem destacada
- uma barra de progresso colorida conforme o risco
- a previsão de tempo ("colapso em ~28 min")
- os fatores que estão causando o risco (chuva, horário de pico, obras...)
- qual satélite coletou os dados

No final da tela tem um botão pra ir pras rotas.

---

### Análise de Rotas

Lista as rotas disponíveis entre Osasco e Guarulhos com análise preditiva de cada uma. Tem um filtro em cima pra ver só as recomendadas pela IA, as de alto risco ou as indicadas pra logística.

Cada card de rota mostra tempo estimado, % de risco de congestionamento, % de estabilidade e uma barra de risco com o nível escrito (Baixo, Médio, Alto, Crítico). Quando a IA recomenda uma rota aparece um badge verde.

---

### Análise Preditiva da Rota

Tela de detalhe de uma rota específica. Tem a recomendação textual da IA do ORBITA GRID explicando o que foi detectado e por que deve ou não usar aquela rota. Abaixo vem os fatores preditivos individualmente (Tráfego, Clima, Caminhões, Alagamento) com a porcentagem de cada um e uma barra colorida. No topo da tela fica o timestamp de quando os dados foram atualizados e qual satélite os gerou.

---

## Estrutura do projeto

```
br.com.fiap.astroclima/
├── data/
│   ├── model/           # RiskLevel, RouteType, UrbanZone, RouteOption, PredictiveFactor
│   └── repository/      # OrbitaGridRepository — dados mockados
├── ui/
│   ├── navigation/      # Screen.kt, NavGraph.kt
│   ├── theme/           # cores, tipografia, tema escuro
│   ├── components/      # ZoneCard, RouteCard, StatCard, extensões de cor/ícone
│   └── screens/
│       ├── home/
│       ├── monitor/     # MonitorScreen + MonitorViewModel
│       ├── routes/      # RoutesScreen + RoutesViewModel
│       └── detail/      # RouteDetailScreen
└── MainActivity.kt
```

Os dados são todos mockados no `OrbitaGridRepository`, não precisa de chave de API nem conexão com internet pra rodar.

---

## Tecnologias usadas

- Kotlin 1.9.23
- Jetpack Compose (BOM 2024.04.01)
- Navigation Compose 2.7.7
- Material3 1.2.1
- ViewModel + StateFlow (Lifecycle 2.8.0)
- MVVM
- Min SDK 24 / Target SDK 34

---

## Como rodar

1. Abrir a pasta `AstroClima/` no Android Studio
2. Aguardar o Gradle Sync
3. Criar um emulador com API 26 ou superior pelo Device Manager
4. Dar play

---

## Screenshots

**Home**

![Home](screenshots/home.png)

**Monitor Urbano**

![Monitor Urbano](screenshots/monitor.png)

![Monitor Urbano 2](screenshots/monitor2.png)

**Análise de Rotas**

![Análise de Rotas](screenshots/rotas.png)

**Análise Preditiva da Rota**

![Análise Preditiva](screenshots/detalhes.png)
