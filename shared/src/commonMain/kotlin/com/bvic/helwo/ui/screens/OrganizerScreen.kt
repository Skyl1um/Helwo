package com.bvic.helwo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bvic.helwo.theme.HelwoColors
import com.bvic.helwo.ui.components.HelwoIcons
import com.bvic.helwo.ui.components.StatusEnAttentePill
import com.bvic.helwo.ui.components.StatusPayePill

data class ParticipantItem(
    val initials: String,
    val name: String,
    val promo: String,
    val isPaid: Boolean
)

@Composable
fun OrganizerScreen(
    onBackClick: () -> Unit,
    onEditEventClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var selectedFilter by remember { mutableStateOf("Tous") }
    val filters = listOf("Tous", "Payé", "Non payé")

    val participants = listOf(
        ParticipantItem("AB", "[Prénom Nom]", "Promo [X]", true),
        ParticipantItem("CD", "[Prénom Nom]", "Promo [X]", false),
        ParticipantItem("EF", "[Prénom Nom]", "Promo [X]", true)
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(HelwoColors.BackgroundLight)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            contentPadding = PaddingValues(top = 20.dp, bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Header Top Bar
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .clickable { onBackClick() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = HelwoIcons.ArrowBack,
                            contentDescription = "Retour",
                            tint = HelwoColors.TextPrimary,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Column {
                        Text(
                            text = "Espace organisateur",
                            color = HelwoColors.TextSecondary,
                            fontSize = 13.sp
                        )
                        Text(
                            text = "Soirée de rentrée",
                            color = HelwoColors.TextPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                    }
                }
            }

            // Main Revenue / Stripe Card
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(28.dp))
                        .background(HelwoColors.PurplePrimary)
                        .padding(24.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Text(
                            text = "Encaissé via Stripe",
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 14.sp
                        )

                        Text(
                            text = "[000] €",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 36.sp
                        )

                        // Progress Bar
                        LinearProgressIndicator(
                            progress = { 0.65f },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            color = Color.White,
                            trackColor = Color.White.copy(alpha = 0.3f)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "[N] payés",
                                color = Color.White.copy(alpha = 0.9f),
                                fontSize = 13.sp
                            )
                            Text(
                                text = "[N] en attente",
                                color = Color.White.copy(alpha = 0.9f),
                                fontSize = 13.sp
                            )
                        }
                    }
                }
            }

            // Filter Chips
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(filters.size) { index ->
                        val filter = filters[index]
                        val isSelected = filter == selectedFilter
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(if (isSelected) HelwoColors.TextPrimary else Color.White)
                                .clickable { selectedFilter = filter }
                                .padding(horizontal = 20.dp, vertical = 10.dp)
                        ) {
                            Text(
                                text = filter,
                                color = if (isSelected) Color.White else HelwoColors.TextPrimary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }

            // Participant List Items
            items(participants.size) { index ->
                val participant = participants[index]
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFEDEBFD)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = participant.initials,
                                    color = HelwoColors.PurplePrimary,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp
                                )
                            }

                            Column {
                                Text(
                                    text = participant.name,
                                    color = HelwoColors.TextPrimary,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp
                                )
                                Text(
                                    text = participant.promo,
                                    color = HelwoColors.TextSecondary,
                                    fontSize = 13.sp
                                )
                            }
                        }

                        if (participant.isPaid) {
                            StatusPayePill()
                        } else {
                            StatusEnAttentePill()
                        }
                    }
                }
            }
        }

        // Bottom CTA Button: Modifier l'événement
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Button(
                onClick = onEditEventClick,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = HelwoColors.CoralPrimary),
                shape = RoundedCornerShape(28.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                Text(
                    text = "Modifier l'événement",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}
