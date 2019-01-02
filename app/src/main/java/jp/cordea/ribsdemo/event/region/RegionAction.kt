package jp.cordea.ribsdemo.event.region

sealed class RegionAction {
    object Init : RegionAction()
    object Refresh : RegionAction()
    class ClickedItem(val position: Int) : RegionAction()
}
