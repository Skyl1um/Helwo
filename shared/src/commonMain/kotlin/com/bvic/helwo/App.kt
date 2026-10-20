package com.bvic.helwo

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bvic.helwo.theme.HelwoTheme
import com.bvic.helwo.ui.components.HelwoBottomBar
import com.bvic.helwo.ui.components.HelwoTab
import com.bvic.helwo.ui.screens.*

sealed interface Screen {
    data object Login : Screen
    data object Home : Screen
    data class EventDetail(val eventId: String) : Screen
    data class Payment(val eventId: String) : Screen
    data object MyTickets : Screen
    data class TicketDetail(val ticketId: String) : Screen
    data object Organizer : Screen
}

@Composable
fun App() {
    HelwoTheme {
        var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }
        var currentTab by remember { mutableStateOf(HelwoTab.HOME) }

        val showBottomBar = when (currentScreen) {
            Screen.Home, Screen.MyTickets, Screen.Organizer -> true
            else -> false
        }

        Box(modifier = Modifier.fillMaxSize()) {
            Crossfade(targetState = currentScreen, label = "ScreenTransition") { screen ->
                when (screen) {
                    Screen.Login -> {
                        LoginScreen(
                            onLoginSuccess = {
                                currentScreen = Screen.Home
                                currentTab = HelwoTab.HOME
                            }
                        )
                    }

                    Screen.Home -> {
                        HomeScreen(
                            onNavigateToEvent = { eventId -> currentScreen = Screen.EventDetail(eventId) },
                            onNavigateToPayment = { eventId -> currentScreen = Screen.Payment(eventId) },
                            onNavigateToCalendar = { currentScreen = Screen.EventDetail("soiree-rentree") }
                        )
                    }

                    is Screen.EventDetail -> {
                        EventDetailScreen(
                            eventId = screen.eventId,
                            onBackClick = { currentScreen = Screen.Home },
                            onReserveClick = { eventId -> currentScreen = Screen.Payment(eventId) },
                            onChatClick = { currentScreen = Screen.Organizer }
                        )
                    }

                    is Screen.Payment -> {
                        PaymentScreen(
                            eventId = screen.eventId,
                            onBackClick = { currentScreen = Screen.Home },
                            onPaymentSuccess = {
                                currentScreen = Screen.TicketDetail(screen.eventId)
                                currentTab = HelwoTab.TICKETS
                            }
                        )
                    }

                    Screen.MyTickets -> {
                        MyTicketsScreen(
                            onShowQrClick = { ticketId -> currentScreen = Screen.TicketDetail(ticketId) },
                            onPayClick = { eventId -> currentScreen = Screen.Payment(eventId) }
                        )
                    }

                    is Screen.TicketDetail -> {
                        TicketDetailScreen(
                            ticketId = screen.ticketId,
                            onBackClick = {
                                currentScreen = Screen.MyTickets
                                currentTab = HelwoTab.TICKETS
                            },
                            onJoinChannelClick = { currentScreen = Screen.Organizer }
                        )
                    }

                    Screen.Organizer -> {
                        OrganizerScreen(
                            onBackClick = {
                                currentScreen = Screen.Home
                                currentTab = HelwoTab.HOME
                            },
                            onEditEventClick = { currentScreen = Screen.EventDetail("soiree-rentree") }
                        )
                    }
                }
            }

            if (showBottomBar) {
                HelwoBottomBar(
                    currentTab = currentTab,
                    onTabSelected = { tab ->
                        currentTab = tab
                        currentScreen = when (tab) {
                            HelwoTab.HOME -> Screen.Home
                            HelwoTab.TICKETS -> Screen.MyTickets
                            HelwoTab.PROFILE -> Screen.Organizer
                            HelwoTab.MESSAGES -> Screen.Login
                            HelwoTab.CALENDAR -> Screen.EventDetail("soiree-rentree")
                        }
                    },
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }
    }
}
