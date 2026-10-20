package com.bvic.helwo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bvic.helwo.theme.HelwoColors

@Composable
fun TagPill(
    text: String,
    backgroundColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = textColor,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    )
}

@Composable
fun StatusPayePill(modifier: Modifier = Modifier) {
    TagPill(
        text = "Payé",
        backgroundColor = HelwoColors.StatusPayeBg,
        textColor = HelwoColors.StatusPayeText,
        modifier = modifier
    )
}

@Composable
fun StatusAPayerPill(modifier: Modifier = Modifier) {
    TagPill(
        text = "À payer",
        backgroundColor = HelwoColors.StatusAPayerBg,
        textColor = HelwoColors.StatusAPayerText,
        modifier = modifier
    )
}

@Composable
fun StatusEnAttentePill(modifier: Modifier = Modifier) {
    TagPill(
        text = "En attente",
        backgroundColor = HelwoColors.StatusEnAttenteBg,
        textColor = HelwoColors.StatusEnAttenteText,
        modifier = modifier
    )
}
