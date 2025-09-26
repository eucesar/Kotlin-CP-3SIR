# Apresentação do Projeto: Fundamentos Jetpack Compose - Listas Lazy

## Visão Geral do Projeto

Este projeto Android demonstra os conceitos fundamentais do Jetpack Compose, especificamente o uso de listas lazy para exibir uma coleção de jogos favoritos. O aplicativo permite filtrar jogos por estúdio e apresenta uma interface moderna e responsiva.

---

## 🎯 **VILELA - Estrutura do Projeto e Configuração**

### Configuração do Projeto

#### 1. **build.gradle.kts (Nível do Projeto)**
```kotlin
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}
```

**Explicação:** Este arquivo define os plugins que serão aplicados em todo o projeto. O `apply false` significa que os plugins são declarados mas não aplicados diretamente aqui, sendo aplicados nos módulos específicos.

#### 2. **app/build.gradle.kts (Módulo Principal)**
```kotlin
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "eucesar.com.github.fundamentos_jetpack_compose_listas_lazy"
    compileSdk = 35
    
    defaultConfig {
        applicationId = "eucesar.com.github.fundamentos_jetpack_compose_listas_lazy"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
    
    buildFeatures {
        compose = true
    }
}
```

**Explicação:** 
- **namespace:** Define o pacote principal da aplicação
- **compileSdk:** Versão do Android SDK usada para compilar
- **minSdk:** Versão mínima do Android suportada (Android 7.0)
- **targetSdk:** Versão alvo do Android
- **buildFeatures.compose:** Habilita o Jetpack Compose

#### 3. **Dependências Principais**
```kotlin
dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.material3)
}
```

**Explicação:**
- **core-ktx:** Extensões Kotlin para Android
- **lifecycle-runtime-ktx:** Gerenciamento de ciclo de vida
- **activity-compose:** Integração do Compose com Activity
- **compose-bom:** Bill of Materials para versões consistentes
- **material3:** Componentes de design Material 3

#### 4. **AndroidManifest.xml**
```xml
<application
    android:allowBackup="true"
    android:icon="@mipmap/ic_launcher"
    android:label="@string/app_name"
    android:theme="@style/Theme.Fundamentosjetpackcomposelistaslazy">
    <activity
        android:name=".MainActivity"
        android:exported="true"
        android:theme="@style/Theme.Fundamentosjetpackcomposelistaslazy">
        <intent-filter>
            <action android:name="android.intent.action.MAIN" />
            <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
    </activity>
</application>
```

**Explicação:**
- **MainActivity:** Activity principal da aplicação
- **intent-filter:** Define a MainActivity como ponto de entrada
- **exported="true":** Permite que outros apps iniciem esta activity

---

## 🎮 **SAMUEL - Modelo de Dados e Lógica de Negócio**

### 1. **Modelo de Dados - Game.kt**
```kotlin
data class Game(
    val id: Long = 0,
    val title: String = "",
    val studio: String = "",
    val releaseYear: Int = 0
)
```

**Explicação:**
- **data class:** Classe especial do Kotlin que gera automaticamente métodos como `equals()`, `hashCode()`, `toString()` e `copy()`
- **Propriedades imutáveis:** Todas as propriedades são `val`, garantindo imutabilidade
- **Valores padrão:** Cada propriedade tem um valor padrão, facilitando a criação de instâncias

### 2. **Repositório de Dados - GameList.kt**

#### Função para Obter Todos os Jogos
```kotlin
fun getAllGames(): List<Game> {
    return listOf(
        Game(id = 1, title = "Double Dragon", studio = "Technos", releaseYear = 1987),
        Game(id = 2, title = "Batletoads", studio = "Tradewest", releaseYear = 1991),
        Game(id = 3, title = "Enduro", studio = "Activision", releaseYear = 1983),
        // ... mais jogos
    )
}
```

**Explicação:**
- **List<Game>:** Retorna uma lista imutável de jogos
- **listOf():** Função do Kotlin para criar listas imutáveis
- **Dados estáticos:** Simula uma fonte de dados (em um app real, viria de uma API ou banco de dados)

#### Função para Filtrar por Estúdio
```kotlin
fun getGamesByStudio(studio: String): List<Game> {
    return getAllGames().filter {
        it.studio.startsWith(prefix = studio, ignoreCase = true)
    }
}
```

