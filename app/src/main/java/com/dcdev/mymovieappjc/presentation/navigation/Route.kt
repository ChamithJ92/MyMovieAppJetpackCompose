package com.dcdev.mymovieappjc.presentation.navigation

sealed class Route(val route: String){

    object MovieDetailsScreen: Route(route = "detailsScreen")

    object MovieVideoDetailsScreen: Route(route = "videoScreen")

    object SearchScreen: Route(route = "searchScreen")


}
