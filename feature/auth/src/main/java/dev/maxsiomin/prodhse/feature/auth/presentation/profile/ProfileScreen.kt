package dev.maxsiomin.prodhse.feature.auth.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import dev.maxsiomin.authlib.domain.AuthStatus
import dev.maxsiomin.authlib.domain.model.UserInfo
import dev.maxsiomin.prodhse.core.presentation.theme.ProdhseTheme
import dev.maxsiomin.prodhse.feature.auth.R
import dev.maxsiomin.prodhse.feature.auth.theme.CyanThemeColor
import dev.maxsiomin.prodhse.feature.auth.theme.CyanThemeColorGradientEnd
import dev.maxsiomin.prodhse.navdestinations.Screen

@Composable
internal fun ProfileScreenRoot(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    if (state.authStatus == AuthStatus.NotAuthenticated) {
        navController.navigate(Screen.AuthScreen.route)
        return
    }

    if (state.authStatus is AuthStatus.Loading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            )
        }
    }

    (state.authStatus as? AuthStatus.Authenticated)?.userInfo?.let {  userInfo ->
        AuthenticatedProfileScreen(userInfo = userInfo, onEvent = viewModel::onEvent)
    }

}

@Composable
private fun AuthenticatedProfileScreen(userInfo: UserInfo, onEvent: (ProfileViewModel.Event) -> Unit) {

    Column(
        Modifier
            .background(
                brush = Brush.linearGradient(
                    1f to CyanThemeColor, 1f to CyanThemeColorGradientEnd,
                )
            )
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        IconButton(
            modifier = Modifier
                .align(Alignment.End)
                .padding(16.dp),
            onClick = {
                onEvent(ProfileViewModel.Event.LogoutClicked)
            },
        ) {
            Icon(
                tint = Color.White,
                imageVector = Icons.AutoMirrored.Filled.Logout,
                contentDescription = stringResource(id = R.string.log_out),
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "@${userInfo.username}", color = Color.White, fontSize = 24.sp)

        Spacer(modifier = Modifier.height(4.dp))

        Spacer(modifier = Modifier.weight(1f))

    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    ProdhseTheme {
        AuthenticatedProfileScreen(
            userInfo = UserInfo(
                username = "maxsiomindev",
                fullName = "Max S.",
                avatarUrl = "",
                passwordHash = "",
            ),
            onEvent = {},
        )
    }
}