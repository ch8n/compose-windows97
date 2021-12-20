package io.github.ch8n.compose97.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.Router
import com.arkivanov.decompose.router.router
import io.github.ch8n.compose97.routes.desktop.DesktopComponent
import io.github.ch8n.compose97.routes.mycomputer.MyComputerComponent
import io.github.ch8n.compose97.routes.recyclebin.RecycleBinComponent

abstract class NavigationComponent(
    componentContext: ComponentContext,
) : DecomposeComponent(componentContext) {

    abstract val router: Router<Destinations, DecomposeComponent>

}


class AppNavigation(componentContext: ComponentContext) : NavigationComponent(componentContext) {

    override val router: Router<Destinations, DecomposeComponent> = router(
        initialConfiguration = Destinations.Desktop,
        childFactory = ::createDestinations
    )

    private fun createDestinations(
        destinations: Destinations,
        context: ComponentContext
    ): DecomposeComponent {
        return when (destinations) {
            is Destinations.Desktop -> DesktopComponent(context)
            is Destinations.Folder -> TODO()
            is Destinations.InternetExplorer -> TODO()
            is Destinations.MyComputer -> MyComputerComponent(context)
            is Destinations.MyDocuments -> TODO()
            is Destinations.Notepad -> TODO()
            is Destinations.RecyclerBin -> RecycleBinComponent(context)
        }
    }

    companion object {
        fun previewComponent(componentContext: ComponentContext): NavigationComponent {
            return object : NavigationComponent(componentContext) {
                override val router: Router<Destinations, DecomposeComponent>
                    get() = router(
                        initialConfiguration = Destinations.Desktop,
                        childFactory = { _, context ->
                            TODO()
                        }
                    )
            }
        }

    }


}
