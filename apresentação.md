# Apresentação do Código: Fundamentos Jetpack Compose - Listas Lazy

## Visão Geral

Este documento apresenta a explicação detalhada dos códigos do projeto Android que demonstra o uso de Jetpack Compose com listas lazy para exibir uma coleção de jogos favoritos.

---

## 🎯 **VILELA - Modelo de Dados e Repositório**

### 1. **Modelo de Dados - Game.kt**

```kotlin
package eucesar.com.github.fundamentos_jetpack_compose_listas_lazy.model

data class Game(
    val id: Long = 0,
    val title: String = "",
    val studio: String = "",
    val releaseYear: Int = 0
)
```

**Explicação:**
- **`data class`:** Classe especial do Kotlin que gera automaticamente métodos como `equals()`, `hashCode()`, `toString()` e `copy()`
- **Propriedades imutáveis:** Todas as propriedades são `val`, garantindo que os dados não sejam alterados após a criação
- **Valores padrão:** Cada propriedade tem um valor padrão, facilitando a criação de instâncias
- **Estrutura simples:** Representa um jogo com ID, título, estúdio e ano de lançamento

### 2. **Repositório de Dados - GameList.kt**

#### Função para Obter Todos os Jogos
```kotlin
fun getAllGames(): List<Game> {
    return listOf(
        Game(id = 1, title = "Double Dragon", studio = "Technos", releaseYear = 1987),
        Game(id = 2, title = "Batletoads", studio = "Tradewest", releaseYear = 1991),
        Game(id = 3, title = "Enduro", studio = "Activision", releaseYear = 1983),
        Game(id = 4, title = "Ikari Warriors", studio = "SNK", releaseYear = 1986),
        Game(id = 5, title = "Captain Commando", studio = "Capcom", releaseYear = 1991),
        Game(id = 6, title = "Mario Bros", studio = "Nintendo", releaseYear = 1983),
        Game(id = 7, title = "Tiger Heli", studio = "Taito", releaseYear = 1985),
        Game(id = 8, title = "Mega Man", studio = "Capcom", releaseYear = 1987),
        Game(id = 9, title = "Gradius", studio = "Konami", releaseYear = 1985),
        Game(id = 10, title = "Gun Fight", studio = "Taito", releaseYear = 1975)
    )
}
```

**Explicação:**
- **`List<Game>`:** Retorna uma lista imutável de jogos
- **`listOf()`:** Função do Kotlin para criar listas imutáveis
- **Dados estáticos:** Simula uma fonte de dados (em um app real, viria de uma API ou banco de dados)
- **Jogos clássicos:** Contém uma coleção de jogos clássicos dos anos 80 e 90

#### Função para Filtrar por Estúdio
```kotlin
fun getGamesByStudio(studio: String): List<Game> {
    return getAllGames().filter {
        it.studio.startsWith(prefix = studio, ignoreCase = true)
    }
}
```

**Explicação:**
- **`filter()`:** Função de alta ordem que filtra elementos da lista baseado em uma condição
- **`startsWith()`:** Verifica se o nome do estúdio começa com o texto pesquisado
- **`ignoreCase = true`:** Torna a busca case-insensitive (não diferencia maiúsculas de minúsculas)
- **`it`:** Referência implícita ao elemento atual da lista durante a iteração
- **Retorno:** Lista filtrada contendo apenas jogos do estúdio especificado

---

## 🎮 **SAMUEL - Componentes Reutilizáveis**

### 1. **GameCard.kt - Card para Exibir Jogos**

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
- **`@Composable`:** Anotação que marca a função como um componente Compose
- **`Card`:** Container com elevação e sombra seguindo Material Design
- **`Row`:** Layout horizontal que organiza elementos lado a lado
- **`Column`:** Layout vertical para organizar título e estúdio
- **`weight(3f)` e `weight(1f)`:** Define proporção do espaço ocupado (3:1)
- **`Alignment.CenterVertically`:** Centraliza elementos verticalmente
- **`Arrangement.SpaceBetween`:** Distribui espaço entre elementos
- **`padding(bottom = 8.dp)`:** Adiciona espaçamento inferior entre cards

### 2. **StudioCard.kt - Card para Exibir Estúdios**

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
- **`size(100.dp)`:** Define tamanho fixo de 100dp para o card
- **`clickable`:** Torna o card clicável
- **`enabled = onClick != null`:** Só permite clique se houver callback
- **`onClick?.invoke()`:** Executa o callback se não for null (safe call)
- **`Arrangement.Center`:** Centraliza o conteúdo verticalmente
- **`Alignment.CenterHorizontally`:** Centraliza o conteúdo horizontalmente
- **`padding(end = 4.dp)`:** Adiciona espaçamento à direita entre cards

