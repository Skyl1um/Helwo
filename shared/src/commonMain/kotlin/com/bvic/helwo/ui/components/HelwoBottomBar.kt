package com.bvic.helwo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.bvic.helwo.theme.HelwoColors

enum class HelwoTab {
    HOME,
    CALENDAR,
    TICKETS,
    MESSAGES,
    PROFILE
}

@Composable
fun HelwoBottomBar(
    currentTab: HelwoTab,
    onTabSelected: (HelwoTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(36.dp))
                .background(HelwoColors.DarkBottomBar)
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem(
                icon = HelwoIcons.Home,
                isSelected = currentTab == HelwoTab.HOME,
                onClick = { onTabSelected(HelwoTab.HOME) }
            )

            BottomNavItem(
                icon = HelwoIcons.Calendar,
                isSelected = currentTab == HelwoTab.CALENDAR,
                onClick = { onTabSelected(HelwoTab.CALENDAR) }
            )

            BottomNavItem(
                icon = HelwoIcons.Ticket,
                isSelected = currentTab == HelwoTab.TICKETS,
                onClick = { onTabSelected(HelwoTab.TICKETS) }
            )

            BottomNavItem(
                icon = HelwoIcons.Chat,
                isSelected = currentTab == HelwoTab.MESSAGES,
                onClick = { onTabSelected(HelwoTab.MESSAGES) }
            )

            BottomNavItem(
                icon = HelwoIcons.Profile,
                isSelected = currentTab == HelwoTab.PROFILE,
                onClick = { onTabSelected(HelwoTab.PROFILE) }
            )
        }
    }
}

@Composable
private fun BottomNavItem(
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(if (isSelected) HelwoColors.PurplePrimary else Color.Transparent)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (isSelected) Color.White else Color.White.copy(alpha = 0.5f),
            modifier = Modifier.size(24.dp)
        )
    }
}
