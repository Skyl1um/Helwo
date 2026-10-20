package com.bvic.helwo.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bvic.helwo.theme.HelwoColors
import com.bvic.helwo.ui.components.HelwoIcons

@Composable
fun TicketDetailScreen(
    ticketId: String,
    onBackClick: () -> Unit,
    onJoinChannelClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(HelwoColors.PurplePrimary)
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // White Circle with Green Checkmark
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable { onBackClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = HelwoIcons.Check,
                    contentDescription = "Confirmé",
                    tint = Color(0xFF1BB564),
                    modifier = Modifier.size(32.dp)
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "Place confirmée",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp
                )
                Text(
                    text = "Ton billet est aussi dans « Mes billets »",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 14.sp
                )
            }

            // Ticket Card Container
            Card(
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "SOIRÉE",
                        color = HelwoColors.PurplePrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        letterSpacing = 1.sp
                    )

                    Text(
                        text = "Soirée de rentrée",
                        color = HelwoColors.TextPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "Date",
                                color = HelwoColors.TextSecondary,
                                fontSize = 12.sp
                            )
                            Text(
                                text = "Ven. 23 oct · 22h",
                                color = HelwoColors.TextPrimary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }

                        Column {
                            Text(
                                text = "Lieu",
                                color = HelwoColors.TextSecondary,
                                fontSize = 12.sp
                            )
                            Text(
                                text = "Salle des fêtes",
                                color = HelwoColors.TextPrimary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }
                    }

                    Column {
                        Text(
                            text = "Participant",
                            color = HelwoColors.TextSecondary,
                            fontSize = 12.sp
                        )
                        Text(
                            text = "Léa [Nom]",
                            color = HelwoColors.TextPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }

                    // Dashed Divider
                    Canvas(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .padding(vertical = 8.dp)
                    ) {
                        drawLine(
                            color = Color(0xFFD1D5DB),
                            start = Offset(0f, 0f),
                            end = Offset(size.width, 0f),
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                        )
                    }

                    // QR Code Graphic Container
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Canvas(modifier = Modifier.size(180.dp)) {
                            val cellSize = size.width / 9f
                            val darkColor = Color(0xFF161722)

                            // Drawn Mock QR matrix blocks
                            val qrMatrix = listOf(
                                listOf(1, 1, 1, 0, 1, 0, 1, 1, 1),
                                listOf(1, 0, 1, 1, 0, 1, 1, 0, 1),
                                listOf(1, 1, 1, 0, 1, 0, 1, 1, 1),
                                listOf(0, 1, 0, 1, 1, 1, 0, 1, 0),
                                listOf(1, 0, 1, 1, 0, 1, 1, 0, 1),
                                listOf(0, 1, 1, 0, 1, 0, 0, 1, 1),
                                listOf(1, 1, 1, 1, 0, 1, 1, 1, 0),
                                listOf(1, 0, 1, 0, 1, 1, 1, 0, 1),
                                listOf(1, 1, 1, 1, 1, 0, 1, 1, 1)
                            )

                            for (r in qrMatrix.indices) {
                                for (c in qrMatrix[r].indices) {
                                    if (qrMatrix[r][c] == 1) {
                                        drawRect(
                                            color = darkColor,
                                            topLeft = Offset(c * cellSize, r * cellSize),
                                            size = Size(cellSize * 0.9f, cellSize * 0.9f)
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Text(
                        text = "À présenter à l'entrée · [N° du billet]",
                        color = HelwoColors.TextSecondary,
                        fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = { },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                    contentPadding = PaddingValues(vertical = 12.dp)
                ) {
                    Text(
                        text = "Ajouter à l'agenda",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }

                Button(
                    onClick = onJoinChannelClick,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = HelwoColors.CoralPrimary),
                    contentPadding = PaddingValues(vertical = 12.dp)
                ) {
                    Text(
                        text = "Rejoindre le canal",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