### 3. **Previews para Desenvolvimento**

```kotlin
@Preview(showBackground = true, name = "Game Card Preview")
@Composable
fun PreviewGameCard() {
    FundamentosjetpackcomposelistaslazyTheme {
        GameCard(game = Game(1, "Example Game", "Example Studio", 2023))
    }
}
```

**Explicação:**
- **`@Preview`:** Anotação que gera preview no Android Studio
- **`showBackground = true`:** Mostra fundo no preview
- **`name`:** Nome identificador do preview
- **Facilita desenvolvimento:** Permite visualizar componentes sem executar o app

---

## 🎨 **CÉSAR - Interface Principal e Listas Lazy**

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
- **`ComponentActivity`:** Activity otimizada para Jetpack Compose
- **`enableEdgeToEdge()`:** Permite que o conteúdo use toda a tela
- **`setContent`:** Define o conteúdo da activity usando Compose
- **`Scaffold`:** Layout base que fornece estrutura para a tela
- **`innerPadding`:** Espaçamento interno para evitar sobreposição com barras do sistema

### 2. **GamesScreen - Tela Principal**

#### Gerenciamento de Estado
```kotlin
@Composable
fun GamesScreen(modifier: Modifier = Modifier) {
    var searchTextState by remember { mutableStateOf("") }
    var gamesListState by remember { mutableStateOf(getAllGames()) }
```

**Explicação:**
- **`remember`:** Mantém o estado durante recomposições
- **`mutableStateOf`:** Cria estado mutável observável
- **`by`:** Delegation property que simplifica o acesso ao estado
- **`searchTextState`:** Controla o texto da busca
- **`gamesListState`:** Controla a lista de jogos exibida

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
- **`OutlinedTextField`:** Campo de texto com borda
- **`value/onValueChange`:** Binding bidirecional do estado
- **`fillMaxWidth()`:** Ocupa toda a largura disponível
- **`trailingIcon`:** Ícone no final do campo
- **`IconButton`:** Botão clicável com ícone de busca

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
- **Condição `if`:** Só exibe quando há filtro ativo
- **`clickable`:** Torna o texto clicável
- **`padding`:** Adiciona espaçamento superior
- **`FontWeight.SemiBold`:** Define peso da fonte
- **Reset:** Limpa o filtro e restaura a lista completa

### 3. **Listas Lazy - Performance Otimizada**

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
- **`LazyRow`:** Lista horizontal que renderiza apenas itens visíveis
- **`items()`:** Função que itera sobre a lista
- **`onClick`:** Callback executado ao clicar no card
- **Performance:** Renderiza apenas itens visíveis, economizando memória
- **Filtro automático:** Ao clicar, filtra jogos do estúdio selecionado

#### LazyColumn para Jogos
```kotlin
LazyColumn() {
    items(gamesListState) {
        GameCard(game = it)
    }
}
```

**Explicação:**
- **`LazyColumn`:** Lista vertical otimizada
- **`it`:** Referência implícita ao elemento atual
- **Recomposição:** Apenas itens alterados são recompostos
- **Scroll infinito:** Suporta listas grandes sem problemas de performance
- **Layout otimizado:** Renderiza apenas itens visíveis na tela

### 4. **Previews da Tela Principal**

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
- **Preview completo:** Mostra como a tela inteira aparece
- **Desenvolvimento visual:** Facilita o desenvolvimento sem executar o app
- **Teste de layout:** Permite verificar se o layout está correto

---

## 🎓 **Conceitos Demonstrados**

### **Jetpack Compose:**
- **Componentes reutilizáveis:** GameCard e StudioCard
- **Estado reativo:** Gerenciamento com `remember` e `mutableStateOf`
- **Layouts:** Row, Column, LazyRow, LazyColumn
- **Modifiers:** padding, fillMaxWidth, weight, clickable

### **Performance:**
- **Listas Lazy:** Renderização otimizada para listas grandes
- **Recomposição inteligente:** Apenas componentes alterados são recompostos
- **Estado local:** Gerenciamento eficiente do estado da tela

### **Material Design:**
- **Cards:** Componentes com elevação e sombra
- **Typography:** Diferentes tamanhos e pesos de fonte
- **Cores:** Sistema de cores consistente
- **Layout responsivo:** Adaptação a diferentes tamanhos de tela

---

*Este código demonstra os fundamentos do Jetpack Compose com foco em listas lazy, componentes reutilizáveis e gerenciamento de estado reativo.*
