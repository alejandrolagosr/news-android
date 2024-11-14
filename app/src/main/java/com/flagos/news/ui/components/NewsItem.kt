package com.flagos.news.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flagos.domain.model.News

private const val TWO = 2
private const val ONE = 1

@Composable
fun NewsItems(
    modifier: Modifier = Modifier,
    news: News,
    onItemRemoved: () -> Unit = {},
    onItemClicked: () -> Unit = {}
) {
    val dismissState = rememberSwipeToDismissBoxState()
    if (dismissState.currentValue == SwipeToDismissBoxValue.EndToStart) {
        onItemRemoved()
    }

    SwipeToDismissBox(
        state = dismissState,
        backgroundContent = {
            Box(
                Modifier
                    .fillMaxSize()
            )
        },
        enableDismissFromEndToStart = true,
        enableDismissFromStartToEnd = false,
    ) {
        Card(
            shape = RoundedCornerShape(CornerSize(12.dp)),
            modifier = modifier.padding(all = 8.dp),
            elevation = CardDefaults.cardElevation(3.dp),
            onClick = { onItemClicked() }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 5.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(all = 16.dp)
                        .fillMaxWidth(),
                    text = news.title,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = TWO,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    modifier = Modifier.padding(all = 16.dp),
                    text = "${news.author} - ${news.relativeTime}",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = ONE,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Preview
@Composable
fun NewsItemsPreview() {
    NewsItems(
        news = News(
            id = "id",
            title = "Title",
            author = "Author",
            url = "www.google.com",
            relativeTime = "23m",
        )
    )
}