**Explicação:**
- **filter():** Função de alta ordem que filtra elementos da lista
- **startsWith():** Verifica se o nome do estúdio começa com o texto pesquisado
- **ignoreCase = true:** Torna a busca case-insensitive
- **it:** Referência implícita ao elemento atual da lista

### 3. **Tema e Estilização**

#### Color.kt
```kotlin
val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
```

**Explicação:**
- **Cores em hexadecimal:** Formato ARGB (Alpha, Red, Green, Blue)
- **Sufixos 80/40:** Diferentes intensidades para temas claro e escuro
- **Material Design:** Cores seguem as diretrizes do Material Design

#### Theme.kt
```kotlin
@Composable
fun FundamentosjetpackcomposelistaslazyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
```

**Explicação:**
- **@Composable:** Anotação que marca uma função como componente Compose
- **isSystemInDarkTheme():** Detecta automaticamente o tema do sistema
- **dynamicColor:** Usa cores dinâmicas do Android 12+
- **MaterialTheme:** Aplica o tema Material Design ao conteúdo

---

## 🎨 **CÉSAR - Interface do Usuário e Componentes**

### 1. **MainActivity.kt - Ponto de Entrada**

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FundamentosjetpackcomposelistaslazyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GamesScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
```

**Explicação:**
- **ComponentActivity:** Activity otimizada para Jetpack Compose
- **enableEdgeToEdge():** Permite que o conteúdo use toda a tela
- **setContent:** Define o conteúdo da activity usando Compose
- **Scaffold:** Layout base que fornece estrutura para a tela
- **innerPadding:** Espaçamento interno para evitar sobreposição com barras do sistema

### 2. **Tela Principal - GamesScreen**

#### Estado da Tela
```kotlin
@Composable
fun GamesScreen(modifier: Modifier = Modifier) {
    var searchTextState by remember { mutableStateOf("") }
    var gamesListState by remember { mutableStateOf(getAllGames()) }
```

**Explicação:**
- **remember:** Mantém o estado durante recomposições
- **mutableStateOf:** Cria estado mutável observável
- **by:** Delegation property que simplifica o acesso ao estado
- **searchTextState:** Controla o texto da busca
- **gamesListState:** Controla a lista de jogos exibida

#### Campo de Busca
```kotlin
OutlinedTextField(
    value = searchTextState,
    onValueChange = { searchTextState = it },
    modifier = Modifier.fillMaxWidth(),
    label = { Text(text = "Nome do estúdio") },
    trailingIcon = {
        IconButton(onClick = { gamesListState = getGamesByStudio(searchTextState) }) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = ""
            )
        }
    }
)
```

**Explicação:**
- **OutlinedTextField:** Campo de texto com borda
- **value/onValueChange:** Binding bidirecional do estado
- **fillMaxWidth():** Ocupa toda a largura disponível
- **trailingIcon:** Ícone no final do campo
- **IconButton:** Botão clicável com ícone

#### Botão de Limpar Filtro
```kotlin
if (searchTextState.isNotEmpty() || gamesListState != getAllGames()) {
    Text(
        text = "Limpar filtro",
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .clickable {
                searchTextState = ""
                gamesListState = getAllGames()
            },
        fontWeight = FontWeight.SemiBold,
        color = androidx.compose.ui.graphics.Color.Blue
    )
}
```

**Explicação:**
- **Condição if:** Só exibe quando há filtro ativo
- **clickable:** Torna o texto clicável
- **padding:** Adiciona espaçamento
- **FontWeight.SemiBold:** Define peso da fonte

### 3. **Listas Lazy**

#### LazyRow para Estúdios
```kotlin
LazyRow(){
    items(gamesListState){ game ->
        StudioCard(game = game, onClick = {
            searchTextState = game.studio
            gamesListState = getGamesByStudio(game.studio)
        })
    }
}
```

**Explicação:**
- **LazyRow:** Lista horizontal que renderiza apenas itens visíveis
- **items():** Função que itera sobre a lista
- **onClick:** Callback executado ao clicar no card
- **Performance:** Renderiza apenas itens visíveis, economizando memória

#### LazyColumn para Jogos
```kotlin
LazyColumn() {
    items(gamesListState) {
        GameCard(game = it)
    }
}
```

**Explicação:**
- **LazyColumn:** Lista vertical otimizada
- **it:** Referência implícita ao elemento atual
- **Recomposição:** Apenas itens alterados são recompostos

### 4. **Componentes Reutilizáveis**

#### GameCard.kt
```kotlin
@Composable
fun GameCard(game: Game) {
    Card(modifier = Modifier.padding(bottom = 8.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .weight(3f)
            ) {
                Text(
                    text = game.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = game.studio,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            }
            Text(
                text = game.releaseYear.toString(),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
        }
    }
}
```

**Explicação:**
- **Card:** Container com elevação e sombra
- **Row:** Layout horizontal
- **Column:** Layout vertical
- **weight():** Define proporção do espaço ocupado
- **Alignment.CenterVertically:** Centraliza verticalmente
- **Arrangement.SpaceBetween:** Distribui espaço entre elementos

#### StudioCard.kt
```kotlin
@Composable
fun StudioCard(game: Game, onClick: (() -> Unit)? = null) {
    Card(modifier = Modifier
        .size(100.dp)
        .padding(end = 4.dp)
        .clickable(enabled = onClick != null) { onClick?.invoke() }) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = game.studio)
        }
    }
}
```

**Explicação:**
- **size(100.dp):** Define tamanho fixo
- **clickable:** Torna o card clicável
- **enabled:** Controla se o clique está habilitado
- **onClick?.invoke():** Executa o callback se não for null
- **Arrangement.Center:** Centraliza o conteúdo

### 5. **Previews para Desenvolvimento**

```kotlin
@Preview(showBackground = true, name = "Games Screen Preview")
@Composable
fun PreviewGamesScreen() {
    FundamentosjetpackcomposelistaslazyTheme {
        GamesScreen()
    }
}
```

**Explicação:**
- **@Preview:** Anotação que gera preview no Android Studio
- **showBackground:** Mostra fundo no preview
- **name:** Nome identificador do preview
- **Desenvolvimento:** Facilita o desenvolvimento visual sem executar o app

---

## 🚀 **Como Executar o Projeto**

### Pré-requisitos
- Android Studio (versão mais recente)
- JDK 11 ou superior
- Android SDK com API 24+

### Passos para Execução

1. **Clone ou baixe o projeto**
2. **Abra no Android Studio**
3. **Sincronize o projeto** (Sync Project with Gradle Files)
4. **Execute o projeto** (Run 'app' ou Shift+F10)

### Estrutura de Arquivos
```
app/src/main/java/eucesar/com/github/fundamentos_jetpack_compose_listas_lazy/
├── MainActivity.kt              # Activity principal
├── componentes/
│   ├── GameCard.kt             # Card para exibir jogos
│   └── StudioCard.kt           # Card para exibir estúdios
├── model/
│   └── Game.kt                 # Modelo de dados
├── repository/
│   └── GameList.kt             # Fonte de dados
└── ui/theme/
    ├── Color.kt                # Cores do tema
    ├── Theme.kt                # Configuração do tema
    └── Type.kt                 # Tipografia
```

---

## 📱 **Funcionalidades do App**

1. **Lista de Jogos:** Exibe uma lista vertical de jogos favoritos
2. **Filtro por Estúdio:** Permite buscar jogos por nome do estúdio
3. **Cards de Estúdio:** Lista horizontal de estúdios clicáveis
4. **Limpar Filtro:** Botão para resetar a busca
5. **Interface Responsiva:** Adapta-se a diferentes tamanhos de tela

---

## 🎓 **Conceitos Aprendidos**

- **Jetpack Compose:** Framework moderno para UI Android
- **Listas Lazy:** Otimização de performance para listas grandes
- **Estado Reativo:** Gerenciamento de estado com `remember` e `mutableStateOf`
- **Componentes Reutilizáveis:** Criação de componentes modulares
- **Material Design 3:** Sistema de design moderno
- **Kotlin:** Linguagem de programação concisa e expressiva

---

*Projeto desenvolvido para demonstrar os fundamentos do Jetpack Compose com foco em listas lazy e componentes reutilizáveis.*
