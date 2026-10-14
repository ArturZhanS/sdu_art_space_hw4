package com.example.artspaceapp

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource

enum class Artwork{
    MONA_LISA,
    STARRY_NIGHT,
    GIRL_WITH_A_PEARL_EARRING,
    WANDERER_ABOVE_THE_SEA_OF_FOG,
    NINTH_WAVE
}

data class ArtData(
    @DrawableRes val image: Int,
    @StringRes val title: Int,
    @StringRes val artist: Int,
    @StringRes val year: Int
)

class ArtNavigator() {
    fun switchArtwork(current: Artwork, next: Boolean): Artwork {
        return when (current) {
            Artwork.MONA_LISA -> if (next) Artwork.STARRY_NIGHT else Artwork.MONA_LISA
            Artwork.STARRY_NIGHT -> if (next) Artwork.GIRL_WITH_A_PEARL_EARRING else Artwork.MONA_LISA
            Artwork.GIRL_WITH_A_PEARL_EARRING -> if (next) Artwork.WANDERER_ABOVE_THE_SEA_OF_FOG else Artwork.STARRY_NIGHT
            Artwork.WANDERER_ABOVE_THE_SEA_OF_FOG -> if (next) Artwork.NINTH_WAVE else Artwork.GIRL_WITH_A_PEARL_EARRING
            Artwork.NINTH_WAVE -> if (next) Artwork.NINTH_WAVE else Artwork.WANDERER_ABOVE_THE_SEA_OF_FOG
        }
    }

    fun getArtData(artwork: Artwork): ArtData {
        return when (artwork) {
            Artwork.MONA_LISA -> ArtData(
                image = R.drawable.mona_lisa_art,
                title = R.string.artwork_title_mona,
                artist = R.string.artwork_artist_mona,
                year = R.string.artwork_year_mona
            )
            Artwork.STARRY_NIGHT -> ArtData(
                image = R.drawable.starry_night,
                title = R.string.artwork_title_night,
                artist = R.string.artwork_artist_night,
                year = R.string.artwork_year_night
            )
            Artwork.GIRL_WITH_A_PEARL_EARRING -> ArtData(
                image = R.drawable.girl_with_a_pearl_earring,
                title = R.string.artwork_title_pearl,
                artist = R.string.artwork_artist_pearl,
                year = R.string.artwork_year_pearl
            )
            Artwork.WANDERER_ABOVE_THE_SEA_OF_FOG -> ArtData(
                image = R.drawable.wanderer_above_the_sea_of_fog,
                title = R.string.artwork_title_wanderer,
                artist = R.string.artwork_artist_wanderer,
                year = R.string.artwork_year_wanderer
            )
            Artwork.NINTH_WAVE -> ArtData(
                image = R.drawable.ninth_wave,
                title = R.string.artwork_title_ninth,
                artist = R.string.artwork_artist_ninth,
                year = R.string.artwork_year_ninth
            )
        }

    }
}