package com.adridoce.cineo.presentation.core.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adridoce.cineo.ui.theme.Primary
import com.adridoce.cineo.ui.theme.Surface
import com.adridoce.cineo.ui.theme.TextPrimary
import com.adridoce.cineo.ui.theme.TextSecondary

@Composable
fun TrendToggle(
    selected: TrendFilter,
    onSelect: (TrendFilter) -> Unit
) {

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(Surface)
            .padding(4.dp)
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        TrendOption(
            text = "Hoy",
            selected = selected == TrendFilter.DAILY,
            onClick = { onSelect(TrendFilter.DAILY) }
        )
        TrendOption(
            text = "Semana",
            selected = selected == TrendFilter.WEEKLY,
            onClick = { onSelect(TrendFilter.WEEKLY) }
        )
    }

}

@Composable
fun TrendOption(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    val backgroundColor by animateColorAsState(
        targetValue = if (selected) Primary else Surface
    )
    val textColor by animateColorAsState(
        targetValue = if (selected) TextPrimary else TextSecondary
    )

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            fontSize = 14.sp
        )
    }
}

enum class TrendFilter { DAILY, WEEKLY }