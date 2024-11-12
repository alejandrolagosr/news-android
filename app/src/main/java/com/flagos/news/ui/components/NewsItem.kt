package com.flagos.news.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
) {
    Card(
        shape = RoundedCornerShape(CornerSize(12.dp)),
        modifier = modifier.padding(all = 8.dp),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 5.dp)
        ) {
            Text(
                modifier = Modifier.padding(all = 16.dp),
                text = news.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = TWO,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface
            )
            Row {
                Text(
                    modifier = Modifier.padding(all = 16.dp),
                    text = news.author,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = ONE,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    modifier = Modifier.padding(all = 16.dp),
                    text = news.createdAt,
                    style = MaterialTheme.typography.labelSmall,
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
            id = 0,
            title = "Title",
            author = "Author",
            url = "www.google.com",
            createdAt = "23 min ago",
        )
    )
}