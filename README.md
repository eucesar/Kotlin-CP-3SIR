Este projeto é um aplicativo Android desenvolvido em Kotlin, utilizando Jetpack Compose para a construção de interfaces modernas e reativas. O objetivo principal é demonstrar o uso de listas Lazy (LazyColumn e LazyRow) para exibir e filtrar jogos favoritos por estúdio.

## Funcionalidades

- Exibição de uma lista de jogos favoritos.
- Filtro de jogos por nome do estúdio, via campo de texto ou seleção direta.
- Lista horizontal de estúdios (StudioCard) para filtro rápido.
- Botão de limpar filtro, exibido apenas quando um filtro está ativo.
- Interface moderna utilizando Material 3.

## Estrutura do Projeto

```
app/
 ├── src/
 │   ├── main/
 │   │   ├── java/
 │   │   │   └── carreiras/com/github/fundamentos_jetpack_compose_listas_lazy/
 │   │   │        ├── MainActivity.kt         # Tela principal e lógica de UI
 │   │   │        ├── components/
 │   │   │        │    ├── GameCard.kt       # Componente visual para jogos
 │   │   │        │    └── StudioCard.kt     # Componente visual para estúdios
 │   │   │        ├── model/
 │   │   │        │    └── Game.kt           # Modelo de dados para jogos
 │   │   │        ├── repository/
 │   │   │        │    ├── GameRepository.kt # Funções de acesso e filtro de dados
 │   │   │        │    └── ...
 │   │   │        └── ui/theme/              # Temas e estilos
 │   │   └── res/                            # Recursos (layouts, strings, etc)
 │   └── ...
 └── ...
```

## Como funciona

- A tela principal exibe uma lista de jogos e uma lista horizontal de estúdios.
- O usuário pode filtrar os jogos digitando o nome do estúdio ou clicando em um StudioCard.
- O filtro pode ser limpo facilmente com o botão "Limpar filtro".

## Como rodar o projeto

1. Clone este repositório:
   ```sh
   git clone https://github.com/eucesar/Kotlin-CP-3SIR.git
   ```
2. Abra o projeto no Android Studio.
3. Execute em um emulador ou dispositivo físico Android.

## Tecnologias utilizadas
- **Kotlin**
- **Jetpack Compose**
- **Material 3**
- **Gradle Kotlin DSL**

## Screenshots

> Adicione aqui prints da tela principal, busca e filtro aplicados (opcional):
<img width="326" height="705" alt="image" src="https://github.com/user-attachments/assets/3cc51d98-0d46-418f-a29b-aa8159a5827d" />
<img width="336" height="698" alt="image" src="https://github.com/user-attachments/assets/708a1e4f-7aeb-43a3-a8d8-3e8013c23ea9" />
<img width="326" height="703" alt="image" src="https://github.com/user-attachments/assets/ffba210e-2579-47ca-b126-e8d1cf751f0b" />
<img width="341" height="701" alt="image" src="https://github.com/user-attachments/assets/83702536-d528-40c0-98d7-c8e86306a920" />


## Autor
- Desenvolvido por: </br>
Rafael Villela - 550275  </br>
Samuel Aguiar - 550212  </br>
Cesar Iglesias - 98007 </br>

---

Este projeto é um exemplo didático para estudos de Jetpack Compose e listas dinâmicas no Android.
