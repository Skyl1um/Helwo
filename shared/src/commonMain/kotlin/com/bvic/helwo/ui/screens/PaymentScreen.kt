package com.bvic.helwo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
fun PaymentScreen(
    eventId: String,
    onBackClick: () -> Unit,
    onPaymentSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    var ticketCount by remember { mutableStateOf(1) }
    val pricePerTicket = 8.00
    val totalAmount = ticketCount * pricePerTicket
    var selectedMethod by remember { mutableStateOf("CARD") } // CARD or GPAY

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(HelwoColors.BackgroundLight)
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Header Top Bar
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

                Text(
                    text = "Paiement",
                    color = HelwoColors.TextPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }

            // Event Summary Card
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(HelwoColors.GradientPurpleToCoral)
                    )

                    Column {
                        Text(
                            text = "Soirée de rentrée",
                            color = HelwoColors.TextPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp
                        )
                        Text(
                            text = "Ven. 23 oct · 22h · Salle des fêtes",
                            color = HelwoColors.TextSecondary,
                            fontSize = 13.sp
                        )
                    }
                }
            }

            // Quantity & Pricing Card
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Nombre de billets",
                            color = HelwoColors.TextPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFF0F1F5))
                                    .clickable { if (ticketCount > 1) ticketCount-- },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "-",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    color = HelwoColors.TextPrimary
                                )
                            }

                            Text(
                                text = "$ticketCount",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = HelwoColors.TextPrimary
                            )

                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(HelwoColors.PurplePrimary)
                                    .clickable { ticketCount++ },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "+",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    color = Color.White
                                )
                            }
                        }
                    }

                    HorizontalDivider(color = Color(0xFFE5E7EB))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Billet × $ticketCount",
                            color = HelwoColors.TextSecondary,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "${(ticketCount * 8)},00 €",
                            color = HelwoColors.TextPrimary,
                            fontSize = 14.sp
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Frais de service",
                            color = HelwoColors.TextSecondary,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "[0,00 €]",
                            color = HelwoColors.TextSecondary,
                            fontSize = 14.sp
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Total",
                            color = HelwoColors.TextPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text(
                            text = "${totalAmount.toInt()},00 €",
                            color = HelwoColors.TextPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                }
            }

            // Payment Method Title
            Text(
                text = "Moyen de paiement",
                color = HelwoColors.TextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            // Payment Option 1: Carte bancaire
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(if (selectedMethod == "CARD") Color(0xFFEDEBFD) else Color.White)
                    .border(
                        width = if (selectedMethod == "CARD") 2.dp else 0.dp,
                        color = if (selectedMethod == "CARD") HelwoColors.PurplePrimary else Color.Transparent,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .clickable { selectedMethod = "CARD" }
                    .padding(18.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Icon(
                            imageVector = HelwoIcons.CreditCard,
                            contentDescription = null,
                            tint = HelwoColors.PurplePrimary,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "Carte bancaire",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = HelwoColors.TextPrimary
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .border(2.dp, HelwoColors.PurplePrimary, CircleShape)
                            .background(if (selectedMethod == "CARD") HelwoColors.PurplePrimary else Color.Transparent),
                        contentAlignment = Alignment.Center
                    ) {
                        if (selectedMethod == "CARD") {
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .clip(CircleShape)
                                    .background(Color.White)
                            )
                        }
                    }
                }
            }

            // Payment Option 2: Google Pay
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(if (selectedMethod == "GPAY") Color(0xFFEDEBFD) else Color.White)
                    .border(
                        width = if (selectedMethod == "GPAY") 2.dp else 0.dp,
                        color = if (selectedMethod == "GPAY") HelwoColors.PurplePrimary else Color.Transparent,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .clickable { selectedMethod = "GPAY" }
                    .padding(18.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Icon(
                            imageVector = HelwoIcons.Check,
                            contentDescription = null,
                            tint = HelwoColors.TextPrimary,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "Google Pay",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = HelwoColors.TextPrimary
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color(0xFFD1D5DB), CircleShape)
                            .background(if (selectedMethod == "GPAY") HelwoColors.PurplePrimary else Color.Transparent),
                        contentAlignment = Alignment.Center
                    ) {
                        if (selectedMethod == "GPAY") {
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .clip(CircleShape)
                                    .background(Color.White)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Pay Button CTA
            Button(
                onClick = onPaymentSuccess,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = HelwoColors.CoralPrimary),
                shape = RoundedCornerShape(28.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                Text(
                    text = "Payer ${totalAmount.toInt()},00 €",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Text(
                text = "Paiement sécurisé par Stripe",
                color = HelwoColors.TextSecondary,
                fontSize = 13.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}
