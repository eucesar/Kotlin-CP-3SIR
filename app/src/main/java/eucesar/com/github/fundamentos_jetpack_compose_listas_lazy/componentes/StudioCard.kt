package eucesar.com.github.fundamentos_jetpack_compose_listas_lazy.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import eucesar.com.github.fundamentos_jetpack_compose_listas_lazy.model.Game
import eucesar.com.github.fundamentos_jetpack_compose_listas_lazy.ui.theme.FundamentosjetpackcomposelistaslazyTheme
import androidx.compose.foundation.clickable

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

@Preview(showBackground = true, name = "Studio Card Preview")
@Composable
fun PreviewStudioCard() {
    FundamentosjetpackcomposelistaslazyTheme {
        StudioCard(game = Game(1, "Example Game", "Example Studio", 2023))
    }
}