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

@Composable
fun HomeScreen(
    onNavigateToEvent: (String) -> Unit,
    onNavigateToPayment: (String) -> Unit,
    onNavigateToCalendar: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedFilter by remember { mutableStateOf("Tout") }
    val filterOptions = listOf("Tout", "Soirées", "Sport", "Clubs")

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(HelwoColors.BackgroundLight)
            .padding(horizontal = 20.dp),
        contentPadding = PaddingValues(top = 20.dp, bottom = 100.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Top Bar: Profile & Notifications
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(HelwoColors.PurplePrimary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "LM",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }

                    Column {
                        Text(
                            text = "Mardi 20 octobre",
                            color = HelwoColors.TextSecondary,
                            fontSize = 13.sp
                        )
                        Text(
                            text = "Salut Léa",
                            color = HelwoColors.TextPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .clickable { },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = HelwoIcons.Bell,
                        contentDescription = "Notifications",
                        tint = HelwoColors.TextPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        // Hero Event Card (Soirée de rentrée)
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(28.dp))
                    .background(HelwoColors.GradientPurpleToCoral)
                    .clickable { onNavigateToEvent("soiree-rentree") }
                    .padding(24.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Pill "Prochain événement · Ce soir 22h"
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.White.copy(alpha = 0.9f))
                            .padding(horizontal = 14.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "Prochain événement · Ce soir 22h",
                            color = HelwoColors.TextPrimary,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Soirée de rentrée",
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Salle des fêtes · 8 €",
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 15.sp
                        )

                        Button(
                            onClick = { onNavigateToPayment("soiree-rentree") },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = HelwoColors.TextPrimary
                            ),
                            shape = RoundedCornerShape(20.dp),
                            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = "Réserver",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }

        // Category Filter Chips
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(filterOptions.size) { index ->
                    val filter = filterOptions[index]
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
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }

        // Section: "À venir" + "Calendrier"
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "À venir",
                    color = HelwoColors.TextPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    text = "Calendrier",
                    color = HelwoColors.PurplePrimary,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable { onNavigateToCalendar() }
                )
            }
        }

        // Event Card 1: Tournoi inter-promos
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFFEDEBFD)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "24",
                                color = HelwoColors.PurplePrimary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                            Text(
                                text = "OCT",
                                color = HelwoColors.PurplePrimary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp
                            )
                        }
                    }

                    Column {
                        Text(
                            text = "Tournoi inter-promos",
                            color = HelwoColors.TextPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Club Sport · 14h · Gratuit",
                            color = HelwoColors.TextSecondary,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }

        // Event Card 2: Soirée Halloween
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
                    .clickable { onNavigateToEvent("soiree-halloween") }
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color(0xFFFFEBE8)),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "31",
                                    color = HelwoColors.CoralPrimary,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )
                                Text(
                                    text = "OCT",
                                    color = HelwoColors.CoralPrimary,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp
                                )
                            }
                        }

                        Column {
                            Text(
                                text = "Soirée Halloween",
                                color = HelwoColors.TextPrimary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                            Text(
                                text = "BDE · 21h · 10 €",
                                color = HelwoColors.TextSecondary,
                                fontSize = 13.sp
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFFFFEBE8))
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "Places limitées",
                            color = HelwoColors.CoralPrimary,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        // Section: "Activité"
        item {
            Text(
                text = "Activité",
                color = HelwoColors.TextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

        // Activity Card
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(HelwoColors.CoralPrimary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "TM",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }

                    Row {
                        Text(
                            text = "Tom ",
                            fontWeight = FontWeight.Bold,
                            color = HelwoColors.TextPrimary,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "a posté dans ",
                            color = HelwoColors.TextSecondary,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "#soiree-rentree",
                            fontWeight = FontWeight.Bold,
                            color = HelwoColors.PurplePrimary,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}
